package com.example.myapplication.docfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecycleAdapter extends RecyclerView.Adapter<HistoryRecycleAdapter.MyViewHolder> {
    private List<Historyproject> mHistorypro;

    public HistoryRecycleAdapter(List<Historyproject> list) {
        this.mHistorypro = list;
    }

//    public void HistoryRecAdapter(List<Historyproject> list){
//        this.mHistorypro = list;
//    }
    @NonNull
    @Override
    public HistoryRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_history,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecycleAdapter.MyViewHolder holder, int position) {
        //绑定数据
        Historyproject historypro =mHistorypro.get(position);
        //设置数据
//        holder.recy_name.setText(mHistorypro.get(position).getHistoryname());
//        holder.recy_age.setText(mHistorypro.get(position).getHistoryage()+"");
//        holder.recy_sex.setText(mHistorypro.get(position).getHistorysex());
//        holder.recy_doctername.setText(mHistorypro.get(position).getHistorydoc());
//        holder.recy_itemname.setText(mHistorypro.get(position).getHistoryitemname());
//        holder.recy_startdate.setText(mHistorypro.get(position).getHistorystarttime());
//        holder.recy_ifcharge.setText(mHistorypro.get(position).getHistorycharge()+"");
//        holder.recy_knowsituation.setText(mHistorypro.get(position).getHistoryknowsitu()+"");
        holder.recy_id.setText(historypro.getHistoryid()+"");
        holder.recy_name.setText(historypro.getHistoryname());
        holder.recy_age.setText(historypro.getHistoryage()+"");
        holder.recy_sex.setText(historypro.getHistorysex());
        holder.recy_doctername.setText(historypro.getHistorydoc());
        holder.recy_itemname.setText(historypro.getHistoryitemname());
        holder.recy_startdate.setText(historypro.getHistorystarttime());
        holder.recy_ifcharge.setText(historypro.getHistorycharge()+"");
        holder.recy_knowsituation.setText(historypro.getHistoryknowsitu()+"");

    }

    @Override
    public int getItemCount() {
        return mHistorypro.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView recy_name;
        TextView recy_age;
        TextView recy_sex;
        TextView recy_doctername;
        TextView recy_startdate;
        TextView recy_ifcharge;
        TextView recy_knowsituation;
        TextView recy_itemname;
        TextView recy_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recy_name = itemView.findViewById(R.id.rv_name);
            recy_age = itemView.findViewById(R.id.rv_age);
            recy_sex = itemView.findViewById(R.id.rv_sex);
            recy_doctername = itemView.findViewById(R.id.rv_doctername);
            recy_startdate = itemView.findViewById(R.id.rv_startdate);
            recy_ifcharge = itemView.findViewById(R.id.rv_ifcharge);
            recy_knowsituation = itemView.findViewById(R.id.rv_knowsituation);
            recy_itemname = itemView.findViewById(R.id.rv_itemname);
            recy_id = itemView.findViewById(R.id.rv_id);
        }
    }
}
