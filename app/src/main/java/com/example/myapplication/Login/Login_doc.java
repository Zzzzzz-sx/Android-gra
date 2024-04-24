package com.example.myapplication.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Doc_shouye;
import com.example.myapplication.R;
import com.example.myapplication.Register.Register_doc;
import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;


public class Login_doc extends AppCompatActivity {
    private MySQliteOpenHelper dbHelper;
    String name1;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_login);
        EditText login_name,login_password;
        Button btndoclogin,btndocreg,btndocforget;
        login_name = findViewById(R.id.et_login_doc_name);
        login_password = findViewById(R.id.et_login_doc_password);
        btndoclogin = findViewById(R.id.btn_doc_login);
        btndocreg = findViewById(R.id.btn_doc_to_reg);
        dbHelper = new MySQliteOpenHelper(this, "Docinfo.db",null,3);
        btndocreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_doc.this, Register_doc.class));
            }
        });
        btndoclogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String logname = login_name.getText().toString();
                String logpw = login_password.getText().toString();
                boolean success = logsuc(logname,logpw,db);
                if (success){
                    Toast.makeText(Login_doc.this,"登录成功！",Toast.LENGTH_SHORT).show();
//                    view.getContext().startActivity(new Intent(Login_doc.this,Doc_shouye.class));
//                    DocMineFragment fragment = DocMineFragment.newInstance(logname);
                    finish();
                    Intent intent =new Intent(Login_doc.this, Doc_shouye.class);
                    intent.putExtra("name",name1);
                    view.getContext().startActivity(intent);
//                    startActivity(intent);

                }
                else{
                    Toast.makeText(Login_doc.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent =new Intent(Login_doc.this, Login_doc.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean logsuc(String username,String userpw,SQLiteDatabase database){
//        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
        boolean result = false;
//        Log.d("Register_doc","success1");
        Cursor cursor = database.query("docter",null,null,null,null,null,null);
//        Log.d("Register_doc","success1"+cursor.moveToFirst());
        if(cursor.moveToFirst()){
            do {
//                Log.d("Register_doc","success2");
                String username1 = cursor.getString(cursor.getColumnIndexOrThrow("doc_login_name"));
                String userpw1 = cursor.getString(cursor.getColumnIndexOrThrow("doc_login_pw"));
                name1 = cursor.getString(cursor.getColumnIndexOrThrow("docter_name"));
//                Log.d("Register_doc","username1="+username1);
                result = username1.equals(username)&&userpw1.equals(userpw);
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
