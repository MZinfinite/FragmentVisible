package com.danikule.fragmentapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danikule.fragmentapplication.R;
import com.danikule.fragmentapplication.base.BaseFragment;

/**
 * Created by lsq on 8/17/2017.
 */

public class NormalFragment extends BaseFragment {
    private TextView textView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_normal, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.tvTitle);
        textView.setText(this.getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
