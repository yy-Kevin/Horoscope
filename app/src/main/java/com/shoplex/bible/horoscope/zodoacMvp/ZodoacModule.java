package com.shoplex.bible.horoscope.zodoacMvp;

import com.shoplex.bible.horoscope.api.ApiManager;
import com.shoplex.bible.horoscope.base.BaseModule;
import com.shoplex.bible.horoscope.bean.HorocopeBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qsk on 2017/4/25.
 */

public class ZodoacModule extends BaseModule implements ZodoacContract.IZodoacModule {
    @Override
    public void loading(String name, String title, final ZodoacContract.OnLoadingListener listener) {

        ApiManager.apiManager.getHoroscope(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HorocopeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loginFailed();

                    }

                    @Override
                    public void onNext(HorocopeBean horocopeBean) {
                        listener.loginSuccess(horocopeBean);

                    }
                });
    }
}
