package com.example.myapplication.docfragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Doc_shouye;
import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;
import com.example.myapplication.R;

import java.util.Date;

//医生端创建项目界面
public class DocCreateFragment extends DocBaseFragment implements View.OnClickListener{
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_create, container, false);
    }
    private MySQliteOpenHelper dbHelper;
    EditText createname,createage,createpersonalid;
    TextView showcreatedate;
    Button selectcreatedate,createitem,uploadimage;
    Spinner sex,doctername,itemname,charge,inform;
    String getpatname,getsex,getdocname,getitemname,getchargesitu,getinfo,strselectcreatedate,getpersonalid;
    Integer getage,chargenum;
    boolean ifcharge=false,ifagree=false;
    ImageView showimg1,showimg2,showimg3;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.d("DocCreateFragment","image1="+showimg1.getVisibility());
            // 将选择的图片展示在下方的 ImageView 中
            if (showimg1.getVisibility() == View.GONE) {
                showimg1.setImageBitmap(imageBitmap);
                showimg1.setVisibility(View.VISIBLE);
            } else if (showimg2.getVisibility() == View.GONE) {
                showimg2.setImageBitmap(imageBitmap);
                showimg2.setVisibility(View.VISIBLE);
            } else if (showimg3.getVisibility() == View.GONE) {
                showimg3.setImageBitmap(imageBitmap);
                showimg3.setVisibility(View.VISIBLE);
            } else {
                // 最多展示三张图片，可以添加逻辑或者提示
                Toast.makeText(getActivity(), "最多只能上传三张图片", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // 请求权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户重新授予了相机权限，执行相应操作
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
            } else {
                // 用户未授予相机权限，可以展示一个提示或者再次解释为何需要相机权限
                Toast.makeText(getActivity(), "需要相机权限才能继续操作", Toast.LENGTH_SHORT).show();
            }
        }
    }
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
        createpersonalid = getActivity().findViewById(R.id.et_create_personalid);
        //找到上传图片按钮控件以及展示图片的ImageView控件
        uploadimage = getActivity().findViewById(R.id.upload_button);
        showimg1 = getActivity().findViewById(R.id.img_upload_1);
        showimg2 = getActivity().findViewById(R.id.img_upload_2);
        showimg3 = getActivity().findViewById(R.id.img_upload_3);
        //时间选择器的按钮监听
        selectcreatedate.setOnClickListener(this);
//        uploadimage.setOnClickListener(this);
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
                switch(i){
                    case 1:
                        chargenum = 1000;
                        break;
                    case 2:
                        chargenum = 2000;
                        break;
                    case 3:
                        chargenum = 3000;
                        break;
                    case 4:
                        chargenum = 4000;
                        break;
                    case 5:
                        chargenum = 5000;
                        break;
                }
                Log.d("DocCreateFragment","项目名是"+getitemname);
                Log.d("DocCreateFragment","费用是"+chargenum);
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
//        -----------------------上传图片的button响应事件
        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // 相机权限被撤销，请求相机权限
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION);
                } else {
                    // 相机权限已授予，执行相应操作
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
                }

//                //打开相机
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
                //从手机相册中获取图片需要动态申请权限
//                if(ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(this,
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},ALBUM_CODE);
//                }else{
//                    //如果已经获取权限，那么就直接拿
//                    takePhoto();
//                }
            }
        });

//        -----------------------创建项目的button响应事件
        createitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入的名字及年龄信息
                getpersonalid = createpersonalid.getText().toString();
                getpatname = createname.getText().toString();
                getage = Integer.parseInt(createage.getText().toString());
                Log.d("DocCreateFragment",getpatname+getage+getsex+getdocname+getitemname+getchargesitu+strselectcreatedate);
                Log.d("DocCreateFragment",strselectcreatedate);
                if (getpatname.length()==0){
                    Toast.makeText(getActivity(), "姓名为空！请重新输入", Toast.LENGTH_SHORT).show();
                }
                else if (createage.getText().toString().length()==0){
                    Toast.makeText(getActivity(), "年龄为空！请重新输入", Toast.LENGTH_SHORT).show();
                }
                else if (getpersonalid.length() == 0) {
                    Toast.makeText(getActivity(), "身份证号为空！请重新输入", Toast.LENGTH_SHORT).show();
                }
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
                    showcreatedate.setText(String.valueOf(i)+"-"+String.valueOf(i1+1)+"-"+String.valueOf(i2));
                    strselectcreatedate = showcreatedate.getText().toString();
                }
            }, 2023, 2, 2);
            createdate.show();
            Log.d("DocCreateFragment",showcreatedate.getText().toString());
        }
    }

//        -----------------------向数据库中新建item表（插入相对应的值）
    public void createitemtable() {
        //读取数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues Item = new ContentValues();
        //设置字段
        Item.put("name",getpatname);
        Item.put("age",getage);
        Item.put("sex",getsex);
        Item.put("itemname",getitemname);
        Item.put("doc",getdocname);
        Item.put("starttime",strselectcreatedate);
        Item.put("charge",ifcharge);
        Item.put("chargenum",chargenum);
        Item.put("knowsitu",ifagree);
        Item.put("pat_personalid",getpersonalid);
        //插入数据库中
        db.insert("Item",null,Item);
        Toast.makeText(getActivity(),"创建成功！",Toast.LENGTH_SHORT).show();
        getActivity().finish();
        startActivity(new Intent(getActivity(), Doc_shouye.class));
    }
    //权限申请回调

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case ALBUM_CODE:
//                //获取照片
//                takePhoto();
//                break;
//        }
//    }
}

