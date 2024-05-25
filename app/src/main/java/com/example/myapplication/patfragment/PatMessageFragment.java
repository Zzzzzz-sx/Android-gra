package com.example.myapplication.patfragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;
import com.example.myapplication.SQliteOpenHelper.PatSQliteOpenHelper;
import com.example.myapplication.docfragment.ContactAdapter;
import com.example.myapplication.docfragment.ContactPerson;

import java.util.ArrayList;
import java.util.List;

public class PatMessageFragment extends PatBaseFragment{
    private List<ContactPerson> contactList = new ArrayList<>();
    private MySQliteOpenHelper dbHelper;
    ContactAdapter patContactAdapter;
    RecyclerView recyclerView;
    String patientname,userType;
    int patientid;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_message, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        userType = "patient";
        showperson();
        Bundle bundle = getArguments();
        patientname = bundle.getString("patname");
        patientid = bundle.getInt("patid");
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.rv_pat_receiver);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        patContactAdapter = new ContactAdapter(contactList,patientname,patientid,userType);
        recyclerView.setAdapter(patContactAdapter);
        //recyclerview的分隔下划线设置
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.BLACK));
        recyclerView.addItemDecoration(divider);
    }

    private void showperson() {
        dbHelper = new MySQliteOpenHelper(getActivity(),"Docinfo.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("docter",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Integer id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("docter_name"));
                ContactPerson person=new ContactPerson(id,name);
                contactList.add(person);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}
