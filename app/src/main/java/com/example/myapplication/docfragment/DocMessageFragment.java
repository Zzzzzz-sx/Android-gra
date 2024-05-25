package com.example.myapplication.docfragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.SQliteOpenHelper.PatSQliteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//医生端聊天界面
public class DocMessageFragment extends DocBaseFragment {
    private List<ContactPerson> contactList = new ArrayList<>();
    private PatSQliteOpenHelper dbHelper;
    ContactAdapter docContactAdapter;
    RecyclerView recyclerView;
    String doctorname,userType;
    int doctorid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_message, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        userType ="doctor";
        Bundle bundle = getArguments();
        doctorname = bundle.getString("name");
        doctorid = bundle.getInt("doctorid");
        showperson();
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.rv_doc_receiver);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        docContactAdapter = new ContactAdapter(contactList,doctorname,doctorid,userType);
        recyclerView.setAdapter(docContactAdapter);
        //recyclerview的分隔下划线设置
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.BLACK));
        recyclerView.addItemDecoration(divider);
    }

    private void showperson() {
        dbHelper = new PatSQliteOpenHelper(getActivity(),"Patinfo.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("patient",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Integer id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("pat_name"));
                ContactPerson person=new ContactPerson(id,name);
                contactList.add(person);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}