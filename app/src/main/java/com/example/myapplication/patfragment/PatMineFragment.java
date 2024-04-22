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

import com.example.myapplication.Login.Login_pat;
import com.example.myapplication.Pat_Personalinfo;
import com.example.myapplication.Pat_resetpw;
import com.example.myapplication.R;

public class PatMineFragment extends PatBaseFragment{

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pat_mine, container, false);
    }
    TextView personalinfopage,changepassword,exitlogin;
    String getloginname;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        personalinfopage = getActivity().findViewById(R.id.tv_pat_personalinfo);
        changepassword = getActivity().findViewById(R.id.tv_changepw);
        exitlogin = getActivity().findViewById(R.id.tv_exitlogin);
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
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(view.getContext(), Pat_resetpw.class);
                intent.putExtra("login_name",getloginname);
                view.getContext().startActivity(intent);
            }
        });
        exitlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent  = new Intent(view.getContext(), Login_pat.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
