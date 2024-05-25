package com.example.myapplication.Login;

import android.annotation.SuppressLint;
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

import com.example.myapplication.Forgetpw_pat;
import com.example.myapplication.Pat_Personalinfo;
import com.example.myapplication.Pat_shouye;
import com.example.myapplication.R;
import com.example.myapplication.Register.Register_pat;
import com.example.myapplication.SQliteOpenHelper.PatSQliteOpenHelper;

public class Login_pat extends AppCompatActivity {
    private PatSQliteOpenHelper dbHelper;
    EditText login_name,login_password;
    Button btnpatlogin,btnpatreg,btnpatforget;
    String getpersonalid;
    String getpatname;
    int getpatid;
    boolean everlogin;
    String getpatphone;

    @SuppressLint("MissingInflatedId")
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_login);

        login_name = findViewById(R.id.et_login_pat_name);
        login_password = findViewById(R.id.et_login_pat_password);
        btnpatlogin = findViewById(R.id.btn_pat_login);
        btnpatreg = findViewById(R.id.btn_pat_to_reg);
        dbHelper = new PatSQliteOpenHelper(this, "Patinfo.db",null,1);
        //患者忘记密码
        btnpatforget = findViewById(R.id.btn_pat_forget);
        btnpatforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //跳转到患者忘记密码界面
                Intent intent = new Intent(Login_pat.this, Forgetpw_pat.class);
                startActivity(intent);
            }
        });
        //患者登录按钮
        btnpatlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String logname = login_name.getText().toString();
                String logpw = login_password.getText().toString();
                boolean success = logsuc(logname,logpw,db);
                if (success){
                    Toast.makeText(Login_pat.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    finish();
                    if (everlogin){
                        //跳转到患者首页界面
                        Intent intent = new Intent(Login_pat.this, Pat_shouye.class);
//                    startActivity(intent);
                        //传出用户名
                        intent.putExtra("login_name",logname);
                        intent.putExtra("patid",getpatid);
                        intent.putExtra("patname",getpatname);
                        intent.putExtra("patpersonalid",getpersonalid);
                        view.getContext().startActivity(intent);
                    }
                    else{
                        //跳转到患者首页界面
                        Intent intent = new Intent(Login_pat.this, Pat_Personalinfo.class);
                        //传出用户名
                        intent.putExtra("login_name",logname);
                        intent.putExtra("patid",getpatid);
                        view.getContext().startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(Login_pat.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent =new Intent(Login_pat.this, Login_pat.class);
                    startActivity(intent);
                }
            }
        });
        //患者注册按钮
        btnpatreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_pat.this, Register_pat.class));
            }
        });
    }

    private boolean logsuc(String logname, String logpw, SQLiteDatabase db) {
        boolean result = false;
        Cursor cursor = db.query("patient",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                String username1 = cursor.getString(cursor.getColumnIndexOrThrow("pat_login_name"));
                String userpw1 = cursor.getString(cursor.getColumnIndexOrThrow("pat_login_pw"));
                getpatname = cursor.getString(cursor.getColumnIndexOrThrow("pat_name"));
                getpatid = cursor.getInt(cursor.getColumnIndexOrThrow("id"+""));
                getpersonalid = cursor.getString(cursor.getColumnIndexOrThrow("pat_personalid"));
                getpatphone = cursor.getString(cursor.getColumnIndexOrThrow("pat_phonenum"));
                result = username1.equals(logname)&&userpw1.equals(logpw);
                if (getpatname.length()!=0 && getpersonalid.length()!=0&&getpatphone.length()!=0){
                    everlogin = true;
                }
                if(result){
                    return result;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}
