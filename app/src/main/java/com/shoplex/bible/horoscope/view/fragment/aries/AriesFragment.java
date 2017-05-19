package com.shoplex.bible.horoscope.view.fragment.aries;

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
import com.shoplex.bible.horoscope.bean.DataBean;
import com.shoplex.bible.horoscope.databinding.FragmentAriesBinding;
import com.shoplex.bible.horoscope.view.activity.Aries.AriesActivity;

/**
 * Created by qsk on 2017/4/26.
 */

public class AriesFragment extends BaseFragment<AriesPresenter> implements AriesContract.IAriesView, View.OnClickListener {

    private FragmentAriesBinding binding;

    public static AriesFragment getInstance() {
        AriesFragment fragment = new AriesFragment();
        return fragment;
    }

    @Override
    protected AriesPresenter createPresenter() {
        return new AriesPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_aries, container, false);

//        binding.plProgress.showProgress();
        binding.plProgress.setOnClickListener(this);
        initInClude(binding.ilInclude);
        initLucky(binding.ilIncludeLuncky);
        initSwipeLayout(this,binding.swipeRefresh,binding.scroolview);
        binding.plProgress.showContent();

        return binding.getRoot();
    }

    @Override
    public void initNet() {
        mPresenter.loading();
    }

    @Override
    public void toMainActivity(Object user) {
        binding.plProgress.showContent();
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showFailedError() {
        binding.swipeRefresh.setRefreshing(false);
        binding.plProgress.showErrorText(mActivity.getResources().getString(R.string.error));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.pl_progress:
                DataBean bean = new DataBean();
                bean.setBackground(R.mipmap.bg_aquarius);


                Intent intent = new Intent(mActivity, AriesActivity.class);
                intent.putExtra("aries",R.mipmap.bg_aries);


                Log.i(TAG,"yuyao ariesFragment" + bean.getBackground());

                RxBus.getInstance().post(bean);

                mActivity.startActivity(intent);
                break;
        }
    }
}
