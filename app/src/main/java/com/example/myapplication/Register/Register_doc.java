package com.example.myapplication.Register;

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

import com.example.myapplication.Login.Login_doc;
import com.example.myapplication.R;
import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class Register_doc extends AppCompatActivity {
    private MySQliteOpenHelper dbHelper;
    Button docregister,backtologin;
    EditText registername,registerpw;
    boolean suc;
    @SuppressLint("MissingInflatedId")
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_register);
        //数据库可视化软件接口
        SQLiteStudioService.instance().start(this);

        registername = findViewById(R.id.et_reg_name);
        registerpw = findViewById(R.id.et_reg_password);
        docregister = findViewById(R.id.btn_doc_register);
        backtologin = findViewById(R.id.btn_doc_reg_back_login);
        dbHelper = new MySQliteOpenHelper(this, "Docinfo.db",null,3);
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(Register_doc.this,Login_doc.class);
                startActivity(intent);
            }
        });
        docregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String name = registername.getText().toString();
                String password = registerpw.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("docname",name);
                cv.put("docpw",password);

                Log.d("Register_doc","show"+suc);
                if(name.length()==0||password.length()==0){
                    Toast.makeText(Register_doc.this,"用户名或密码为空，请重新输入",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent =new Intent(Register_doc.this,Register_doc.class);
                    startActivity(intent);
                }
                else {
                    suc = regsuc(name);
                    if(!suc){
                        db.insert("docter",null,cv);
                        Toast.makeText(Register_doc.this,"注册成功！",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Register_doc.this, Login_doc.class));
                    }
                    else if (suc){
                        Toast.makeText(Register_doc.this,"用户名已存在！请重新注册！",Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent =new Intent(Register_doc.this,Register_doc.class);
                        startActivity(intent);
                    }
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
                String username1 = cursor.getString(cursor.getColumnIndexOrThrow("doc_login_name"));
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
