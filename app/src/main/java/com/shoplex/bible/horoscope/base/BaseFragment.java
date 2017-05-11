package com.shoplex.bible.horoscope.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.view.activity.PairActivity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qsk on 2017/4/26.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IFragmentView, View.OnClickListener {

    public String TAG = getClass().getName();
    private ProgressDialog dialog;
    public Activity mActivity;
    public T mPresenter;
    public static final int MATCHING_HOROSCOPES = 0;
    public RelativeLayout rl_aries;
    public RelativeLayout rl_taurus;
    public RelativeLayout rl_gemini;
    public RelativeLayout rl_cancer;
    public RelativeLayout rl_lion;
    public RelativeLayout rl_virgo;
    public RelativeLayout rl_libra;
    public RelativeLayout rl_scorpio;
    public RelativeLayout rl_sag;
    public RelativeLayout rl_caprorn;
    public RelativeLayout rl_aquarus;
    public RelativeLayout rl_pisces;
    public String isHorcorpes = null;

    private boolean isDouble = false;
    private ImageView iv_aires;
    private Set<RelativeLayout> relativeLayouts = new HashSet<>();
    private ImageView iv_pair2;
    private ImageView iv_pair1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //想让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法，否则Toolbar没有菜单。
        setHasOptionsMenu(true);
        mPresenter = createPresenter();
        mActivity = getActivity();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_meun, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    public void initInClude(View view) {
        rl_aries = (RelativeLayout) view.findViewById(R.id.rl_aries);
        rl_taurus = (RelativeLayout) view.findViewById(R.id.rl_taurus);
        rl_gemini = (RelativeLayout) view.findViewById(R.id.rl_gemini);
        rl_cancer = (RelativeLayout) view.findViewById(R.id.rl_cancer);
        rl_lion = (RelativeLayout) view.findViewById(R.id.rl_lion);
        rl_virgo = (RelativeLayout) view.findViewById(R.id.rl_virgo);
        rl_libra = (RelativeLayout) view.findViewById(R.id.rl_libra);
        rl_scorpio = (RelativeLayout) view.findViewById(R.id.rl_scorpio);
        rl_sag = (RelativeLayout) view.findViewById(R.id.rl_sagittarius);
        rl_caprorn = (RelativeLayout) view.findViewById(R.id.rl_caprorn);
        rl_aquarus = (RelativeLayout) view.findViewById(R.id.rl_aquarus);
        rl_pisces = (RelativeLayout) view.findViewById(R.id.rl_pisces);
        iv_aires = (ImageView) view.findViewById(R.id.iv_aires);
        iv_pair2 = (ImageView) view.findViewById(R.id.iv_pair2);
        iv_pair1 = (ImageView) view.findViewById(R.id.iv_pair1);

        rl_aries.setOnClickListener(this);
        rl_taurus.setOnClickListener(this);
        rl_gemini.setOnClickListener(this);
        rl_cancer.setOnClickListener(this);
        rl_lion.setOnClickListener(this);
        rl_virgo.setOnClickListener(this);
        rl_libra.setOnClickListener(this);
        rl_scorpio.setOnClickListener(this);
        rl_sag.setOnClickListener(this);
        rl_caprorn.setOnClickListener(this);
        rl_aquarus.setOnClickListener(this);
        rl_pisces.setOnClickListener(this);
        iv_aires.setOnClickListener(this);


    }

    public void startShakeByViewAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        Animation rotateAnim = new RotateAnimation(-shakeDegrees, shakeDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(duration);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        rotateAnim.setRepeatCount(Animation.INFINITE);

        AnimationSet smallAnimationSet = new AnimationSet(false);
        smallAnimationSet.addAnimation(rotateAnim);
        smallAnimationSet.setRepeatCount(Animation.INFINITE);

        view.startAnimation(smallAnimationSet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_aries:
                isSelect(rl_aries, R.string.aries ,R.mipmap.aries_map);
                break;
            case R.id.rl_taurus:
                isSelect(rl_taurus, R.string.taurus,R.mipmap.taurus_map);
                break;
            case R.id.rl_gemini:
                isSelect(rl_gemini, R.string.gemini, R.mipmap.gemini_map);
                break;
            case R.id.rl_cancer:
                isSelect(rl_cancer, R.string.cancer, R.mipmap.cancer_map);
                break;
            case R.id.rl_lion:
                isSelect(rl_lion, R.string.lion, R.mipmap.lion_map);
                break;
            case R.id.rl_virgo:
                isSelect(rl_virgo, R.string.virgo,R.mipmap.virgo_map);
                break;
            case R.id.rl_libra:
                isSelect(rl_libra, R.string.libra, R.mipmap.libra_map);
                break;
            case R.id.rl_scorpio:
                isSelect(rl_scorpio, R.string.scorpio,R.mipmap.scorpio_map);
                break;
            case R.id.rl_sagittarius:
                isSelect(rl_sag, R.string.sagittarius,R.mipmap.sagittarius_map);
                break;
            case R.id.rl_caprorn:
                isSelect(rl_caprorn, R.string.caprcorn,R.mipmap.caprorn_map);
                break;
            case R.id.rl_aquarus:
                isSelect(rl_aquarus, R.string.aquarius,R.mipmap.aquarus_map);
                break;
            case R.id.rl_pisces:
                isSelect(rl_pisces, R.string.pisces, R.mipmap.pisces_map);
                break;
        }
    }

    private void isSelect(RelativeLayout layout, int resources, int drawable) {
        relativeLayouts.add(layout);
        if (isDouble && isHorcorpes != getResources().getString(resources)) {
            iv_pair2.setBackgroundResource(drawable);
            layout.setBackgroundResource(R.mipmap.red);
            Intent intent = new Intent(getActivity(), PairActivity.class);
            startActivityForResult(intent, MATCHING_HOROSCOPES);
            isDouble = false;
            isHorcorpes = null;
            return;
        }
        if (isDouble && isHorcorpes == getResources().getString(resources)) {
            layout.setBackgroundResource(R.mipmap.basemap);
            isDouble = false;
        } else {
            layout.setBackgroundResource(R.mipmap.red);
            iv_pair1.setBackgroundResource(drawable);
            isDouble = true;
        }
        isHorcorpes = getResources().getString(resources);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MATCHING_HOROSCOPES:
                for (RelativeLayout rl : relativeLayouts) {
                    rl.setBackgroundResource(R.mipmap.basemap);
                }
                relativeLayouts.clear();
                break;
        }
    }
}
