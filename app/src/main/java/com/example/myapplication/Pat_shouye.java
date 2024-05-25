package com.example.myapplication;

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

import com.example.myapplication.patfragment.PatHomeFragment;
import com.example.myapplication.patfragment.PatMessageFragment;
import com.example.myapplication.patfragment.PatMineFragment;
import com.example.myapplication.patfragment.PatSearchFragment;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class Pat_shouye extends AppCompatActivity implements View.OnClickListener {

    private TextView mtvHome;
    private TextView mtvtabHome;
    private TextView mtvItem;
    private TextView mtvtabItem;
    private TextView mtvMessage;
    private TextView mtvtabMessage;
    private TextView mtvMine;
    private TextView mtvtabMine;
    private FragmentTransaction mfragmentTransaction;
    private PatHomeFragment mpatHomeFragment;
    private PatSearchFragment mpatSearchFragment;
    private PatMessageFragment mpatMessageFragment;
    private PatMineFragment mpatMineFragment;
    private FragmentManager mFragmentManager;
    String getloginname,getpersonalid,getpatname;
    int getpatid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pat_shouye);
        Intent intent = getIntent();
        if(intent != null){
            getloginname = intent.getStringExtra("login_name");
            getpersonalid = intent.getStringExtra("patpersonalid");
            getpatname = intent.getStringExtra("patname");
            getpatid = intent.getIntExtra("patid",0);
        }
        initview();

    }

    private void initview() {
        //链接SQLliteStudio
        SQLiteStudioService.instance().start(this);
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
        //找到relative容器
        RelativeLayout rlHome = findViewById(R.id.rl_pat_shouye);
        RelativeLayout rlItem = findViewById(R.id.rl_pat_item);
        RelativeLayout rlMessage = findViewById(R.id.rl_pat_message);
        RelativeLayout rlMine = findViewById(R.id.rl_pat_mine);
        //设置监听
        rlHome.setOnClickListener(this);
        rlItem.setOnClickListener(this);
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
        mfragmentTransaction.add(R.id.pat_content_layout,mpatHomeFragment);
        //提交
        mfragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        mfragmentTransaction = mFragmentManager.beginTransaction();
        switch (id){
            case R.id.rl_pat_shouye:
//                不存在时创建 存在时使用事务show操作
//                使用方法隐藏其他fragment
                if(mpatHomeFragment!=null){
                    mfragmentTransaction.show(mpatHomeFragment);
                }
                if(mpatSearchFragment!=null){
                    mfragmentTransaction.hide(mpatSearchFragment);
                }
                if(mpatMessageFragment != null){
                    mfragmentTransaction.hide(mpatMessageFragment);
                }
                if(mpatMineFragment != null){
                    mfragmentTransaction.hide(mpatMineFragment);
                }
                changecolors(mtvHome,mtvtabHome,R.mipmap.home_selected,R.color.selected);
            break;
            case R.id.rl_pat_item:
//                不存在时创建 存在时使用事务show操作
//                使用方法隐藏其他fragment
                if(mpatHomeFragment!=null){
                    mfragmentTransaction.hide(mpatHomeFragment);
                }
                if(mpatMessageFragment != null){
                    mfragmentTransaction.hide(mpatMessageFragment);
                }
                if(mpatMineFragment != null){
                    mfragmentTransaction.hide(mpatMineFragment);
                }
                if(mpatSearchFragment == null){
                    mpatSearchFragment = new PatSearchFragment();
                    mfragmentTransaction.add(R.id.pat_content_layout,mpatSearchFragment);
                    Bundle bundle = new Bundle();
                    bundle.putString("patpersonalid",getpersonalid);
                    mpatSearchFragment.setArguments(bundle);
                }else if (mpatSearchFragment !=null){
                    mfragmentTransaction.show(mpatSearchFragment);
                }
                changecolors(mtvItem,mtvtabItem,R.mipmap.searchitem_selected,R.color.selected);
                break;
            case R.id.rl_pat_message:
//                todo 切换到DocMessageFragment
                if(mpatHomeFragment != null){
                    mfragmentTransaction.hide(mpatHomeFragment);
                }
                if(mpatSearchFragment != null){
                    mfragmentTransaction.hide(mpatSearchFragment);
                }
                if(mpatMineFragment != null){
                    mfragmentTransaction.hide(mpatMineFragment);
                }
                if(mpatMessageFragment == null){
                    mpatMessageFragment = new PatMessageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("patname",getpatname);
                    bundle.putInt("patid",getpatid);
                    mfragmentTransaction.add(R.id.pat_content_layout,mpatMessageFragment);
                    mpatMessageFragment.setArguments(bundle);
                }else{
                    mfragmentTransaction.show(mpatMessageFragment);
                }

                changecolors(mtvMessage,mtvtabMessage,R.mipmap.message_selected,R.color.selected);
                break;
            case R.id.rl_pat_mine:
//                todo 切换到DocMineFragment'
                //隐藏其他fragment
                if(mpatHomeFragment != null){
                    mfragmentTransaction.hide(mpatHomeFragment);
                }
                if(mpatSearchFragment != null){
                    mfragmentTransaction.hide(mpatSearchFragment);
                }
                if(mpatMessageFragment != null){
                    mfragmentTransaction.hide(mpatMessageFragment);
                }
                //显示选中fragment
                if(mpatMineFragment == null){
                    mpatMineFragment = new PatMineFragment();
                    mfragmentTransaction.add(R.id.pat_content_layout,mpatMineFragment);
                    Bundle bundle = new Bundle();
                    bundle.putString("login_name",getloginname);
                    Log.d("Pat_shouye","shouye name"+getloginname);
                    mpatMineFragment.setArguments(bundle);
                }else{
                    mfragmentTransaction.show(mpatMineFragment);
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
        mtvMessage.setBackgroundResource(R.mipmap.message);
        mtvtabMessage.setTextColor(getResources().getColor(R.color.unselected));
        mtvMine.setBackgroundResource(R.mipmap.mine);
        mtvtabMine.setTextColor(getResources().getColor(R.color.unselected));
        //设置选中的变换颜色
        changeimg.setBackgroundResource(changeID);
        changetab.setTextColor(changecolor);
    }
}
