package com.shoplex.bible.horoscope.view.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.shoplex.bible.horoscope.view.activity.LockActivity;

/**
 * Created by qsk on 2017/5/8.
 */

public class LockServer extends Service {

    private Intent mLockIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenOffReceiver, intentFilter);
    }

    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
        @SuppressWarnings("deprecation")
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() == Intent.ACTION_SCREEN_OFF) {
                mLockIntent = new Intent(context, LockActivity.class);
                mLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(mLockIntent);
            }
        }
    };
}
