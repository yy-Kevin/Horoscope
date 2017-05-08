package com.shoplex.bible.horoscope.zodoacMvp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.base.BaseActivity;
import com.shoplex.bible.horoscope.bean.HorocopeBean;
import com.shoplex.bible.horoscope.databinding.ActivityZodoacBinding;
import com.shoplex.bible.horoscope.utils.SharedPreferencesUtils;
import com.shoplex.bible.horoscope.utils.ToastUtil;
import com.shoplex.bible.horoscope.view.activity.MainActivity;


/**
 * Created by qsk on 2017/4/25.
 */

public class ZodoacActivity extends BaseActivity<ZodoacPresenter> implements View.OnClickListener, ZodoacContract.IZodoacView {

    private ActivityZodoacBinding binging;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binging = DataBindingUtil.setContentView(this, R.layout.activity_zodoac);
        initView();

        binging.tvAries.setOnClickListener(this);
        binging.tvTaurus.setOnClickListener(this);
        binging.tvGemini.setOnClickListener(this);
        binging.tvCancer.setOnClickListener(this);
        binging.tvLion.setOnClickListener(this);
        binging.tvVirgo.setOnClickListener(this);
        binging.tvLibra.setOnClickListener(this);
        binging.tvScorpio.setOnClickListener(this);
        binging.tvSagittarius.setOnClickListener(this);
        binging.tvCaprcorn.setOnClickListener(this);
        binging.tvAquarius.setOnClickListener(this);
        binging.tvPisces.setOnClickListener(this);
    }

    public void initView() {
        setSupportActionBar(binging.tlZodoac);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected ZodoacPresenter createPresenter() {
        return new ZodoacPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int i = 0;

        switch (v.getId()) {
            //白羊座
            case R.id.tv_aries:
                i = 0;
                break;
            case R.id.tv_taurus:
                i = 1;
                break;
            case R.id.tv_gemini:
                i = 2;
                break;
            case R.id.tv_cancer:
                i = 3;
                break;
            case R.id.tv_lion:
                i = 4;
                break;
            case R.id.tv_virgo:
                i = 5;
                break;
            case R.id.tv_libra:
                i = 6;
                break;
            case R.id.tv_scorpio:
                i = 7;
                break;
            case R.id.tv_sagittarius:
                i = 8;
                break;
            case R.id.tv_caprcorn:
                i = 9;
                break;
            case R.id.tv_aquarius:
                i = 10;
                break;
            case R.id.tv_pisces:
                i = 11;
                break;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SharedPreferencesUtils.put(this, "isZodoac", i);
        finish();


    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void toMainActivity(HorocopeBean user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailedError() {
        ToastUtil.showToast(this, R.string.zodoac_error);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
