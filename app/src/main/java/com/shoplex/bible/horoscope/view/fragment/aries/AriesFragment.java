package com.shoplex.bible.horoscope.view.fragment.aries;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.base.BaseFragment;
import com.shoplex.bible.horoscope.databinding.FragmentAriesBinding;
import com.shoplex.bible.horoscope.view.activity.Aries.AriesActivity;
import com.shoplex.bible.horoscope.view.activity.MainActivity;
import com.shoplex.bible.horoscope.view.weight.ObservableScrollView;

/**
 * Created by qsk on 2017/4/26.
 */

public class AriesFragment extends BaseFragment<AriesPresenter> implements AriesContract.IAriesView {

    private FragmentAriesBinding binding;
    final String[] luncky = new String[]{"Luncky Number", "Luncky Mothle", "Luncky Monkey"};

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
        binding.plProgress.showProgress();

        initSwipeLayout();
        initNet();

        return binding.getRoot();
    }

    public void initNet() {
        mPresenter.loading();
    }

    private void initSwipeLayout() {
        //初始化下拉刷新
        binding.swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initNet();
            }
        });

        //设置星座内容的点击事件
        binding.rlLunckly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,AriesActivity.class);
                mActivity.startActivity(intent);
            }
        });

        final MainActivity activity = (MainActivity) mActivity;
        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = activity.binding.tlToolbar.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                activity.binding.tlToolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                final int height =   activity.binding.tlToolbar.getHeight();


                binding.scroolview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                        //      Log.i("TAG","y--->"+y+"    height-->"+height);
                        if(y <= height){
                            float scale =(float) y /height;
                            float alpha =  (255 * scale);

                            //只是layout背景透明
                            activity.binding.tlToolbar.setBackgroundColor(Color.argb((int) alpha, 0x15, 0x17, 0x44));
                        }
                    }
                });
            }
        });

    }

    @Override
    public void toMainActivity(Object user) {
        binding.plProgress.showContent();
        binding.swipeRefresh.setRefreshing(false);


    }

    @Override
    public void showFailedError() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefresh.setRefreshing(false);
                binding.plProgress.showErrorText(mActivity.getResources().getString(R.string.error));

            }
        }, 2000);
    }

}
