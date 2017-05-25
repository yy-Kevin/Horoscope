package com.shoplex.bible.horoscope.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.base.BaseActivity;
import com.shoplex.bible.horoscope.base.BasePresenter;
import com.shoplex.bible.horoscope.databinding.ActivityMainBinding;
import com.shoplex.bible.horoscope.utils.SharedPreferencesUtils;
import com.shoplex.bible.horoscope.utils.ToastUtil;
import com.tencent.bugly.Bugly;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public ActivityMainBinding binding;
    private List<ImageView> dotViewLists = new ArrayList<>();
    private ImageView imageView;
    private static boolean isExit = false;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Bugly.init(getApplicationContext(), "386b03031f", true);

        initSmallDots();
        initFragment();
    }

    @Override
    public void onBackPressed() {
        isExit();
        super.onBackPressed();
    }

    public void isExit(){
        if (!isExit) {
            isExit = true;
            ToastUtil.showToast(this,"再按一次退出");
            mHandler.sendEmptyMessageAtTime(0,2000);

        } else {
            finish();
            System.exit(0);
        }


    }
    private void initSmallDots(){
        for (int i = 0; i < 12; i++) {
            imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

            //为小圆点左右添加间距
            params.leftMargin = 10;
            params.rightMargin = 10;
            //手动给小圆点一个大小
            params.height = 16;
            params.width = 16;
            if (i == (int)SharedPreferencesUtils.get(this, "isZodoac", 0)) {
//                imageView.setBackgroundResource(img1);
//                imageView.setBackgroundColor(Color.WHITE);
                imageView.setBackgroundResource(R.mipmap.white_cicle);

            } else {
                imageView.setBackgroundResource(R.mipmap.cicle);
//                imageView.setBackgroundColor(Color.RED);

            }
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.vpMainViewpager.setCurrentItem(finalI,false);
                    selectFragment(finalI);
                }
            });
            //为LinearLayout添加ImageView
            binding.llMainLinear.addView(imageView, params);
            dotViewLists.add(imageView);
        }
    }

    private void initFragment() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        binding.vpMainViewpager.setAdapter(mainAdapter);
        binding.vpMainViewpager.setOffscreenPageLimit(0);
        int current = (int) SharedPreferencesUtils.get(this,"isZodoac",0);
        binding.vpMainViewpager.setCurrentItem(current);
        selectFragment(current);

        binding.vpMainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectFragment(position);
                for (int i = 0; i < 12; i++) {
                    //选中的页面改变小圆点为选中状态，反之为未选中
                    if ((position % 12) == i) {
                        dotViewLists.get(i).setBackgroundResource(R.mipmap.white_cicle);
                    } else {
                        dotViewLists.get(i).setBackgroundResource(R.mipmap.cicle);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void selectFragment(int position){
        switch (position) {
            case 0:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.aries));
                break;
            case 1:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.taurus));
                break;
            case 2:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.gemini));
                break;
            case 3:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.cancer));
                break;
            case 4:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.lion));
                break;
            case 5:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.virgo));
                break;
            case 6:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.libra));
                break;
            case 7:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.scorpio));
                break;
            case 8:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.sagittarius));
                break;
            case 9:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.caprcorn));
                break;
            case 10:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.aquarius));
                break;
            case 11:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.pisces));
                break;
            default:
                initToolbar(binding.tlToolbar, getResources().getString(R.string.aries));
                break;
        }
    }
}
