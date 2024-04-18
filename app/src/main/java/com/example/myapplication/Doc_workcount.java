package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;

public class Doc_workcount extends AppCompatActivity {
    Integer charge,chargesum;
    TextView showstarttime,showendtime,showworkcount;
    Button selectstarttime,selectendtime,getworkcount;
    String getstarttime,getendtime,nameget;
    private MySQliteOpenHelper dbHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_workcount);
        charge = 0;
        chargesum = 0;
        dbHelper = new MySQliteOpenHelper(this, "Docinfo.db",null,3);
        showstarttime = findViewById(R.id.tv_select_startdate);
        showendtime = findViewById(R.id.tv_select_enddate);
        showworkcount = findViewById(R.id.tv_chargecount);
        selectstarttime = findViewById(R.id.btn_select_startdate);
        selectendtime = findViewById(R.id.btn_select_enddate);
        getworkcount = findViewById(R.id.btn_getcount);
        Intent intent = getIntent();
        if(intent != null){
            nameget = intent.getStringExtra("name");
        }
        selectstarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog startdate = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        showstarttime.setText(i+"-"+String.valueOf(i1+1)+"-"+String.valueOf(i2));
                        getstarttime = showstarttime.getText().toString();
                    }
                },2023, 2, 2);
                startdate.show();
                Log.d("Doc_workcount",showstarttime.getText().toString());
            }
        });
        selectendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog enddate = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        showendtime.setText(i+"-"+String.valueOf(i1+1)+"-"+String.valueOf(i2));
                        getendtime = showendtime.getText().toString();
                    }
                },2023, 2, 2);
                enddate.show();
                Log.d("Doc_workcount",showendtime.getText().toString());
            }
        });
        getworkcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Doc_workcount","医生为"+nameget);
                SQLiteDatabase db =dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Item",null,"starttime BETWEEN ? AND ? AND doc = ?",new String[]{getstarttime,getendtime,nameget},null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        charge = cursor.getInt(cursor.getColumnIndexOrThrow("chargenum"));
                        Log.d("Doc_workcount","费用为"+charge);
                        chargesum +=charge;
                        Log.d("Doc_workcount","费用2为"+chargesum);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                showworkcount.setText(chargesum.toString());
            }
        });
    }
}
