package com.shoplex.bible.horoscope.progress;

/**
 * Created by qsk on 2017/4/18.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
