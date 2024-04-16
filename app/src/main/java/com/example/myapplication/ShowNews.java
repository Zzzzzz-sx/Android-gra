package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.service.quicksettings.Tile;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowNews extends AppCompatActivity {
    TextView Title,Content;
    String title,content;
    Button deletenews;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_news);
        Title = this.findViewById(R.id.tv_news_title);
        Content = this.findViewById(R.id.tv_news_content);
        deletenews = this.findViewById(R.id.btn_delete_news);
        newsshow();
        deletenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击删除对应news
            }
        });
    }

    public void newsshow(){
        Intent intent = getIntent();
        if(intent != null){
            //接收数值并展示
            title = intent.getStringExtra("title");
            content = intent.getStringExtra("content");
            Title.setText(title);
            Content.setText(content);
        }
    }
}
