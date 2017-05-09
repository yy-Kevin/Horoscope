package com.shoplex.bible.horoscope.view.fragment.aries;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.base.BaseFragment;
import com.shoplex.bible.horoscope.databinding.FragmentAriesBinding;
import com.shoplex.bible.horoscope.view.activity.Aries.AriesActivity;
import com.shoplex.bible.horoscope.view.activity.MainActivity;
import com.shoplex.bible.horoscope.view.weight.ObservableScrollView;

/**
 * Created by qsk on 2017/4/26.
 */

public class AriesFragment extends BaseFragment<AriesPresenter> implements AriesContract.IAriesView, View.OnClickListener {

    private FragmentAriesBinding binding;
    final String[] luncky = new String[]{"Lucky Number", "Lucky Direction", "Lucky Color"};
    private PopupWindow window;
    private int number = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

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


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                number++;
                binding.tvLunckly.setText(luncky[number % 3]);
                binding.tvLunckly.postDelayed(this, 3000);
            }
        };
        binding.tvLunckly.postDelayed(runnable, 3000);


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
        binding.rlLunckly.setOnClickListener(this);
        binding.plProgress.setOnClickListener(this);

        final MainActivity activity = (MainActivity) mActivity;

//        tv_lunckly
        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = activity.binding.tlToolbar.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                activity.binding.tlToolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                final int height = activity.binding.tlToolbar.getHeight();


                binding.scroolview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                        if (y <= height) {
                            float scale = (float) y / height;
                            float alpha = (255 * scale);

                            //只是layout背景透明
                            activity.binding.tlToolbar.setBackgroundColor(Color.argb((int) alpha, 0x15, 0x17, 0x44));
                        }
                    }
                });
            }
        });

                startShakeByViewAnim(binding.ivLunckyKnow1, 1.0f,1.5f,30.0f,1000);
                startShakeByViewAnim(binding.ivLunckyKnow2, 1.0f,1.5f,30.0f,1000);
    }


    private void startShakeByViewAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        //TODO 验证参数的有效性

        //由小变大
//        Animation scaleAnim = new ScaleAnimation(scaleSmall, scaleLarge, scaleSmall, scaleLarge);
        //从左向右
        Animation rotateAnim = new RotateAnimation(-shakeDegrees, shakeDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

//        scaleAnim.setDuration(duration);
        rotateAnim.setDuration(duration);
        rotateAnim.setRepeatMode(Animation.REVERSE);
//        rotateAnim.setRepeatCount(10);
        rotateAnim.setRepeatCount(Animation.INFINITE);
//        scaleAnim.setRepeatCount(Animation.INFINITE);


        AnimationSet smallAnimationSet = new AnimationSet(false);
//        smallAnimationSet.addAnimation(scaleAnim);
        smallAnimationSet.addAnimation(rotateAnim);
        smallAnimationSet.setRepeatCount(Animation.INFINITE);


        view.startAnimation(smallAnimationSet);
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


    /**
     * 显示 广告的popupWindow
     */
    public View showPopwindowad(int layout, int gravity) {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);
        ImageView iv_dismiss = (ImageView) view.findViewById(R.id.iv_dismiss);

        iv_dismiss.setOnClickListener(this);
        // 下面是两种方法得到宽度和高度
        // getWindow().getDecorView().getWidth()

        window = new PopupWindow(view);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        backgroundAlpha(0.8f);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        // window.setFocusable(true);

        //点击外面，PopWindowad不消失
        window.setBackgroundDrawable(new BitmapDrawable());

        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(view, gravity, 0, 0);

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        return view;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_dismiss:
                if (window != null)
                    window.dismiss();
                break;

            case R.id.rl_lunckly:
                if (binding.tvLunckly.getText().equals(luncky[0])) {
                    showPopwindowad(R.layout.pop_luncky_number, Gravity.CENTER);
                } else if (binding.tvLunckly.getText().equals(luncky[1])) {
                    showPopwindowad(R.layout.pop_luncky_direction, Gravity.CENTER);

                } else if (binding.tvLunckly.getText().equals(luncky[2])) {
                    showPopwindowad(R.layout.pop_luncky_color, Gravity.CENTER);

                }
                break;

            case R.id.pl_progress:

                Intent intent = new Intent(mActivity, AriesActivity.class);
                mActivity.startActivity(intent);

                break;
        }
    }
}
