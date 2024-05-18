package com.example.myapplication.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView chatRecyclerView;
    private EditText messageEditText;
    private Button sendButton;
    private MessageAdapter adapter;
    private WebSocketClient mWebSocketClient;
    private List<Message> messageList;
    String pat_name,doctor_name,receiver;
    int pat_id,doctor_id;
    private boolean issender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        Intent intent = getIntent();
        if(intent != null){
            doctor_name = intent.getStringExtra("name");
            doctor_id = intent.getIntExtra("doc_id",0);
        }
        if (doctor_id==4){
            pat_id=3;
            pat_name="doctor3";
        }
        else if(doctor_id==3){
            pat_id=4;
            pat_name="doctor4";
        }
        issender = true;
        Log.d("DocterChatActivity","成功链接服务器");
        // 初始化界面组件
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        // 初始化消息列表和适配器
        messageList = new ArrayList<>();
        adapter = new MessageAdapter(messageList, this);
        chatRecyclerView.setAdapter(adapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 建立 WebSocket 连接
        connectWebSocket();

        // 发送按钮点击事件
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    messageEditText.setText(""); // 清空输入框
                }
            }
        });
    }

    // 建立 WebSocket 连接
    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.0.2.2:8765");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.d("DocterChatActivity.java","成功链接服务器");
                requestHistory();
            }

            @Override
            public void onMessage(String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 解析服务器发送的消息
                        // 假设消息格式为 "sender:receiver:content"
                        String[] parts = message.split(":");
                        if (parts.length == 3) {
                            String sender = parts[0].trim();
                            receiver = parts[1].trim();
                            String content = parts[2].trim();
                            Log.d("WebSocket", "Message sender: "+sender+"Message receiver: "+receiver);
                            if (sender.equals(doctor_name)){
                                issender = true;
                                // 更新 RecyclerView 中的数据
                                messageList.add(new Message(sender, content, issender));
                                adapter.notifyDataSetChanged();
                                chatRecyclerView.smoothScrollToPosition(adapter.getItemCount() - 1); // 滚动到最新消息位置
                            }
                            else if (receiver.equals(doctor_name)){
                                issender = false;
                                // 更新 RecyclerView 中的数据
                                messageList.add(new Message(sender, content, issender));
                                adapter.notifyDataSetChanged();
                                chatRecyclerView.smoothScrollToPosition(adapter.getItemCount() - 1); // 滚动到最新消息位置
                            }

                        }
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {}

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                Log.e("WebSocket", "WebSocket error: " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    private void requestHistory() {
        if (mWebSocketClient != null && mWebSocketClient.isOpen()) {
            mWebSocketClient.send("request_history");
        }
    }


    // 发送消息到 WebSocket 服务器
    private void sendMessage(String message) {
        Log.d("WebSocket", "WebSocket client: " + (mWebSocketClient != null ? "not null" : "null"));
        Log.d("WebSocket", "WebSocket connection status: " + (mWebSocketClient != null && mWebSocketClient.isOpen() ? "open" : "not open"));
        if (mWebSocketClient != null && mWebSocketClient.isOpen()) {
            mWebSocketClient.send(doctor_name+":"+pat_name+":"+message);
            Log.d("WebSocket", "Message receiver: "+pat_name);
            Log.d("WebSocket", "Message sent: "+message);
// // 更新 RecyclerView 中的数据
// messageList.add(new Message("我", message, true));
// adapter.notifyDataSetChanged();
// chatRecyclerView.smoothScrollToPosition(adapter.getItemCount() - 1); // 滚动到最新消息位置
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭 WebSocket 连接
        if (mWebSocketClient != null) {
            mWebSocketClient.close();
        }
    }
}