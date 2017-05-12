package com.shoplex.bible.horoscope.view.fragment.gemini;

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
import com.shoplex.bible.horoscope.databinding.FragmentGeminiBinding;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qsk on 2017/4/26.
 */

public class GeminiFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription;
    private FragmentGeminiBinding binding;

    public static GeminiFragment getInstance() {
        GeminiFragment fragment = new GeminiFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gemini, container, false);
        initInClude(binding.ilInclude);
        initLucky(binding.ilIncludeLuncky);
        initSwipeLayout(binding.swipeRefresh,binding.scroolview);
        Log.i(TAG,"yuyao G=eminiFragment onCreateView");
        return binding.getRoot();
    }


    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }


}
