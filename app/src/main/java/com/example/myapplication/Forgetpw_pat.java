package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

import com.example.myapplication.Login.Login_pat;
import com.example.myapplication.SQliteOpenHelper.PatSQliteOpenHelper;

public class Forgetpw_pat extends AppCompatActivity {
    PatSQliteOpenHelper dbHelper;
    EditText et_phonenum,et_newpw,et_confirmpw;
    Button btn_backtologin,btn_confirm;
    String phonenum,newpw,confirmpw;
    boolean samepw;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_forgetpw);
        et_phonenum = findViewById(R.id.et_pat_phonenum);
        et_newpw = findViewById(R.id.et_pat_newpassword);
        et_confirmpw = findViewById(R.id.et_pat_conpassword);
        dbHelper = new PatSQliteOpenHelper(this,"Patinfo.db",null,1);
        btn_backtologin = findViewById(R.id.btn_pat_forgetpw_to_login);
        btn_confirm = findViewById(R.id.btn_pat_confirmchangepw);
        btn_backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(view.getContext(), Login_pat.class));
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonenum = et_phonenum.getText().toString();
                Log.d("Forgetpw_pat","输入手机号为1："+phonenum);
                newpw = et_newpw.getText().toString();
                confirmpw = et_confirmpw.getText().toString();
                samepw = newpw.equals(confirmpw);
                if (phonenum.length()==0){
                    Toast.makeText(view.getContext(),"手机号为空！",Toast.LENGTH_SHORT).show();
                }
                else if (newpw.length()==0){
                    Toast.makeText(view.getContext(),"新密码为空！",Toast.LENGTH_SHORT).show();
                }
                else if (confirmpw.length()==0){
                    Toast.makeText(view.getContext(),"确认密码为空！",Toast.LENGTH_SHORT).show();
                }
                else if (samepw==false){
                    Toast.makeText(view.getContext(),"密码不一致！",Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("Forgetpw_pat","输入手机号为2："+phonenum);
                    findpat(phonenum);
                    Toast.makeText(view.getContext(),"重置密码成功！",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(view.getContext(), Login_pat.class));
                }

            }
        });
    }
    private void findpat(String phonenumber) {
        Log.d("Forgetpw_pat","输入手机号为："+phonenumber);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("patient",null,"pat_phonenum = ?",new String[]{phonenumber},null,null,null);
        if (cursor.moveToFirst()){
            do {
                Log.d("Forgetpw_pat","cursor success");
                    ContentValues cv = new ContentValues();
                    cv.put("pat_login_pw",newpw);
                    db.update("patient",cv,"pat_phonenum = ?",new String[]{phonenumber});
            }while(cursor.moveToNext());
        }
        cursor.close();

    }
}
