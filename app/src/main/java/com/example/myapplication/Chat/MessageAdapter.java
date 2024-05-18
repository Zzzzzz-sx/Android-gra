package com.example.myapplication.Chat;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messages;
    private Context context;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.senderTextView.setText(message.getSender());
        holder.messageTextView.setText(message.getContent());
        // 根据消息是发送者还是接收者调整布局
        if (message.isIssender()) {
            // 设置发送的消息的背景
            GradientDrawable sentBackground = (GradientDrawable) ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_message_sent);
            holder.messageTextView.setBackground(sentBackground);
            holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL); // 文本从右向左布局
        } else {
            // 设置接收到的消息的背景
            GradientDrawable receivedBackground = (GradientDrawable) ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_message_received);
            holder.messageTextView.setBackground(receivedBackground);
            holder.itemView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR); // 文本从左向右布局
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView messageTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.senderTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
    }
}
