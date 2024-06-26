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

import com.example.myapplication.Login.Login_pat;
import com.example.myapplication.R;
import com.example.myapplication.SQliteOpenHelper.PatSQliteOpenHelper;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class Register_pat extends AppCompatActivity {
    private PatSQliteOpenHelper dbHelper;
    Button patregister,backtologin;
    EditText registername,registerpw;
    boolean suc;
    @SuppressLint("MissingInflatedId")
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_register);
        SQLiteStudioService.instance().start(this);

        registername = findViewById(R.id.et_reg_pat_name);
        registerpw = findViewById(R.id.et_reg_pat_password);
        patregister = findViewById(R.id.btn_pat_register);
        backtologin = findViewById(R.id.btn_pat_reg_back_login);
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent =new Intent(Register_pat.this,Login_pat.class);
                startActivity(intent);
            }
        });
        dbHelper = new PatSQliteOpenHelper(this,"Patinfo.db",null,1);
        patregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String name = registername.getText().toString();
                String password = registerpw.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("pat_login_name",name);
                cv.put("pat_login_pw",password);
                Log.d("Register_pat","show"+suc);
                Log.d("Register_pat","show---"+name.length());
                Log.d("Register_pat","show-------"+password.length());

//                if(!suc){
//                    db.insert("patient",null,cv);
//                    Toast.makeText(Register_pat.this,"注册成功！",Toast.LENGTH_SHORT).show();
//                    finish();
//                    startActivity(new Intent(Register_pat.this, Login_pat.class));
//                }
                if(name.length()==0||password.length()==0){
                    Toast.makeText(Register_pat.this,"用户名或密码为空，请重新输入",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent =new Intent(Register_pat.this,Register_pat.class);
                    startActivity(intent);
                }
                else {
                    suc = regsuc(name);
                    if (!suc){
                        db.insert("patient",null,cv);
                        Toast.makeText(Register_pat.this,"注册成功！",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Register_pat.this, Login_pat.class));
                    }
                    else if (suc){
                        Toast.makeText(Register_pat.this,"用户名已存在！请重新注册！",Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent =new Intent(Register_pat.this,Register_pat.class);
                        startActivity(intent);
                    }
                }


            }
        });
    }

    private boolean regsuc(String name) {
        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
        boolean result = false;
//        Log.d("Register_doc","success1");
        Cursor cursor = db1.query("patient",null,null,null,null,null,null);
//        Log.d("Register_doc","success1"+cursor.moveToFirst());
        if(cursor.moveToFirst()){
            do {
//                Log.d("Register_doc","success2");
                String username1 = cursor.getString(cursor.getColumnIndexOrThrow("pat_login_name"));
//                Log.d("Register_doc","username1="+username1);
                result = username1.equals(name);
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
