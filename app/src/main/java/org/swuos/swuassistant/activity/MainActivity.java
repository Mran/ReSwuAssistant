package org.swuos.swuassistant.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.ArrayMap;

import org.swuos.swuassistant.R;
import org.swuos.swuassistant.activity.login.LoginActivity;
import org.swuos.swuassistant.commmon.CommoneUtil;
import org.swuos.swuassistant.commmon.Constant;
import org.swuos.swuassistant.commmon.Logger;
import org.swuos.swuassistant.commmon.TotalInfos;
import org.swuos.swuassistant.page.BaseFragment;
import org.swuos.swuassistant.page.schedule.ScheduleFragment;
import org.swuos.swuassistant.weight.BottomBar;

/**
 * Created by mran on 17-12-10.
 */

public class MainActivity extends BaseActivity {
    BottomBar mBottomBar;
    ArrayMap<String, BaseFragment> mFragmentList;
    String[] tags = {"schedeule", "grade", "library", "mine"};
    int REQUEST_CODE = 1000;
    int RESULT_CODE = 0;//代表返回了预想的结果

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_home);
        bindView();
        initData();
    }

    public void bindView() {
        mBottomBar = new BottomBar(findViewById(R.id.bottom_bar));
        mBottomBar.setBottomBarItemClickLinstener(new BottomBar.BottomBarItemClickLinstener() {
            @Override
            public void itemClick(int i) {
                Logger.d("MainActivity", "itemClick: " + i);
                transFragment(i);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        if (CommoneUtil.isLogin(this)) {
            startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), REQUEST_CODE);
        }
        CommoneUtil.initTotalInfo(this, TotalInfos.getInstance());
        mFragmentList = new ArrayMap<>();
        mFragmentList.put(tags[0], new ScheduleFragment());
        mFragmentList.put(tags[1], new ScheduleFragment());
        mFragmentList.put(tags[2], new ScheduleFragment());
        mFragmentList.put(tags[3], new ScheduleFragment());
        mBottomBar.setSelectedItem(0);
        transFragment(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Constant.LOGIN_RESULT_CODE) {
                         /*打开保存用户信息的文件*/
                CommoneUtil.initTotalInfo(this, TotalInfos.getInstance());
                for (int i = 0; i < mFragmentList.size(); i++) {
                    BaseFragment baseFragment = mFragmentList.valueAt(i);
                    baseFragment.update();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void transFragment(int index) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        BaseFragment baseFragment;
        for (int i = 0; i < tags.length; i++) {
            if (i != index) {
                baseFragment = mFragmentList.get(tags[i]);
                fragmentTransaction.hide(baseFragment);
            } else {
                baseFragment = (BaseFragment) getFragmentManager().findFragmentByTag(tags[index]);
                if (baseFragment == null) {
                    baseFragment = mFragmentList.get(tags[index]);
                    fragmentTransaction.add(R.id.fragment_content, baseFragment, tags[index]);
                }
                fragmentTransaction.show(baseFragment);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
        Logger.d("MainActivity", "transFragment: ");
    }

    @Override
    public void onBackPressed() {
        for (int i = 0; i < mFragmentList.size(); i++) {
            if (mFragmentList.valueAt(i).onBackPressed()) return;
        }
        super.onBackPressed();
    }
}
