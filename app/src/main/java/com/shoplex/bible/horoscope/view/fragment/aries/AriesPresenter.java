package com.shoplex.bible.horoscope.view.fragment.aries;

import com.shoplex.bible.horoscope.base.BasePresenter;
import com.shoplex.bible.horoscope.bean.HorocopeBean;

/**
 * Created by qsk on 2017/4/26.
 */

public class AriesPresenter extends BasePresenter<AriesContract.IAriesView,AriesModule> {


    public AriesPresenter(AriesContract.IAriesView iAriesView) {
        attachView(iAriesView,new AriesModule());
    }

    public void loading() {

        baseView.showDialog();
        baseModule.loading(new AriesContract.OnLoadingListener<HorocopeBean>() {
            @Override
            public void loginSuccess(HorocopeBean horocopeBean) {
                baseView.hideDialog();
                baseView.toMainActivity(horocopeBean);
            }

            @Override
            public void loginFailed() {
                baseView.hideDialog();
                baseView.showFailedError();
            }
        });

    }

}
