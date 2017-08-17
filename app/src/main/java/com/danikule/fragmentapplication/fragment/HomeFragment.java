package com.danikule.fragmentapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.danikule.fragmentapplication.R;
import com.danikule.fragmentapplication.base.BaseFragment;

/**
 * Created by lsq on 8/17/2017.
 * 这个Fragment中嵌套了3个Fragment，
 * 这里使用的FragmentManager的getChildFragmentManager，以便子Fragment能使用getParentFragment方法获取到父Fragment
 */

public class HomeFragment extends BaseFragment {
    private BaseFragment firstFragment;
    private BaseFragment secondFragment;
    private BaseFragment thirdFragment;

    private FrameLayout flContainer;
    private Button btFragment1;
    private Button btFragment2;
    private Button btFragment3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flContainer = (FrameLayout) view.findViewById(R.id.flContainer);
        btFragment1 = (Button) view.findViewById(R.id.btFragment1);
        btFragment2 = (Button) view.findViewById(R.id.btFragment2);
        btFragment3 = (Button) view.findViewById(R.id.btFragment3);
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        getChildFragmentManager().beginTransaction()
                .add(R.id.flContainer, firstFragment)
                .show(firstFragment)
                .add(R.id.flContainer, secondFragment)
                .hide(secondFragment)
                .add(R.id.flContainer, thirdFragment)
                .hide(thirdFragment)
                .commit();

        btFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .hide(secondFragment)
                        .hide(thirdFragment)
                        .show(firstFragment)
                        .commit();
            }
        });

        btFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .hide(firstFragment)
                        .hide(thirdFragment)
                        .show(secondFragment)
                        .commit();
            }
        });

        btFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction()
                        .hide(firstFragment)
                        .hide(secondFragment)
                        .show(thirdFragment)
                        .commit();
            }
        });
    }
}
