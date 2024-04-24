package com.example.myapplication.News;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Doc_shouye;
import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;
import com.example.myapplication.R;

public class EditNews extends AppCompatActivity {
    EditText Title,Content;
    String title,content;
    Button deletenews,updatenews;
    String edittitle,editcontent;
    int id;
    private MySQliteOpenHelper dbHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MySQliteOpenHelper(this, "Docinfo.db",null,3);
        setContentView(R.layout.edit_news);
        Title = this.findViewById(R.id.tv_news_title);
        Content = this.findViewById(R.id.tv_news_content);
        deletenews = this.findViewById(R.id.btn_delete_news);
        updatenews = this.findViewById(R.id.btn_undate_news);
        //展示新闻内容
        newsshow();
        //点击删除对应news
        deletenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNews();
            }
        });
        //点击修改对应news
        updatenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittitle=Title.getText().toString();
                editcontent=Content.getText().toString();
                if(edittitle.equals("")){
                    Toast.makeText(view.getContext(),"标题为空！",Toast.LENGTH_SHORT).show();
                }
                else if (editcontent.equals("")){
                    Toast.makeText(view.getContext(),"内容为空！",Toast.LENGTH_SHORT).show();
                }
                else {
                    savenews();
                }
            }
        });
    }

    private void deleteNews() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("News","id=?",new String[]{String.valueOf(id)});
        Toast.makeText(this,"删除成功！",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, Doc_shouye.class));
    }

    //保存修改的函数
    private void savenews() {
        //读取数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues news = new ContentValues();
        news.put("title",edittitle);
        news.put("content",editcontent);
        //更新数据库 根据News表的id来识别（传参）
        db.update("News",news,"id=?",new String[]{String.valueOf(id)});
        Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, Doc_shouye.class));
    }


    public void newsshow(){
        Intent intent = getIntent();
        if(intent != null){
            //接收数值并展示
            title = intent.getStringExtra("title");
            content = intent.getStringExtra("content");
            id = intent.getIntExtra("newsid",0);
            Log.d("ShowNews","newsid"+id);
            Title.setText(title);
            Content.setText(content);
        }
    }
}
