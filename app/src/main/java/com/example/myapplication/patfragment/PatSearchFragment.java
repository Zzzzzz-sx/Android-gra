package com.example.myapplication.patfragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MySQliteOpenHelper;
import com.example.myapplication.R;
import com.example.myapplication.docfragment.HistoryRecycleAdapter;
import com.example.myapplication.docfragment.Historyproject;

import java.util.ArrayList;
import java.util.List;

public class PatSearchFragment extends PatBaseFragment{
    private List<Historyproject> Hisproject = new ArrayList<>();
    private MySQliteOpenHelper dbHelper;
    HistoryRecycleAdapter historyAdapter;
    RecyclerView recyclerView;
    String charge,knowsitu,searchcontent;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //--------------------------展示历史项目
        showhistory();
        //recyclerview配置 布局管理 适配器
        recyclerView = getActivity().findViewById(R.id.rv_pat_history);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        historyAdapter = new HistoryRecycleAdapter(Hisproject);
        recyclerView.setAdapter(historyAdapter);
        //recyclerview的分隔下划线设置
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.BLACK));
        recyclerView.addItemDecoration(divider);
    }

    private void showhistory() {
        dbHelper = new MySQliteOpenHelper(getActivity(),"Docinfo.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Item",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                //此处需要根据患者名or身份证or联系方式来进行筛选，显示一个患者的信息
                Log.d("PatSearchFragment","cursor successful");
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
