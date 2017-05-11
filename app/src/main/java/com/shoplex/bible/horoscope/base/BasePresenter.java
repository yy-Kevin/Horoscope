package com.shoplex.bible.horoscope.base;

/**
 * Created by yuyao on 2017/4/25.
 */

public class BasePresenter<V,T extends BaseModule> implements Presenter<V,T>{

    public V baseView;
    public T baseModule;


    @Override
    public void attachView(V mvpView,T mvpModule) {
        this.baseView = mvpView;
        this.baseModule = mvpModule;
    }

    @Override
    public void detachView() {
        baseModule.detachView();
        this.baseView = null;
        this.baseModule = null;
    }

}
