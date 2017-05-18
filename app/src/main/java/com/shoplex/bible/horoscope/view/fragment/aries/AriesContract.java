package com.shoplex.bible.horoscope.view.fragment.aries;

import com.shoplex.bible.horoscope.base.IFragmentView;

/**
 * Created by yuyao on 2017/4/26.
 */

public interface AriesContract {

    interface OnLoadingListener<T> {

        void loginSuccess(T t);

        void loginFailed();

    }

    interface IAriesView<T> extends IFragmentView<T> {

    }


    interface IAriesModule {

        void loading(AriesContract.OnLoadingListener listener);
    }
}
