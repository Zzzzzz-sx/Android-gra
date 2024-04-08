package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
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

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class Register_doc extends AppCompatActivity {
    private MySQliteOpenHelper dbHelper;
    Button docregister;
    EditText registername,registerpw;
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_register);
        //数据库可视化软件接口
        SQLiteStudioService.instance().start(this);

        registername = findViewById(R.id.et_reg_name);
        registerpw = findViewById(R.id.et_reg_password);
        docregister = findViewById(R.id.btn_doc_register);
        dbHelper = new MySQliteOpenHelper(this, "Docinfo.db",null,3);
        docregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String name = registername.getText().toString();
                String password = registerpw.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("docname",name);
                cv.put("docpw",password);
                boolean suc = regsuc(name);
                Log.d("Register_doc","show"+suc);
                if(!suc){
                    db.insert("docter",null,cv);
                    Toast.makeText(Register_doc.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Register_doc.this,Login_doc.class));
                }
                else if(name==null||password==null){
                    Toast.makeText(Register_doc.this,"用户名或密码为空，请重新输入",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent =new Intent(Register_doc.this,Register_doc.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Register_doc.this,"用户名已存在！请重新注册！",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent =new Intent(Register_doc.this,Register_doc.class);
                    startActivity(intent);
                }

            }
        });
    }
    public boolean regsuc(String username){
        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
        boolean result = false;
//        Log.d("Register_doc","success1");
        Cursor cursor = db1.query("docter",null,null,null,null,null,null);
//        Log.d("Register_doc","success1"+cursor.moveToFirst());
        if(cursor.moveToFirst()){
            do {
//                Log.d("Register_doc","success2");
                String username1 = cursor.getString(cursor.getColumnIndexOrThrow("docname"));
//                Log.d("Register_doc","username1="+username1);
                result = username1.equals(username);
//                Log.d("Register_doc","equal="+result);
//                Log.d("Register_doc","next="+cursor.moveToNext());
                if(result){
                    return result;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}
