package com.example.myapplication.docfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Chat.ChatActivity;
import com.example.myapplication.HistoryItem.HistoryRecycleAdapter;
import com.example.myapplication.News.EditNews;
import com.example.myapplication.News.News;
import com.example.myapplication.R;
import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class docContactAdapter extends RecyclerView.Adapter<docContactAdapter.MyViewHolder> {
    List<ContactPerson> ContactArrayList;
    public docContactAdapter(List<ContactPerson> list) {
    this.ContactArrayList = list;
    }
    @NonNull
    @Override

    public docContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contactperson,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ContactPerson contactperson  = ContactArrayList.get(position);
        holder.recy_id.setText(contactperson.getPersonid()+"");
        holder.recy_name.setText(contactperson.getPersonname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), ChatActivity.class);
                intent.putExtra("contactid",contactperson.getPersonid());
                intent.putExtra("contactname",contactperson.getPersonname());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ContactArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView recy_id;
        TextView recy_name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recy_id = itemView.findViewById(R.id.tv_contactid);
            recy_name = itemView.findViewById(R.id.tv_contactname);
        }
    }
}
