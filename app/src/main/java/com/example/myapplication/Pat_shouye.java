package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.docfragment.DochomeFragment;
import com.example.myapplication.patfragment.PatHomeFragment;
import com.example.myapplication.patfragment.PatMessageFragment;
import com.example.myapplication.patfragment.PatMineFragment;
import com.example.myapplication.patfragment.PatSearchFragment;

public class Pat_shouye extends AppCompatActivity implements View.OnClickListener {

    private TextView tvHome;
    private TextView tvtabHome;
    private TextView tvItem;
    private TextView tvtabItem;
    private TextView tvMessage;
    private TextView tvtabMessage;
    private TextView tvMine;
    private TextView tvtabMine;
    private TextView mtvHome;
    private TextView mtvtabHome;
    private TextView mtvItem;
    private View mtvtabItem;
    private TextView mtvMessage;
    private View mtvtabMessage;
    private View mtvMine;
    private View mtvtabMine;
    private FragmentTransaction mfragmentTransaction;
    private PatHomeFragment mpatHomeFragment;
    private PatSearchFragment mpatSearchFragment;
    private PatMessageFragment mpatMessageFragment;
    private PatMineFragment mpatMineFragment;
    private FragmentManager mFragmentManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_shouye);
        initview();

    }

    private void initview() {
        //初始化img和tabbar控件
        mtvHome = findViewById(R.id.Tv_img_pat_home);
        mtvtabHome = findViewById(R.id.Tv_tab_pat_home);
        mtvItem = findViewById(R.id.Tv_img_pat_item);
        mtvtabItem = findViewById(R.id.Tv_tab_pat_item);
        mtvMessage = findViewById(R.id.Tv_img_pat_message);
        mtvtabMessage = findViewById(R.id.Tv_tab_pat_message);
        mtvMine = findViewById(R.id.Tv_img_pat_mine);
        mtvtabMine = findViewById(R.id.Tv_tab_pat_mine);
        //默认选中home控件
        mtvHome.setBackgroundResource(R.mipmap.home_selected);
        mtvtabHome.setTextColor(getResources().getColor(R.color.selected));
        //找到relativ容器
        RelativeLayout rlHome = findViewById(R.id.rl_pat_shouye);
        RelativeLayout rlItem = findViewById(R.id.rl_pat_item);
        RelativeLayout rlCreate = findViewById(R.id.rl_pat_create);
        RelativeLayout rlMessage = findViewById(R.id.rl_pat_message);
        RelativeLayout rlMine = findViewById(R.id.rl_pat_mine);
        //设置监听
        rlHome.setOnClickListener(this);
        rlItem.setOnClickListener(this);
        rlCreate.setOnClickListener(this);
        rlMessage.setOnClickListener(this);
        rlMine.setOnClickListener(this);
        //创建fragment
        mpatHomeFragment = new PatHomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("name",nameget);
//        Log.d("Doc_shouye","shouye name"+nameget);
//        mdocMineFragment = new DocMineFragment();
//        mdocMineFragment.setArguments(bundle);
        //创建manager管理类 activity---> manager ---> fragment 事务控制fragment
        mFragmentManager = getFragmentManager();
        mfragmentTransaction = mFragmentManager.beginTransaction();
        mfragmentTransaction.replace(R.id.pat_content_layout,mpatHomeFragment);
        //提交
        mfragmentTransaction.commit();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        mfragmentTransaction = mFragmentManager.beginTransaction();
    }
}
