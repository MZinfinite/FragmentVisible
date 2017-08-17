package com.danikule.fragmentapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.danikule.fragmentapplication.R;
import com.danikule.fragmentapplication.adapter.MainPagerAdapter;
import com.danikule.fragmentapplication.base.BaseActivity;
import com.danikule.fragmentapplication.fragment.HomeFragment;
import com.danikule.fragmentapplication.fragment.InfoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vpPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private HomeFragment homeFragment;
    private InfoFragment infoFragment;
    private TabLayout tbTopTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        tbTopTabs = (TabLayout) findViewById(R.id.tbTopTabs);
        findViewById(R.id.btJump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        homeFragment = new HomeFragment();
        infoFragment = new InfoFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(infoFragment);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList);
        vpPager.setAdapter(mainPagerAdapter);
        vpPager.addOnPageChangeListener(this);
        tbTopTabs.setupWithViewPager(vpPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
