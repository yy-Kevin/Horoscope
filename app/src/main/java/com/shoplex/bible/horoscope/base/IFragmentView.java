package com.shoplex.bible.horoscope.base;

/**
 * Created by qsk on 2017/4/26.
 */

public interface IFragmentView<T> {

    void toMainActivity(T user);
    void showFailedError();
    void showDialog();
    void hideDialog();
}
