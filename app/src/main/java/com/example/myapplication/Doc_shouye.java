package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.docfragment.DocCreateFragment;
import com.example.myapplication.docfragment.DocMessageFragment;
import com.example.myapplication.docfragment.DocMineFragment;
import com.example.myapplication.docfragment.DocSearchFragment;
import com.example.myapplication.docfragment.DochomeFragment;


import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class Doc_shouye extends AppCompatActivity implements View.OnClickListener {

    private TextView mtvHome;
    private TextView mtvtabHome;
    private TextView mtvItem;
    private TextView mtvtabItem;
    private TextView mtvCreate;
    private TextView mtvMessage;
    private TextView mtvtabMessage;
    private TextView mtvMine;
    private TextView mtvtabMine;
    private FragmentTransaction mfragmentTransaction;
    private DocSearchFragment mdocSearchFragment;
    private DocCreateFragment mdocCreateFragment;
    private DocMessageFragment mdocMessageFragment;

    private DocMineFragment mdocMineFragment;
    private DochomeFragment mdochomeFragment;
    private FragmentManager mFragmentManager;
    String nameget;
    int idget;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_shouye);
        Intent intent = getIntent();
        if(intent != null){
            nameget = intent.getStringExtra("name");
            idget = intent.getIntExtra("doctorid",0);
        }
        //初始化函数
        initview();
    }

    private void initview() {
        //链接SQLliteStudio
        SQLiteStudioService.instance().start(this);

        //初始化界面 进入首页
        mtvHome = findViewById(R.id.Tv_img_home);
        mtvtabHome = findViewById(R.id.Tv_tab_home);
        mtvItem = findViewById(R.id.Tv_img_item);
        mtvtabItem = findViewById(R.id.Tv_tab_item);
        mtvCreate = findViewById(R.id.Tv_img_createit);
        mtvMessage = findViewById(R.id.Tv_img_message);
        mtvtabMessage = findViewById(R.id.Tv_tab_message);
        mtvMine = findViewById(R.id.Tv_img_mine);
        mtvtabMine = findViewById(R.id.Tv_tab_mine);
        mtvHome.setBackgroundResource(R.mipmap.home_selected);
        mtvtabHome.setTextColor(getResources().getColor(R.color.selected));
        //找到relative容器
        RelativeLayout rlHome = findViewById(R.id.rl_doc_shouye);
        RelativeLayout rlItem = findViewById(R.id.rl_doc_item);
        RelativeLayout rlCreate = findViewById(R.id.rl_doc_create);
        RelativeLayout rlMessage = findViewById(R.id.rl_doc_message);
        RelativeLayout rlMine = findViewById(R.id.rl_doc_mine);
        //设置监听
        rlHome.setOnClickListener(this);
        rlItem.setOnClickListener(this);
        rlCreate.setOnClickListener(this);
        rlMessage.setOnClickListener(this);
        rlMine.setOnClickListener(this);
        //创建fragment
        mdochomeFragment = new DochomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("name",nameget);
//        Log.d("Doc_shouye","shouye name"+nameget);
//        mdocMineFragment = new DocMineFragment();
//        mdocMineFragment.setArguments(bundle);
        //创建manager管理类 activity---> manager ---> fragment 事务控制fragment
        mFragmentManager = getFragmentManager();
        mfragmentTransaction = mFragmentManager.beginTransaction();
        mfragmentTransaction.replace(R.id.doc_content_layout,mdochomeFragment);
        //提交
        mfragmentTransaction.commit();


    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        mfragmentTransaction = mFragmentManager.beginTransaction();
        switch (id){
            case R.id.rl_doc_shouye:
//                mtvHome.setBackgroundResource(R.mipmap.home_selected);
//                mtvtabHome.setTextColor(getResources().getColor(R.color.selected));
//                mtvItem.setBackgroundResource(R.mipmap.searchitem);
//                mtvtabItem.setTextColor(getResources().getColor(R.color.unselected));
//                mtvCreate.setBackgroundResource(R.mipmap.createitem);
//                mtvMessage.setBackgroundResource(R.mipmap.message);
//                mtvtabMessage.setTextColor(getResources().getColor(R.color.unselected));
//                mtvMine.setBackgroundResource(R.mipmap.mine);
//                mtvtabMine.setTextColor(getResources().getColor(R.color.unselected))；
//                todo 切换到DochomeFragment
                if(mdochomeFragment != null){
                    mfragmentTransaction.show(mdochomeFragment);
                }
                if(mdocSearchFragment != null){
                    mfragmentTransaction.hide(mdocSearchFragment);
                }
                if(mdocCreateFragment != null){
                       mfragmentTransaction.hide(mdocCreateFragment);
                }
                if(mdocMessageFragment != null){
                    mfragmentTransaction.hide(mdocMessageFragment);
                }
                if(mdocMineFragment != null){
                    mfragmentTransaction.hide(mdocMineFragment);
                }
                changecolors(mtvHome,mtvtabHome,R.mipmap.home_selected,R.color.selected);
            break;
            case R.id.rl_doc_item:
//                mtvItem.setBackgroundResource(R.mipmap.searchitem_selected);
//                mtvtabItem.setTextColor(getResources().getColor(R.color.selected));
//                mtvHome.setBackgroundResource(R.mipmap.home);
//                mtvtabHome.setTextColor(getResources().getColor(R.color.unselected));
//                mtvCreate.setBackgroundResource(R.mipmap.createitem);
//                mtvMessage.setBackgroundResource(R.mipmap.message);
//                mtvtabMessage.setTextColor(getResources().getColor(R.color.unselected));
//                mtvMine.setBackgroundResource(R.mipmap.mine);
//                mtvtabMine.setTextColor(getResources().getColor(R.color.unselected));
//                todo 切换到DocSearchFragment
//                不存在时创建 存在时使用事务show操作
//                使用方法隐藏其他fragment
                if(mdochomeFragment != null){
                    mfragmentTransaction.hide(mdochomeFragment);
                }
                if(mdocCreateFragment != null){
                    mfragmentTransaction.hide(mdocCreateFragment);
                }
                if(mdocMessageFragment != null){
                    mfragmentTransaction.hide(mdocMessageFragment);
                }
                if(mdocMineFragment != null){
                    mfragmentTransaction.hide(mdocMineFragment);
                }
                if(mdocSearchFragment == null){
                    mdocSearchFragment = new DocSearchFragment();
                    mfragmentTransaction.add(R.id.doc_content_layout,mdocSearchFragment);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",nameget);
                    Log.d("Doc_shouye","shouye name"+nameget);
                    mdocSearchFragment.setArguments(bundle);
                }else{
                    mfragmentTransaction.show(mdocSearchFragment);
                }

                changecolors(mtvItem,mtvtabItem,R.mipmap.searchitem_selected,R.color.selected);
                break;
            case R.id.rl_doc_create:
                mtvCreate.setBackgroundResource(R.mipmap.createitem_selected);
                mtvHome.setBackgroundResource(R.mipmap.home);
                mtvtabHome.setTextColor(getResources().getColor(R.color.unselected));
                mtvItem.setBackgroundResource(R.mipmap.searchitem);
                mtvtabItem.setTextColor(getResources().getColor(R.color.unselected));
                mtvMessage.setBackgroundResource(R.mipmap.message);
                mtvtabMessage.setTextColor(getResources().getColor(R.color.unselected));
                mtvMine.setBackgroundResource(R.mipmap.mine);
                mtvtabMine.setTextColor(getResources().getColor(R.color.unselected));
//              todo 切换到DocCreateFragment
                if(mdochomeFragment != null){
                    mfragmentTransaction.hide(mdochomeFragment);
                }
                if(mdocSearchFragment != null){
                    mfragmentTransaction.hide(mdocSearchFragment);
                }
                if(mdocMessageFragment != null){
                    mfragmentTransaction.hide(mdocMessageFragment);
                }
                if(mdocMineFragment != null){
                    mfragmentTransaction.hide(mdocMineFragment);
                }
                if(mdocCreateFragment == null){
                    mdocCreateFragment = new DocCreateFragment();
                    mfragmentTransaction.add(R.id.doc_content_layout,mdocCreateFragment);
                }else{
                    mfragmentTransaction.show(mdocCreateFragment);
                }
            break;
            case R.id.rl_doc_message:
//                mtvMessage.setBackgroundResource(R.mipmap.message_selected);
//                mtvtabMessage.setTextColor(getResources().getColor(R.color.selected));
//                mtvHome.setBackgroundResource(R.mipmap.home);
//                mtvtabHome.setTextColor(getResources().getColor(R.color.unselected));
//                mtvItem.setBackgroundResource(R.mipmap.searchitem);
//                mtvtabItem.setTextColor(getResources().getColor(R.color.unselected));
//                mtvCreate.setBackgroundResource(R.mipmap.createitem);
//                mtvMine.setBackgroundResource(R.mipmap.mine);
//                mtvtabMine.setTextColor(getResources().getColor(R.color.unselected));
//                todo 切换到DocMessageFragment
                if(mdochomeFragment != null){
                    mfragmentTransaction.hide(mdochomeFragment);
                }
                if(mdocSearchFragment != null){
                    mfragmentTransaction.hide(mdocSearchFragment);
                }
                if(mdocCreateFragment != null){
                    mfragmentTransaction.hide(mdocCreateFragment);
                }
                if(mdocMineFragment != null){
                    mfragmentTransaction.hide(mdocMineFragment);
                }
                if(mdocMessageFragment == null){
                    mdocMessageFragment = new DocMessageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name",nameget);
                    bundle.putInt("doctorid",idget);
                    mdocMessageFragment.setArguments(bundle);
                    mfragmentTransaction.add(R.id.doc_content_layout,mdocMessageFragment);
                }else{
                    mfragmentTransaction.show(mdocMessageFragment);
                }
                changecolors(mtvMessage,mtvtabMessage,R.mipmap.message_selected,R.color.selected);
                break;
            case R.id.rl_doc_mine:
//                mtvMine.setBackgroundResource(R.mipmap.mine_selected);
//                mtvtabMine.setTextColor(getResources().getColor(R.color.selected));
//                mtvHome.setBackgroundResource(R.mipmap.home);
//                mtvtabHome.setTextColor(getResources().getColor(R.color.unselected));
//                mtvItem.setBackgroundResource(R.mipmap.searchitem);
//                mtvtabItem.setTextColor(getResources().getColor(R.color.unselected));
//                mtvCreate.setBackgroundResource(R.mipmap.createitem);
//                mtvMessage.setBackgroundResource(R.mipmap.message);
//                mtvtabMessage.setTextColor(getResources().getColor(R.color.unselected));
//                todo 切换到DocMineFragment'
                //隐藏其他fragment
                if(mdochomeFragment != null){
                    mfragmentTransaction.hide(mdochomeFragment);
                }
                if(mdocSearchFragment != null){
                    mfragmentTransaction.hide(mdocSearchFragment);
                }
                if(mdocCreateFragment != null){
                    mfragmentTransaction.hide(mdocCreateFragment);
                }
                if(mdocMessageFragment != null){
                    mfragmentTransaction.hide(mdocMessageFragment);
                }
                //显示选中fragment
                if(mdocMineFragment == null){
                    mdocMineFragment = new DocMineFragment();
                    mfragmentTransaction.add(R.id.doc_content_layout,mdocMineFragment);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",nameget);
                    Log.d("Doc_shouye","shouye name"+nameget);
                    mdocMineFragment.setArguments(bundle);
                }else{
                        mfragmentTransaction.show(mdocMineFragment);
                }
                changecolors(mtvMine,mtvtabMine,R.mipmap.mine_selected,R.color.selected);
                break;
        }
        mfragmentTransaction.commit();
    }

    private void changecolors(TextView changeimg, TextView changetab, int changeID, int changecolor) {
        //初始化为未选中
        mtvHome.setBackgroundResource(R.mipmap.home);
        mtvtabHome.setTextColor(getResources().getColor(R.color.unselected));
        mtvItem.setBackgroundResource(R.mipmap.searchitem);
        mtvtabItem.setTextColor(getResources().getColor(R.color.unselected));
        mtvCreate.setBackgroundResource(R.mipmap.createitem);
        mtvMessage.setBackgroundResource(R.mipmap.message);
        mtvtabMessage.setTextColor(getResources().getColor(R.color.unselected));
        mtvMine.setBackgroundResource(R.mipmap.mine);
        mtvtabMine.setTextColor(getResources().getColor(R.color.unselected));
        //设置选中的变换颜色
        changeimg.setBackgroundResource(changeID);
        changetab.setTextColor(changecolor);
    }
}

