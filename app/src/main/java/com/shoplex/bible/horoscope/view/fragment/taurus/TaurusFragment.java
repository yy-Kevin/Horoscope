package com.shoplex.bible.horoscope.view.fragment.taurus;

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
import com.shoplex.bible.horoscope.databinding.FragmentTaurusBinding;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qsk on 2017/4/26.
 */

public class TaurusFragment extends BaseFragment  {

    private CompositeSubscription mCompositeSubscription;
    private FragmentTaurusBinding binding;

    public static TaurusFragment getInstance() {
        TaurusFragment fragment = new TaurusFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_taurus, container, false);
        Log.i(TAG,"yuyao TaurusFragment onCreateView");
//        initToolbar(R.id.tl_toolbar);
        startShakeByViewAnim(binding.ivLunckyKnow1, 1.0f,1.5f,30.0f,1000);
        startShakeByViewAnim(binding.ivLunckyKnow2, 1.0f,1.5f,30.0f,1000);
        initInClude(binding.ilInclude);
        return binding.getRoot();
    }

    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }
}
