package com.shoplex.bible.horoscope.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.databinding.ActivityPairBinding;
import com.shoplex.bible.horoscope.utils.SharedPreferencesUtils;

/**
 * Created by qsk on 2017/5/11.
 */

public class PairActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityPairBinding binding;
    private PopupWindow window;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pair);

        setSupportActionBar(binding.tlToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        binding.tlToolbar.setNavigationIcon(R.mipmap.back);

        int drawable1 = (int) SharedPreferencesUtils.get(this,"isFirstSelect",0);
        int drawable2 = (int) SharedPreferencesUtils.get(this,"isSecendSelect",0);
        binding.ivHeart1.setBackgroundResource(drawable1);
        binding.ivHeart2.setBackgroundResource(drawable2);
        binding.ivHeart2.setOnClickListener(this);

        mHandler.postDelayed(mRunnable, 350);

    }
    private Runnable mRunnable = () -> {
        // 弹出PopupWindow的具体代码
        showPopwindowad(R.layout.pop_pair, Gravity.BOTTOM);
    };

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    /**
     * 显示 广告的popupWindow
     */
    public View showPopwindowad(int layout, int gravity) {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view = inflater.inflate(layout, null);
        ImageView iv_dismiss = (ImageView) view.findViewById(R.id.iv_dismiss);

        iv_dismiss.setOnClickListener(this);
        // 下面是两种方法得到宽度和高度
        // getWindow().getDecorView().getWidth()

        window = new PopupWindow(view);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        backgroundAlpha(0.5f);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
//         window.setFocusable(true);

        //点击外面，PopWindowad不消失
        window.setBackgroundDrawable(new BitmapDrawable());

        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(binding.ivHeart2, gravity, 0, 200);

        //popWindow消失监听方法
        window.setOnDismissListener(() -> backgroundAlpha(1.0f));
        return view;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_dismiss:
                window.dismiss();
                window = null;
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (window != null){
            window.dismiss();
            window = null;
            return;
        }
        super.onBackPressed();
    }
}
