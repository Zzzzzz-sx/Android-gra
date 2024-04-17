package com.example.myapplication.patfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Pat_Personalinfo;
import com.example.myapplication.R;

public class PatMineFragment extends PatBaseFragment{

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_mine, container, false);
    }
    TextView personalinfopage;
    String getloginname;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        personalinfopage = getActivity().findViewById(R.id.tv_pat_personalinfo);
        Bundle bundle = getArguments();
        getloginname = bundle.getString("login_name");
        Log.d("DocMineFragment","name="+getloginname);
        personalinfopage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(view.getContext(), Pat_Personalinfo.class);
                intent.putExtra("login_name",getloginname);
                view.getContext().startActivity(intent);
            }
        });
    }
}
