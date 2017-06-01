package com.shoplex.bible.horoscope.view.fragment.aries;

import com.shoplex.bible.horoscope.api.ApiManager;
import com.shoplex.bible.horoscope.base.BaseModule;
import com.shoplex.bible.horoscope.bean.HorocopeBean;
import com.shoplex.bible.horoscope.progress.RxSubscribe;

/**
 * Created by qsk on 2017/4/26.
 */

public class AriesModule extends BaseModule implements  AriesContract.IAriesModule {


    @Override
    public void loading(final AriesContract.OnLoadingListener listener) {

        addSubscription(ApiManager.apiManager.getHoroscope(6), new RxSubscribe<HorocopeBean>() {
            @Override
            protected void _onNext(HorocopeBean o) {
                listener.loginSuccess(o);
            }

            @Override
            protected void _onError(String message) {
                listener.loginFailed();
            }
        });
//        ApiManager.apiManager.getHoroscope(6)
//                .compose(RxHelper.<RxBusEvent.HoroBackground>handleResult())
//                .subscribe();
    }
}

