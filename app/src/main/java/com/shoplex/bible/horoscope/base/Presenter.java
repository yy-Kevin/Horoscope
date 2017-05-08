package com.shoplex.bible.horoscope.base;

/**
 * Created by qsk on 2017/4/25.
 */

public interface Presenter<V,T> {


    void attachView(V mvpView,T mvpModule);

    void detachView();
}
