package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.SQliteOpenHelper.PatSQliteOpenHelper;

public class Pat_Personalinfo extends AppCompatActivity {
    EditText inputname,inputid,inputphonenum;
    Button saveinfo;
    String setpatname,setpatpersonalid,setpatphonenum,getpatname,getpatpersonalid,getpatphonenum;
    String loginname;
    private PatSQliteOpenHelper dbHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        dbHelper = new PatSQliteOpenHelper(this, "Patinfo.db",null,1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_personal_information);
        inputname = findViewById(R.id.et_pat_name);
        inputid = findViewById(R.id.et_pat_personalid);
        inputphonenum = findViewById(R.id.et_pat_mobilenum);
        saveinfo = findViewById(R.id.btn_pat_save);
        Intent intent = getIntent();
        if(intent != null){
            loginname = intent.getStringExtra("login_name");
            Log.d("Pat_Personalinfo","登录用户名："+loginname);
        }
        getinfo();
        saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idlength = inputid.getText().length();
                int phonelength = inputphonenum.getText().length();
                setpatname = inputname.getText().toString();
                setpatpersonalid = inputid.getText().toString();
                setpatphonenum = inputphonenum.getText().toString();
                Log.d("Pat_Personalinfo","长度为："+inputid.getText().length());
                if(idlength!=18){
                    Toast.makeText(view.getContext(),"身份证填写有误，请重新填写！",Toast.LENGTH_SHORT).show();
                }
                else if (phonelength!=11){
                    Toast.makeText(view.getContext(),"手机号填写有误，请重新填写！",Toast.LENGTH_SHORT).show();
                }
                else if (setpatname==null){
                    Toast.makeText(view.getContext(),"姓名填写有误，请重新填写！",Toast.LENGTH_SHORT).show();
                }else {
                    save();
                }


            }
        });
    }

    private void getinfo() {
        dbHelper = new PatSQliteOpenHelper(this,"Patinfo.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("patient",null,"pat_login_name=?",new String[]{loginname},null,null,null);
        if (cursor.moveToFirst()){
            do {
                getpatname = cursor.getString(cursor.getColumnIndexOrThrow("pat_name"));
                getpatpersonalid = cursor.getString(cursor.getColumnIndexOrThrow("pat_personalid"));
                Log.d("Pat_Personalinfo","身份证"+getpatpersonalid);
                getpatphonenum = cursor.getString(cursor.getColumnIndexOrThrow("pat_phonenum"));
            }while (cursor.moveToNext());
        }
        cursor.close();
        inputname.setText(getpatname);
        inputid.setText(getpatpersonalid);
        inputphonenum.setText(getpatphonenum);
    }

    private void save() {
        //读取数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues Patinfo = new ContentValues();
        //设置字段
        Patinfo.put("pat_name",setpatname);
        Patinfo.put("pat_personalid",setpatpersonalid);
        Patinfo.put("pat_phonenum",setpatphonenum);
        //插入数据库中
        db.update("patient",Patinfo,"pat_login_name=?",new String[]{loginname});
        Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, Pat_shouye.class));
    }

}
