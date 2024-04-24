package com.example.myapplication.News;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;

import org.w3c.dom.Text;

public class ShowNews extends AppCompatActivity {
    TextView showtitle,showcontent;
    private MySQliteOpenHelper dbHelper;
    String Title,Content;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MySQliteOpenHelper(this, "Docinfo.db",null,3);
        setContentView(R.layout.show_news);
        showtitle = findViewById(R.id.tv_show_newstitle);
        showcontent = findViewById(R.id.tv_show_newscontent);
        shownews();
    }

    private void shownews() {
        Intent intent = getIntent();
        if(intent != null){
            //接收数值并展示
            Title = intent.getStringExtra("title");
            Content = intent.getStringExtra("content");
            showtitle.setText(Title);
            showcontent.setText(Content);
        }
    }
}
