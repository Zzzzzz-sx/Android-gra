package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.SQliteOpenHelper.PatSQliteOpenHelper;

public class Pat_resetpw extends AppCompatActivity {
    EditText et_origin_pw,et_new_pw,et_confirm_pw;
    String loginname,originpw,newpw,confirmpw;
    Button btn_reset_pw;
    Boolean samepw;
    PatSQliteOpenHelper dbHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_resetpw);
        et_origin_pw = findViewById(R.id.et_original_pw);
        et_new_pw = findViewById(R.id.et_new_pw);
        et_confirm_pw = findViewById(R.id.et_confirm_pw);
        btn_reset_pw = findViewById(R.id.btn_pat_resetpw);
        dbHelper = new PatSQliteOpenHelper(this,"Patinfo.db",null,1);
        Intent intent = getIntent();
        if(intent != null){
            loginname = intent.getStringExtra("login_name");
            Log.d("Pat_resetpw","登录用户名："+loginname);
        }
        btn_reset_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                originpw = et_origin_pw.getText().toString();
                newpw = et_new_pw.getText().toString();
                confirmpw = et_confirm_pw.getText().toString();
                samepw = newpw.equals(confirmpw);
                Log.d("Pat_resetpw","新密码与原密码是否一致："+samepw);
                validate();
                Toast.makeText(view.getContext(),"修改成功！",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(view.getContext(), Pat_shouye.class));
            }
        });
    }
//验证原密码是否正确、新密码不为空、新密码两次输入一致、数据库中修改密码
    private void validate() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (originpw.length()!=0&&newpw.length()!=0&&confirmpw.length()!=0){
            Cursor cursor = db.query("patient",null,"pat_login_name = ?",new String[]{loginname},null,null,null);
            if (cursor.moveToFirst()){
                do {
                    String validatepw = cursor.getString(cursor.getColumnIndexOrThrow("pat_login_pw"));
                    Log.d("Pat_resetpw","原密码："+validatepw);
                    if (originpw.equals(validatepw)&&samepw){
                        ContentValues cv = new ContentValues();
                        cv.put("pat_login_pw",newpw);
                        db.update("patient",cv,"pat_login_name = ?",new String[]{loginname});

                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
        }

    }
}
