package com.shoplex.bible.horoscope.view.fragment.aries;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.api.RxBus;
import com.shoplex.bible.horoscope.base.BaseFragment;
import com.shoplex.bible.horoscope.bean.DataBean;
import com.shoplex.bible.horoscope.databinding.FragmentAriesBinding;
import com.shoplex.bible.horoscope.utils.ToastUtil;
import com.shoplex.bible.horoscope.view.activity.Aries.AriesActivity;

/**
 * Created by qsk on 2017/4/26.
 */

public class AriesFragment extends BaseFragment<AriesPresenter> implements AriesContract.IAriesView, View.OnClickListener {

    private FragmentAriesBinding binding;
    private RelativeLayout viewById;
    private TextView tv_comment;

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
        tv_comment = (TextView) binding.ilIncludeData.findViewById(R.id.tv_comment);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(false);

        if (viewById != null){
            Log.i(TAG,"error eooror ro or 2");
        }
        Log.i(TAG,"error eooror ro or ");
//        viewById.setOnClickListener(mActivity);
        tv_comment.setOnClickListener(this);
        initInClude(binding.ilInclude);
//        initLucky(binding.ilIncludeLuncky);
        initSwipeLayout(this,binding.swipeRefresh,binding.scroolview);
        setAnimailOnIntroduction();

//        binding.plProgress.showContent();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initNet() {
//        binding.plProgress.showProgress();
        mPresenter.loading();

    }

    @Override
    public void onResume() {
        initLucky(binding.ilIncludeLuncky);
        super.onResume();
    }

    @Override
    public void toMainActivity(Object user) {
//        binding.plProgress.showContent();
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showFailedError() {
        binding.swipeRefresh.setRefreshing(false);
//        binding.plProgress.showErrorText(mActivity.getResources().getString(R.string.error));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Log.i(TAG,"yuyao aries + = " + v.getId());
        switch (v.getId()) {
            case R.id.tv_comment:
                ToastUtil.showToast(mActivity,"hahaha");

                break;
            case R.id.rl_lunckly_data:
                Log.i(TAG,"yuyao aries 111");

                DataBean bean = new DataBean();
                bean.setBackground(R.mipmap.bg_aries);
                Intent intent = new Intent(mActivity, AriesActivity.class);
                RxBus.getInstance().post(bean);
                mActivity.startActivity(intent);
                break;
        }
    }


    int maxDescripLine = 3; //TextView默认最大展示行数

    private void setAnimailOnIntroduction(){

        binding.tvWeekly2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                binding.tvWeekly3.setVisibility(binding.tvWeekly2.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
                if(binding.tvWeekly3.getLineCount()>0){
                    binding.tvWeekly3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

        if (binding.tvWeekly3.getVisibility() == View.VISIBLE)
        binding.rlWeekly2.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                binding.tvWeekly2.clearAnimation();//清楚动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = binding.tvWeekly2.getHeight();//起始高度
                int durationMillis = 350;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = binding.tvWeekly2.getLineHeight() * binding.tvWeekly2.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    binding.tvWeekly3.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = binding.tvWeekly2.getLineHeight() * maxDescripLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    binding.tvWeekly3.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        binding.tvWeekly2.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                binding.tvWeekly2.startAnimation(animation);
            }
        });
    }
}
