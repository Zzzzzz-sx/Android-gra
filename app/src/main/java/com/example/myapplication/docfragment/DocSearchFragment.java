package com.example.myapplication.docfragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    String charge,knowsitu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_search, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showhistory();
        recyclerView = getActivity().findViewById(R.id.rv_doc_history);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        historyAdapter = new HistoryRecycleAdapter(Hisproject);
        recyclerView.setAdapter(historyAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.BLACK));
        recyclerView.addItemDecoration(divider);


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
//                 Integer charge = cursor.getInt(cursor.getColumnIndexOrThrow("charge"));
//                 Integer knowsitu = cursor.getInt(cursor.getColumnIndexOrThrow("knowsitu"));
                 Historyproject history=new Historyproject(id,name,age,sex,itemname,doc,starttime,charge,knowsitu);
                 Hisproject.add(history);
//                 Log.d("DocSearchFragment","cursor successful"+id+name);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

}