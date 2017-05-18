package com.shoplex.bible.horoscope.api;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.shoplex.bible.horoscope.utils.MyApplication;
import com.shoplex.bible.horoscope.global.GlobalConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
//                    .addInterceptor(new CacheInterceptor())
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

            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
                    .header("Cache-Control", "max-age=" + 10)
                    .build();
            return response1;

//            Request request = chain.request();
//            if(!isNetworkAvailable(MyApplication.getInstance())){
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build();
//            } else {
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_NETWORK)
//                        .build();
//            }
//            Response originalResponse = chain.proceed(request);
//            if(isNetworkAvailable(MyApplication.getInstance())){
//
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, max-age=" + 0)
//                        .removeHeader("Pragma")
//                        .build();
//            }else{
//
//                int maxTime = 4*24*60*60;
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale="+6)
//                        .removeHeader("Pragma")
//                        .build();
//            }

        }
    }

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
