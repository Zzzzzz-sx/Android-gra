package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.service.quicksettings.Tile;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowNews extends AppCompatActivity {
    TextView Title,Content;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_news);
        Title = this.findViewById(R.id.tv_news_title);
        Content = this.findViewById(R.id.tv_news_content);
        newsshow();
    }
    public void newsshow(){
        Intent intent = getIntent();
        if(intent != null){
            //接收数值并展示
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            Title.setText(title);
            Content.setText(content);
        }
    }
}
