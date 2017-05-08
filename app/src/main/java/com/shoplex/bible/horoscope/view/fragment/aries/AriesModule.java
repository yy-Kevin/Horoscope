package com.shoplex.bible.horoscope.view.fragment.aries;

import com.shoplex.bible.horoscope.api.ApiManager;
import com.shoplex.bible.horoscope.base.BaseModule;
import com.shoplex.bible.horoscope.bean.HorocopeBean;

import rx.Subscriber;

/**
 * Created by qsk on 2017/4/26.
 */

public class AriesModule extends BaseModule implements AriesContract.IAriesModule {


    @Override
    public void loading(final AriesContract.OnLoadingListener listener) {

        addSubscription(ApiManager.apiManager.getHoroscope(6), new Subscriber<HorocopeBean>() {
            @Override
            public void onCompleted() {}

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

