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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.MySQliteOpenHelper;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

//医生端首页界面
public class DochomeFragment extends DocBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_home, container, false);
    }
    private List<News> news = new ArrayList<>();
    private MySQliteOpenHelper dbHelper;
    NewsRecyclerAdapter newsAdapter;
    RecyclerView recyclerView;
    Button createnews;

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shownews();
        recyclerView = getActivity().findViewById(R.id.rv_show_news);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsRecyclerAdapter(news);
        recyclerView.setAdapter(newsAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.BLACK));
        recyclerView.addItemDecoration(divider);
        createnews = getActivity().findViewById(R.id.btn_Create_News);
        createnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void shownews(){
        dbHelper = new MySQliteOpenHelper(getActivity(),"Docinfo.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("News",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Log.d("DochomeFragment","cursor successful");
                Integer id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                News news1=new News(id,title,content);
                news.add(news1);
//                 Log.d("DocSearchFragment","cursor successful"+id+name);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

}
