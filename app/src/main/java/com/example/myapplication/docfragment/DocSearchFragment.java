package com.example.myapplication.docfragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Doc_shouye;
import com.example.myapplication.MySQliteOpenHelper;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
//医生端搜索项目历史界面

public class DocSearchFragment extends DocBaseFragment {
    private List<Historyproject> Hisproject = new ArrayList<>();
    private MySQliteOpenHelper dbHelper;
    HistoryRecycleAdapter historyAdapter;
    RecyclerView recyclerView;
    String charge,knowsitu,searchcontent;
    EditText et_search;
    Button btn_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_search, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //--------------------------展示历史项目
        showhistory();
        //recyclerview配置 布局管理 适配器
        recyclerView = getActivity().findViewById(R.id.rv_doc_history);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        historyAdapter = new HistoryRecycleAdapter(Hisproject);
        recyclerView.setAdapter(historyAdapter);
        //recyclerview的分隔下划线设置
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.BLACK));
        recyclerView.addItemDecoration(divider);

        //--------------------------搜索功能实现
        et_search = getActivity().findViewById(R.id.et_item_search);
        btn_search = getActivity().findViewById(R.id.btn_item_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //------------------------点击事件 点击后在原列表顶端显示（待定------>跳转到新的页面进行recyclerview的展示？）
                searchcontent = et_search.getText().toString();
                Log.d("DocSearchFragment","搜索内容"+searchcontent);
                dbHelper = new MySQliteOpenHelper(getActivity(),"Docinfo.db",null,3);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Item",null,"name = ?",new String[]{searchcontent},null,null,null);
                if (cursor.getCount()==0){
                    cursor.close();
                    Toast.makeText(getActivity(),"查询失败！姓名不存在！",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), Doc_shouye.class));
                }
                if(cursor.moveToFirst()){
                    do{
                        Log.d("DocSearchFragment","项目数"+cursor.getCount());
                        Log.d("DocSearchFragment","cursor successful");
                        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        Integer age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                        String sex = cursor.getString(cursor.getColumnIndexOrThrow("sex"));
                        String itemname = cursor.getString(cursor.getColumnIndexOrThrow("itemname"));
                        String doc = cursor.getString(cursor.getColumnIndexOrThrow("doc"));
                        String starttime = cursor.getString(cursor.getColumnIndexOrThrow("starttime"));
                        if(cursor.getInt(cursor.getColumnIndexOrThrow("charge"))==1){
                            charge = "已缴费";
                        }
                        else if(cursor.getInt(cursor.getColumnIndexOrThrow("charge"))==0){
                            charge = "未缴费";
                        }
                        if(cursor.getInt(cursor.getColumnIndexOrThrow("knowsitu"))==1){
                            knowsitu = "已了解并签署知情通知书";
                        }
                        else if(cursor.getInt(cursor.getColumnIndexOrThrow("charge"))==0){
                            charge = "未了解";
                        }
                        Historyproject history=new Historyproject(id,name,age,sex,itemname,doc,starttime,charge,knowsitu);
                        Hisproject.add(history);
                        historyAdapter.notifyItemInserted((cursor.getCount()-1));
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }

    private void showhistory(){
        dbHelper = new MySQliteOpenHelper(getActivity(),"Docinfo.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Item",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                 Log.d("DocSearchFragment","cursor successful");
                 Integer id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                 String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                 Integer age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                 String sex = cursor.getString(cursor.getColumnIndexOrThrow("sex"));
                 String itemname = cursor.getString(cursor.getColumnIndexOrThrow("itemname"));
                 String doc = cursor.getString(cursor.getColumnIndexOrThrow("doc"));
                 String starttime = cursor.getString(cursor.getColumnIndexOrThrow("starttime"));
                 if(cursor.getInt(cursor.getColumnIndexOrThrow("charge"))==1){
                     charge = "已缴费";
                 }
                 else if(cursor.getInt(cursor.getColumnIndexOrThrow("charge"))==0){
                     charge = "未缴费";
                 }
                if(cursor.getInt(cursor.getColumnIndexOrThrow("knowsitu"))==1){
                     knowsitu = "已了解并签署知情通知书";
                }
                else if(cursor.getInt(cursor.getColumnIndexOrThrow("charge"))==0){
                     charge = "未了解";
                }
                 Historyproject history=new Historyproject(id,name,age,sex,itemname,doc,starttime,charge,knowsitu);
                 Hisproject.add(history);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

}