package com.shoplex.bible.horoscope.api;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.shoplex.bible.horoscope.global.GlobalConfig;
import com.shoplex.bible.horoscope.utils.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qsk on 2017/4/17.
 */

public class ApiManager {

    private static final String TAG = "ApiManager";
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    //设置缓存目录
    private static File cacheDirectory = new File(MyApplication.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
        private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);


    public static ApiManagerService apiManager = getRetrofit(GlobalConfig.NEW_RUL).create(ApiManagerService.class);

    public static ApiManagerService  getIntances(String url){
        ApiManagerService apiManager = getRetrofit(url).create(ApiManagerService.class);
        return apiManager;
    }

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getRetrofit(String baseUrl) {

        if (mRetrofit == null) {

            if (mOkHttpClient == null) {
                mOkHttpClient =getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
//                    .cookieJar(new CookiesManager())
                    .addInterceptor(new CacheInterceptor())
                    .addNetworkInterceptor(new CacheInterceptor())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }

    public static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

//            Request request = chain.request();
//            Response response = chain.proceed(request);
//            Response response1 = response.newBuilder()
//                    .removeHeader("Pragma")
//                    .removeHeader("Cache-Control")
//                    //cache for 30 days
//                    .header("Cache-Control", "max-age=" + 10)
//                    .build();
//            return response1;

            Request request = chain.request();

            if (isNetworkAvailable(MyApplication.getInstance())) {
                Response response = chain.proceed(request);
                int maxAge = 6; // 在线缓存在1分钟内可读取
                String cacheControl = request.cacheControl().toString();
                Log.e("yjbo-cache", "在线缓存在1分钟内可读取 11" + cacheControl);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                String cacheControl = request.cacheControl().toString();
                Log.e("yjbo-cache", "离线时缓存时间设置 1" + cacheControl);
                Log.e("yjbo-cache", "离线时缓存时间设置");
                request = request.newBuilder()
                        .cacheControl(FORCE_CACHE1)//此处设置了7秒---修改了系统方法
                        .build();

                Response response = chain.proceed(request);
                //下面注释的部分设置也没有效果，因为在上面已经设置了
                return response.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=50")
                        .build();
            }

        }
    }




    /***
     * 拦截器，保存缓存的方法
     * 2016年7月29日11:22:47
     */
    Interceptor interceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (isNetworkAvailable(MyApplication.getInstance())) {
                Response response = chain.proceed(request);
                int maxAge = 6; // 在线缓存在1分钟内可读取
                String cacheControl = request.cacheControl().toString();
                Log.e("yjbo-cache", "在线缓存在1分钟内可读取" + cacheControl);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                Log.e("yjbo-cache", "离线时缓存时间设置");
                request = request.newBuilder()
                        .cacheControl(FORCE_CACHE1)//此处设置了7秒---修改了系统方法
                        .build();

                Response response = chain.proceed(request);
                //下面注释的部分设置也没有效果，因为在上面已经设置了
                return response.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=50")
                        .build();
            }
        }
    };
    //这是设置在多长时间范围内获取缓存里面
    public static final CacheControl FORCE_CACHE1 = new CacheControl.Builder()
            .onlyIfCached()
            .maxStale(7, TimeUnit.SECONDS)//这里是7s，CacheControl.FORCE_CACHE--是int型最大值
            .build();

    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity == null) {
                Log.i(TAG, "couldn't get connectivity manager");
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].isAvailable()) {
                            Log.i(TAG, "network is available");
                            return true;
                        }
                    }
                }
            }
        }
        Log.i(TAG, "network is not available");
        return false;
    }
}
