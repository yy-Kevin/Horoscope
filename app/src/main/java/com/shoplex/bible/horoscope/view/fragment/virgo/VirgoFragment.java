package com.shoplex.bible.horoscope.view.fragment.virgo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.api.RxBus;
import com.shoplex.bible.horoscope.base.BaseFragment;
import com.shoplex.bible.horoscope.base.BasePresenter;
import com.shoplex.bible.horoscope.bean.DataBean;
import com.shoplex.bible.horoscope.databinding.FragmentVirgoBinding;
import com.shoplex.bible.horoscope.view.activity.Aries.AriesActivity;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qsk on 2017/4/26.
 */

public class VirgoFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription;
    private FragmentVirgoBinding binding;

    public static VirgoFragment getInstance() {
        VirgoFragment fragment = new VirgoFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_virgo, container, false);
        Log.i(TAG,"yuyao VirgoFragment onCreateView");
        initInClude(binding.ilInclude);
        initLucky(binding.ilIncludeLuncky);
        initSwipeLayout(this,binding.swipeRefresh,binding.scroolview);
        binding.plProgress.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.pl_progress:
                DataBean bean = new DataBean();
                bean.setBackground(R.mipmap.bg_virgo);
                RxBus.getInstance().post(bean);
                Intent intent = new Intent(mActivity, AriesActivity.class);
                mActivity.startActivity(intent);
                break;
        }
    }
}
