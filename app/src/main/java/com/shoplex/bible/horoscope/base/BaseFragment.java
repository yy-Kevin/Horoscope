package com.shoplex.bible.horoscope.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoplex.bible.horoscope.R;

/**
 * Created by qsk on 2017/4/26.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IFragmentView{

    public String TAG = getClass().getName();
    private ProgressDialog dialog;
    public Activity mActivity;
    public T mPresenter;


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
        if (mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    public Toolbar initToolbar(int title) {
        AppCompatActivity mAppCompatActivity = (AppCompatActivity) mActivity;
        Toolbar toolbar = (Toolbar) mAppCompatActivity.findViewById(title);
        mAppCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("title");
        }

        return toolbar;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_meun, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


    public void showProgress() {

    }


    public void hideProgress() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
