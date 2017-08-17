package com.danikule.fragmentapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lsq on 8/17/2017.
 * Fragment的可见性可分为三种情况：
 * 情况一、当Fragment嵌套在Fragment中时，调用父Fragment的hide方法进行切换时调用{@link Fragment#onHiddenChanged(boolean)}
 * 情况二、当父容器是ViewPager时，切换ViewPager的item时调用{@link Fragment#setUserVisibleHint(boolean)}
 * 情况三、当Fragment回到屏幕视图顶端时调用{@link Fragment#onResume()}和{@link Fragment#onPause()}
 * <p>
 * 所有的情况都可以直接在{@link BaseFragment {@link #doWhenVisibleChange(boolean)}方法中进行相应的操作}
 */

public class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName() + "#######";

    /**
     * 情况一、当Fragment嵌套在Fragment中时，调用父Fragment的hide方法进行切换会调用此方法
     *
     * @param hidden true表示不可见
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        doWhenVisibleChange(!hidden);
        needChangeChild(!hidden);
    }

    /**
     * 情况二、当父容器是ViewPager时，切换ViewPager会调用此方法
     *
     * @param isVisibleToUser true表示可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isAdded()) {
            doWhenVisibleChange(isVisibleToUser);
            needChangeChild(isVisibleToUser);
        }
    }

    /**
     * 情况三、当Fragment回到屏幕视图顶端时，判断Fragment的可见性
     */
    @Override
    public void onResume() {
        super.onResume();
        doWhenVisibleChange((getUserVisibleHint() && isVisible() && isParentVisible()));
    }

    /**
     * 如果是嵌套在Fragment中，判断父Fragment是否可见
     * 注意：父Fragment需要使用getChildFragmentManager方法进行fragment的add操作，否则getParentFragment()将获取到null
     *
     * @return
     */
    private boolean isParentVisible() {
        Fragment parent = getParentFragment();//这里获取到父Fragment，父Fragment需要使用getChildFragmentManager方法进行fragment的add操作
        boolean parentVisible = true;
        if (parent != null) {
            parentVisible = parent.getUserVisibleHint() && parent.isVisible();
        }
        return parentVisible;
    }

    /**
     * 判断Fragment是否嵌套了子Fragment,如果嵌套了，则通知子Fragment是否可见
     *
     * @param visible true表示可见
     */
    private void needChangeChild(boolean visible) {
        if (isAdded() && getChildFragmentManager() != null) {
            List<Fragment> childFragments = getChildFragmentManager().getFragments();
            if (childFragments != null && childFragments.size() > 0) {
                for (Fragment childFragment : childFragments) {
                    if (childFragment instanceof BaseFragment) {
                        ((BaseFragment) childFragment).doWhenVisibleChange(visible
                                && childFragment.getUserVisibleHint()
                                && childFragment.isVisible());
                    }
                }
            }
        }
    }

    /**
     * Fragment可见性发生变化时会调用此方法
     *
     * @param visible true表示可见
     */
    public void doWhenVisibleChange(boolean visible) {
        Log.i(TAG, "doWhenVisibleChange: " + (visible ? "可见" : "不可见"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
        doWhenVisibleChange(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
