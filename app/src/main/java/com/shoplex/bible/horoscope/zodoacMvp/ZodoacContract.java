package com.shoplex.bible.horoscope.zodoacMvp;

import com.shoplex.bible.horoscope.base.IBaseView;
import com.shoplex.bible.horoscope.base.Presenter;
import com.shoplex.bible.horoscope.bean.HorocopeBean;

/**
 * Created by qsk on 2017/4/26.
 */

public interface ZodoacContract {

    interface OnLoadingListener {

        void loginSuccess(HorocopeBean user);

        void loginFailed();
    }

    interface IZodoacView extends IBaseView {

        void showLoading();

        void hideLoading();
    }

    interface IZodoacModule {
        void loading(String name, String title, ZodoacContract.OnLoadingListener listener);
    }

    interface IZodoacPresenter extends Presenter {

    }
}
