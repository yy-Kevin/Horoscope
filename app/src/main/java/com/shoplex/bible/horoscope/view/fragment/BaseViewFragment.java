package com.shoplex.bible.horoscope.view.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;

import com.shoplex.bible.horoscope.base.BaseFragment;
import com.shoplex.bible.horoscope.base.BasePresenter;

/**
 * Created by qsk on 2017/5/9.
 */

public class BaseViewFragment extends BaseFragment{


    public void startShakeByViewAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration) {
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

    }

    @Override
    public void showFailedError() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
