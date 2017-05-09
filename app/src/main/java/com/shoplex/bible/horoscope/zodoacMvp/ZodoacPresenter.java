package com.shoplex.bible.horoscope.zodoacMvp;

import com.shoplex.bible.horoscope.base.BasePresenter;
import com.shoplex.bible.horoscope.bean.HorocopeBean;

/**
 * Created by qsk on 2017/4/25.
 */

public class ZodoacPresenter extends BasePresenter<ZodoacContract.IZodoacView,ZodoacModule>{


    public ZodoacPresenter(ZodoacContract.IZodoacView zodoacView){
        attachView(zodoacView,new ZodoacModule());
    }

    public void zodoacLoading() {

        baseView.showLoading();

        baseModule.loading("", "", new ZodoacContract.OnLoadingListener() {
            @Override
            public void loginSuccess(HorocopeBean user) {
                baseView.toMainActivity(user);
                baseView.hideLoading();
            }

            @Override
            public void loginFailed() {
                baseView.showFailedError();
                baseView.hideLoading();
            }
        });
    }
}
