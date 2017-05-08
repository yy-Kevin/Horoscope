package com.shoplex.bible.horoscope.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.utils.SharedPreferencesUtils;
import com.shoplex.bible.horoscope.zodoacMvp.ZodoacActivity;

/**
 * Created by qsk on 2017/4/25.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        handleSplash();
    }

    public void handleSplash(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int  i = (int) SharedPreferencesUtils.get(SplashActivity.this, "", 0);
                Intent intent = new Intent(SplashActivity.this,ZodoacActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        },1000);
    }
}