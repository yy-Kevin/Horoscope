package com.shoplex.bible.horoscope.view.fragment.caprocorn;

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
import com.shoplex.bible.horoscope.databinding.FragmentCaprcornBinding;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qsk on 2017/4/26.
 */

public class CaprcornFragment extends BaseFragment {

    private CompositeSubscription mCompositeSubscription;
    private FragmentCaprcornBinding binding;

    public static CaprcornFragment getInstance() {
        CaprcornFragment fragment = new CaprcornFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_caprcorn, container, false);

        Log.i(TAG,"yuyao CaprcornFragment onCreateView");
        return binding.getRoot();
    }

    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
