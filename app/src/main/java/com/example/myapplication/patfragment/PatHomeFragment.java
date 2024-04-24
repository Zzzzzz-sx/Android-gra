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

import com.example.myapplication.SQliteOpenHelper.MySQliteOpenHelper;
import com.example.myapplication.R;
import com.example.myapplication.News.News;
import com.example.myapplication.News.NewsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PatHomeFragment extends PatBaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_home, container, false);
    }
    private List<News> news = new ArrayList<>();
    private MySQliteOpenHelper dbHelper;
    NewsRecyclerAdapter newsAdapter;
    RecyclerView recyclerView;
    boolean isdocter = false;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shownews();
        recyclerView = getActivity().findViewById(R.id.rv_pat_show_news);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsRecyclerAdapter(news,isdocter);
        recyclerView.setAdapter(newsAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.BLACK));
        recyclerView.addItemDecoration(divider);
    }

    private void shownews() {
        dbHelper = new MySQliteOpenHelper(getActivity(),"Docinfo.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("News",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Log.d("PatHomeFragment","cursor successful");
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
