package com.example.myapplication.docfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Doc_workcount;
import com.example.myapplication.R;

//医生端个人信息界面
public class DocMineFragment extends DocBaseFragment {
//    ImageView docimg;
    TextView showname,workcount;
    String name;


//    public static DocMineFragment newInstance(String logname) {
//        DocMineFragment fragment = new DocMineFragment();
//        Bundle args = new Bundle();
//        args.putString("name",logname);
//        fragment.setArguments(args);
//        return fragment;
//    }

    //    View docter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        if (getArguments() != null) {
//            name = getArguments().getString("name");
//            Log.d("DocMineFragment","name="+name);
//        }\

        return inflater.inflate(R.layout.fragment_doc_mine, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showname = getActivity().findViewById(R.id.tv_mine_name);
        workcount = getActivity().findViewById(R.id.tv_work_statistics);
        Bundle bundle = getArguments();
        name = bundle.getString("name");
        Log.d("DocMineFragment","name="+name);
        showname.setText(name);
        workcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Doc_workcount.class));
            }
        });
//        Showname();
//        Intent intent = Intent.getIntent();
//        if(intent!= null){
//            String name = intent.getStringExtra("name");
//            showname.setText(name);
//        }
//        docimg = getActivity().findViewById(R.id.docter_image_view);
//        Glide.with(getActivity())
//                .load("F:\\ASproject\\app\\src\\main\\res\\mipmap-xxhdpi.mine_docterpic.png")
//                .placeholder(R.mipmap.mine_docterpic) // Placeholder image while loading
//                .circleCrop() // Crop the loaded image into circular shape
//                .into(docimg);
    }
//    public void Showname(){
//        Intent intent = getIntent();
//        if(intent!= null){
//            String name = intent.getStringExtra("name");
//            showname.setText(name);
//    }
}