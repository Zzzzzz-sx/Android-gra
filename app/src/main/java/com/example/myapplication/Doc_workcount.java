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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;

public class Doc_workcount extends AppCompatActivity {
    Integer charge,chargesum,itemindex;
    TextView showstarttime,showendtime,showworkcount;
    Spinner showitemname;
    Button selectstarttime,selectendtime,getworkcount;
    String getstarttime,getendtime,nameget,getitemname;
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
        showitemname = findViewById(R.id.spinner_workcount_itemname);
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
                },2023, 1, 1);
                enddate.show();
                Log.d("Doc_workcount",showendtime.getText().toString());
            }
        });
        showitemname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getitemname = Doc_workcount.this.getResources().getStringArray(R.array.item)[i];
//                Log.d("Doc_workcount","查询索引i："+itemindex);

                Log.d("Doc_workcount","查询项目名"+getitemname);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Doc_workcount.this,"未选择项目",Toast.LENGTH_SHORT).show();
            }
        });
        getworkcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chargesum = 0;
                Log.d("Doc_workcount","医生为"+nameget);
                SQLiteDatabase db =dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Item",null,"starttime BETWEEN ? AND ? AND doc = ? AND itemname = ?",new String[]{getstarttime,getendtime,nameget,getitemname},null,null,null);
                Log.d("Doc_workcount","cursor____"+cursor.moveToFirst());
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
