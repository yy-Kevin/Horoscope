package com.shoplex.bible.horoscope.api;

import android.content.Context;

import com.shoplex.bible.horoscope.bean.HorocopeBean;
import com.shoplex.bible.horoscope.utils.CacheManager;

import java.io.Serializable;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by qsk on 2017/5/27.
 */

public class RxHelper {

    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<HorocopeBean<T>, T> handleResult() {
        return new Observable.Transformer<HorocopeBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<HorocopeBean<T>> tObservable) {
                return tObservable.flatMap(new Func1<HorocopeBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HorocopeBean<T> result) {
//                        Track.i("result from network : " + result);
//                        if (result.success()) {
//                            return createData(result.data);
//                        } else {
//                            return Observable.error(new ServerException(result.msg));
//                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }
    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(T data) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(data);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });

    }

    public static <T> Observable<T> load(Context context,
                                         final String cacheKey,
                                         final long expireTime,
                                         Observable<T> fromNetwork,
                                         boolean forceRefresh) {
        Observable<T> fromCache = Observable.create((Observable.OnSubscribe<T>) subscriber -> {
            T cache = (T) CacheManager.readObject(context, cacheKey,expireTime);
            if (cache != null) {
                subscriber.onNext(cache);
            } else {
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());


        /**
         *
         * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
         */
        fromNetwork = fromNetwork.map(result -> {
            CacheManager.saveObject(context, (Serializable) result, cacheKey);
            return result;
        });
        if (forceRefresh) {
            return fromNetwork;
        } else {
            return Observable.concat(fromCache, fromNetwork).first();
        }
    }
}
