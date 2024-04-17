package com.example.myapplication.News;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Doc_shouye;
import com.example.myapplication.MySQliteOpenHelper;
import com.example.myapplication.Pat_shouye;
import com.example.myapplication.R;

public class CreateNews extends AppCompatActivity {
    EditText settitle,setcontent;
    Button createnews;
    String newstitle,newscontent;
    private MySQliteOpenHelper dbHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MySQliteOpenHelper(this, "Docinfo.db",null,3);
        setContentView(R.layout.create_news);
        settitle = findViewById(R.id.et_create_newstitle);
        setcontent = findViewById(R.id.et_create_newscontent);
        //创建文章按钮事件
        createnews = findViewById(R.id.btn_create_news);
        createnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newstitle = settitle.getText().toString();
                newscontent = setcontent.getText().toString();
                if(newstitle.equals("")){
                    Toast.makeText(view.getContext(),"标题为空！",Toast.LENGTH_SHORT).show();
                }
                else if (newscontent.equals("")){
                    Toast.makeText(view.getContext(),"内容为空！",Toast.LENGTH_SHORT).show();
                }
                else {
                    Createnews();
                }
            }
        });

    }

    private void Createnews() {
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",newstitle);
        cv.put("content",newscontent);
        db.insert("News",null,cv);
        Toast.makeText(this,"创建成功！",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, Doc_shouye.class));
    }
}
