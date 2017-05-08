package com.shoplex.bible.horoscope.zodoacMvp;

import com.shoplex.bible.horoscope.base.BasePresenter;
import com.shoplex.bible.horoscope.bean.HorocopeBean;

/**
 * Created by qsk on 2017/4/25.
 */

public class ZodoacPresenter extends BasePresenter<ZodoacContract.IZodoacView,ZodoacModule>{

    private ZodoacModule zodoacModule;

    public ZodoacPresenter(ZodoacContract.IZodoacView zodoacView){
        attachView(zodoacView,new ZodoacModule());
        this.zodoacModule = new ZodoacModule();
    }

    public void zodoacLoading() {

        baseView.showLoading();

        zodoacModule.loading("", "", new ZodoacContract.OnLoadingListener() {
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
