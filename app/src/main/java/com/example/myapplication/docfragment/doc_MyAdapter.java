package com.example.myapplication.docfragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.News.News;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class doc_MyAdapter extends RecyclerView.Adapter<doc_MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<News> newsArrayList;
    public doc_MyAdapter(Context context,ArrayList<News> newsArrayList) {
    this.context = context;
    this.newsArrayList = newsArrayList;
    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news  = newsArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView titleImage;;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
