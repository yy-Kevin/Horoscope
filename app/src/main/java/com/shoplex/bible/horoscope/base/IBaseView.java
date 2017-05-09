package com.shoplex.bible.horoscope.base;

/**
 * Created by qsk on 2017/4/26.
 */

public interface IBaseView<T> {

    void toMainActivity(T user);

    void showFailedError();

    /**
     * 需要刷新的继承此View
     *
     * @param
     */
    interface OnRefreshView<T> extends IBaseView<T> {

        void hideProgress();

        void showProgress();

        void showFoot();

        void hideFoot();
    }

}
