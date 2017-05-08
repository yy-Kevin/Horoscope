package com.shoplex.bible.horoscope.base;

import com.shoplex.bible.horoscope.bean.HorocopeBean;

/**
 * Created by qsk on 2017/4/26.
 */

public interface IBaseView {

    void toMainActivity(HorocopeBean user);

    void showFailedError();

}
