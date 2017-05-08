package com.shoplex.bible.horoscope.view.activity.Aries;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.databinding.ActivityAriesCommentBinding;

/**
 * Created by qsk on 2017/4/26.
 */

public class DataFragment extends Fragment {

    private ActivityAriesCommentBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_aries_comment, container, false);

        return binding.getRoot();
    }


}
