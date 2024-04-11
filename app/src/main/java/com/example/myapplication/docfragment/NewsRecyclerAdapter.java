package com.example.myapplication.docfragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ShowNews;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder> {
    private List<News> mNews;

    public NewsRecyclerAdapter(List<News> list) {
        this.mNews = list;
    }

    //    public void HistoryRecAdapter(List<Historyproject> list){
//        this.mHistorypro = list;
//    }
    @NonNull
    @Override
    public NewsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_news,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerAdapter.MyViewHolder holder, int position) {
        //绑定数据
        News news =mNews.get(position);
        //设置数据
        holder.recy_title.setText(news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转并且传值
                Toast.makeText(view.getContext(),"跳转到news",Toast.LENGTH_SHORT);
                Intent intent  = new Intent(view.getContext(), ShowNews.class);
                intent.putExtra("title",news.getTitle().toString());
                intent.putExtra("content",news.getContent().toString());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView recy_title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recy_title = itemView.findViewById(R.id.tv_newstitle);
        }
    }
}

