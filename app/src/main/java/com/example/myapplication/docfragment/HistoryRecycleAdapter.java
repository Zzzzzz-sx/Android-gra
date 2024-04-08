package com.example.myapplication.docfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecycleAdapter extends RecyclerView.Adapter<HistoryRecycleAdapter.MyViewHolder> {
    private List<Historyproject> mHistorypro = new ArrayList<>();
    public void HistoryProAdapter(List<Historyproject> list){
        this.mHistorypro = list;
    }
    @NonNull
    @Override
    public HistoryRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_doc_search,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecycleAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mHistorypro.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
