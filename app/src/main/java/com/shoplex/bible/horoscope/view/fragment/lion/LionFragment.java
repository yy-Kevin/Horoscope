package com.shoplex.bible.horoscope.view.fragment.lion;

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
import com.shoplex.bible.horoscope.databinding.FragmentLionBinding;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qsk on 2017/4/26.
 */

public class LionFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription;
    private FragmentLionBinding binding;

    public static LionFragment getInstance() {
        LionFragment fragment = new LionFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lion, container, false);
        initInClude(binding.ilInclude);
        initLucky(binding.ilIncludeLuncky);
        initSwipeLayout(this,binding.swipeRefresh,binding.scroolview);


        Log.i(TAG,"yuyao LionFragment onCreateView");
        return binding.getRoot();
    }


    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }
}
