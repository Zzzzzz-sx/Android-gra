package com.example.myapplication.docfragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Chat.ChatActivity;
import com.example.myapplication.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    List<ContactPerson> ContactArrayList;
    String currentname;
    int currentid;
    String userType;
    public ContactAdapter(List<ContactPerson> list, String name, int id,String userType) {
    this.ContactArrayList = list;
    this.currentname = name;
    this.currentid = id;
    this.userType = userType;
    }
    @NonNull
    @Override

    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                intent.putExtra("userType", userType);
                intent.putExtra("currentname",currentname);
                intent.putExtra("currentid",currentid);
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
