package com.example.myapplication.docfragment;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Doc_shouye;
import com.example.myapplication.Login_doc;
import com.example.myapplication.MainActivity;
import com.example.myapplication.MySQliteOpenHelper;
import com.example.myapplication.R;
import com.example.myapplication.Register_doc;

import org.w3c.dom.Text;

//医生端创建项目界面
public class DocCreateFragment extends DocBaseFragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_create, container, false);
    }
    private MySQliteOpenHelper dbHelper;
    EditText createname,createage;
    TextView showcreatedate;
    Button selectcreatedate,createitem;
    Spinner sex,doctername,itemname,charge,inform;
    String str_name,getsex,getdocname,getitemname,getchargesitu,getinfo,strselectcreatedate;
    Integer age;
    boolean ifcharge=false,ifagree=false;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        dbHelper = new MySQliteOpenHelper(getActivity(),"Docinfo.db",null,3);
        super.onActivityCreated(savedInstanceState);
        createname = getActivity().findViewById(R.id.et_create_name);
        createage = getActivity().findViewById(R.id.et_create_age);
        sex = getActivity().findViewById(R.id.spinner_sex);
        doctername = getActivity().findViewById(R.id.spinner_docname);
        itemname = getActivity().findViewById(R.id.spinner_itemname);
        charge = getActivity().findViewById(R.id.spinner_chargesitu);
        inform = getActivity().findViewById(R.id.spinner_informletter);
        showcreatedate = getActivity().findViewById(R.id.tv_startdate);
        selectcreatedate = getActivity().findViewById(R.id.btn_selectdate);
        createitem = getActivity().findViewById(R.id.btn_createItem);
        selectcreatedate.setOnClickListener(this);
//        strselectcreatedate = showcreatedate.getText().toString();

//        Integer int_age = Integer.parseInt(createage.getEditableText().toString());
//        String str_sex = sex.getSelectedItem().toString();
//        String docname = doctername.getSelectedItem().toString();
//        String itname = itemname.getSelectedItem().toString();
//        String chargesitu = charge.getSelectedItem().toString();
//        String informlet = inform.getSelectedItem().toString();


//        -----------------------setOnItemSelectedListener()实时监听性别
            sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getsex=DocCreateFragment.this.getResources().getStringArray(R.array.sex)[i];
                Log.d("DocCreateFragment","性别是"+getsex);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"未选择性别",Toast.LENGTH_SHORT).show();
            }
        });

//         -----------------------setOnItemSelectedListener()实时监听医生名
        doctername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getdocname=DocCreateFragment.this.getResources().getStringArray(R.array.docname)[i];
                Log.d("DocCreateFragment","医生名"+getdocname);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"未选择医生",Toast.LENGTH_SHORT).show();
            }
        });

//        -----------------------setOnItemSelectedListener()实时监听项目名称
        itemname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getitemname=DocCreateFragment.this.getResources().getStringArray(R.array.item)[i];
                Log.d("DocCreateFragment","项目名是"+getitemname);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"未选择项目",Toast.LENGTH_SHORT).show();
            }
        });


//        -----------------------setOnItemSelectedListener()实时监听缴费情况
        charge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getchargesitu=DocCreateFragment.this.getResources().getStringArray(R.array.charge)[i];
                if (i == 1){
                    ifcharge = true;
                }
                Log.d("DocCreateFragment","缴费情况是"+getchargesitu);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"未选择项目",Toast.LENGTH_SHORT).show();
            }
        });


//        -----------------------setOnItemSelectedListener()实时监听知情同意书
        inform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getinfo=DocCreateFragment.this.getResources().getStringArray(R.array.inform)[i];
                if (i == 2){
                    ifagree = true;
                }
                Log.d("DocCreateFragment","知情情况是"+getinfo);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"未选择项目",Toast.LENGTH_SHORT).show();
            }
        });

//        -----------------------创建项目的button响应事件
        createitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入的名字及年龄信息
                str_name = createname.getText().toString();
                age = Integer.parseInt(createage.getText().toString());
                Log.d("DocCreateFragment",str_name+age+getsex+getdocname+getitemname+getchargesitu+strselectcreatedate);
                Log.d("DocCreateFragment",strselectcreatedate);
                createitemtable();
            }
        });
//        DatePickerDialog.OnDateSetListener startdatelis = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                mselectcreatedate.setText(String.valueOf(i)+"年"+String.valueOf(i1+1)+"月"+String.valueOf(i2)+"日");
//            }
//        };
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_selectdate){
            DatePickerDialog createdate = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    showcreatedate.setText(String.valueOf(i)+"年"+String.valueOf(i1+1)+"月"+String.valueOf(i2)+"日");
                    strselectcreatedate = showcreatedate.getText().toString();
                }
            }, 2023, 2, 2);
            createdate.show();
            Log.d("DocCreateFragment",showcreatedate.getText().toString());
        }
    }

//        -----------------------向数据库中新建item表（插入相对应的值）
    public void createitemtable() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues Item = new ContentValues();
        Item.put("name",str_name);
        Item.put("age",age);
        Item.put("sex",getsex);
        Item.put("itemname",getitemname);
        Item.put("doc",getdocname);
        Item.put("starttime",strselectcreatedate);
        Item.put("charge",ifcharge);
        Item.put("knowsitu",ifagree);
        db.insert("Item",null,Item);
        Toast.makeText(getActivity(),"创建成功！",Toast.LENGTH_SHORT).show();
        getActivity().finish();
        startActivity(new Intent(getActivity(), Doc_shouye.class));

    }

}

