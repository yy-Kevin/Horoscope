package com.shoplex.bible.horoscope.view.fragment.pisces;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.base.BaseFragment;
import com.shoplex.bible.horoscope.base.BasePresenter;
import com.shoplex.bible.horoscope.databinding.FragmentPiscesBinding;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qsk on 2017/4/26.
 */

public class PiscesFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription;
    private FragmentPiscesBinding binding;

    public static PiscesFragment getInstance() {
        PiscesFragment fragment = new PiscesFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pisces, container, false);
        initInClude(binding.ilInclude);
        initLucky(binding.ilIncludeLuncky);
        initSwipeLayout(binding.swipeRefresh,binding.scroolview);

        Log.i(TAG,"yuyao PiscesFragment onCreateView");
        return binding.getRoot();
    }


    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }
}
