package com.shoplex.bible.horoscope.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.romainpiel.shimmer.Shimmer;
import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.databinding.ActivityLockBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Created by qsk on 2017/5/8.
 */

public class LockActivity extends SwipeBackActivity {


    private ActivityLockBinding binding;
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String str = (String) msg.obj;
            String[] strData = str.split(" ");
            binding.tvTime1.setText(strData[1]);

            String wek = "";
            String moh = "";
            switch (msg.arg1) {
                case 0:
                    wek = week[msg.arg1];
                    break;
                case 1:
                    wek = week[msg.arg1];
                    break;
                case 2:
                    wek = week[msg.arg1];
                    break;
                case 3:
                    wek = week[msg.arg1];
                    break;
                case 4:
                    wek = week[msg.arg1];
                    break;
                case 5:
                    wek = week[msg.arg1];
                    break;
                case 6:
                    wek = week[msg.arg1];
                    break;
            }
            switch (msg.arg2) {
                case 0:
                    moh = mothun[msg.arg2];
                    break;
                case 1:
                    moh = mothun[msg.arg2];
                    break;
                case 2:
                    moh = mothun[msg.arg2];
                    break;
                case 3:
                    moh = mothun[msg.arg2];
                    break;
                case 4:
                    moh = mothun[msg.arg2];
                    break;
                case 5:
                    moh = mothun[msg.arg2];
                    break;
                case 6:
                    moh = mothun[msg.arg2];
                    break;
                case 7:
                    moh = mothun[msg.arg2];
                    break;
                case 8:
                    moh = mothun[msg.arg2];
                    break;
                case 9:
                    moh = mothun[msg.arg2];
                    break;
                case 10:
                    moh = mothun[msg.arg2];
                    break;
                case 11:
                    moh = mothun[msg.arg2];
                    break;
            }

            String arg = wek + ", " + moh + " " + strData[0];
            binding.tvTime2.setText(arg);
        }
    };
    private String[] week;
    private String[] mothun;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lock);
        week = getResources().getStringArray(R.array.select_week);
        mothun = getResources().getStringArray(R.array.select_month);
        initTime();

//        getSwipeBackLayout().setEnableGesture(true);//关闭右滑返回上一级
//        getSwipeBackLayout().setSwipeMode(SwipeBackLayout.FULL_SCREEN_LEFT);
        Shimmer shimmer = new Shimmer();
        shimmer.start(binding.shimmerTv);
        getSwipeBackLayout().setSwipeMode(SwipeBackLayout.FULL_SCREEN_LEFT);
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        }
    }


    public void initTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd HH:mm");
//                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Message msg = new Message();
                        msg.obj = sdf.format(new Date());

                        Calendar c = Calendar.getInstance();
                        int mMonth = c.get(Calendar.MONTH);//获取当前月份;
                        int mWeek = c.get(Calendar.DAY_OF_WEEK);//获取周;
                        if (Calendar.MONDAY == mWeek) {
                            msg.arg1 = 0;
                        } else if (Calendar.TUESDAY == mWeek) {
                            msg.arg1 = 1;
                        } else if (Calendar.WEDNESDAY == mWeek) {
                            msg.arg1 = 2;
                        } else if (Calendar.THURSDAY == mWeek) {
                            msg.arg1 = 3;
                        } else if (Calendar.FRIDAY == mWeek) {
                            msg.arg1 = 4;
                        } else if (Calendar.SATURDAY == mWeek) {
                            msg.arg1 = 5;
                        } else if (Calendar.SUNDAY == mWeek) {
                            msg.arg1 = 6;
                        }
                        switch (mMonth) {
                            case Calendar.JANUARY:
                                msg.arg2 = 0;
                                break;
                            case Calendar.FEBRUARY:
                                msg.arg2 = 1;
                                break;
                            case Calendar.MARCH:
                                msg.arg2 = 2;
                                break;
                            case Calendar.APRIL:
                                msg.arg2 = 3;
                                break;
                            case Calendar.MAY:
                                msg.arg2 = 4;
                                break;
                            case Calendar.JUNE:
                                msg.arg2 = 5;
                                break;
                            case Calendar.JULY:
                                msg.arg2 = 6;
                                break;
                            case Calendar.AUGUST:
                                msg.arg2 = 7;
                                break;
                            case Calendar.SEPTEMBER:
                                msg.arg2 = 8;
                                break;
                            case Calendar.OCTOBER:
                                msg.arg2 = 9;
                                break;
                            case Calendar.NOVEMBER:
                                msg.arg2 = 10;
                                break;
                            case Calendar.DECEMBER:
                                msg.arg2 = 11;
                                break;
                        }

                        handler.sendMessage(handler.obtainMessage(100, msg.arg1, msg.arg2, msg.obj));

                        Thread.sleep(1000 * 60);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
