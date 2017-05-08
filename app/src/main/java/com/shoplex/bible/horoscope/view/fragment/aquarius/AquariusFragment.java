package com.shoplex.bible.horoscope.view.fragment.aquarius;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.base.BaseFragment;
import com.shoplex.bible.horoscope.base.BasePresenter;
import com.shoplex.bible.horoscope.databinding.FragmentAquariusBinding;

/**
 * Created by qsk on 2017/4/26.
 */

public class AquariusFragment extends BaseFragment {

    private FragmentAquariusBinding binding;

    public static AquariusFragment getInstance() {
        AquariusFragment fragment = new AquariusFragment();
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_aquarius, container, false);
        return binding.getRoot();
    }

    @Override
    public void toMainActivity(Object user) {

    }

    @Override
    public void showFailedError() {

    }

}
