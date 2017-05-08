package com.shoplex.bible.horoscope.view.fragment.scorpio;

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
import com.shoplex.bible.horoscope.databinding.FragmentScorpioBinding;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qsk on 2017/4/26.
 */

public class ScorpioFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription;
    private FragmentScorpioBinding binding;

    public static ScorpioFragment getInstance() {
        ScorpioFragment fragment = new ScorpioFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scorpio, container, false);
        Log.i(TAG,"yuyao ScorpioFragment onCreateView");
        return binding.getRoot();
    }


    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }
}
