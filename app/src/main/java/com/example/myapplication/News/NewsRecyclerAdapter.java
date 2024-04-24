package com.example.myapplication.News;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder> {
    private List<News> mNews;
    private boolean isdocter;
    public NewsRecyclerAdapter(List<News> list,boolean isdocter) {
        this.mNews = list;
        this.isdocter = isdocter;
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
                Intent intent;
                //跳转并且传值
                if(isdocter){
                    intent = new Intent(view.getContext(),EditNews.class);
                    intent.putExtra("title",news.getTitle());
                    intent.putExtra("content",news.getContent());
                    intent.putExtra("newsid",news.getId());
                    view.getContext().startActivity(intent);
                }
                else if (!isdocter){
                    Toast.makeText(view.getContext(),"跳转到shownews",Toast.LENGTH_SHORT).show();
                    intent = new Intent(view.getContext(),ShowNews.class);
                    intent.putExtra("title",news.getTitle());
                    intent.putExtra("content",news.getContent());
                    intent.putExtra("newsid",news.getId());
                    view.getContext().startActivity(intent);
                }
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

