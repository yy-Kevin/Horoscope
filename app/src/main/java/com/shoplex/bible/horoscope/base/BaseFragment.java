package com.shoplex.bible.horoscope.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.progress.SuccinctProgress;
import com.shoplex.bible.horoscope.utils.SharedPreferencesUtils;
import com.shoplex.bible.horoscope.view.activity.MainActivity;
import com.shoplex.bible.horoscope.view.activity.PairActivity;
import com.shoplex.bible.horoscope.view.server.LockServer;
import com.shoplex.bible.horoscope.view.weight.ObservableScrollView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qsk on 2017/4/26.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IFragmentView, View.OnClickListener {


    private final String[] luncky = new String[]{"Lucky Number", "Lucky Direction", "Lucky Color"};
    public String TAG = getClass().getName();
    private ProgressDialog dialog;
    public Activity mActivity;
    public T mPresenter;
    public static final int MATCHING_HOROSCOPES = 0;
    public RelativeLayout rl_aries;
    public RelativeLayout rl_taurus;
    public RelativeLayout rl_gemini;
    public RelativeLayout rl_cancer;
    public RelativeLayout rl_lion;
    public RelativeLayout rl_virgo;
    public RelativeLayout rl_libra;
    public RelativeLayout rl_scorpio;
    public RelativeLayout rl_sag;
    public RelativeLayout rl_caprorn;
    public RelativeLayout rl_aquarus;
    public RelativeLayout rl_pisces;
    public String isHorcorpes = null;

    private boolean isDouble = false;
    private ImageView iv_aires;
    private Set<RelativeLayout> relativeLayouts = new HashSet<>();
    private ImageView iv_pair2;
    private ImageView iv_pair1;
    private View mRootView;
    private int number = 0;
    private TextView tv_lunckly;
    private PopupWindow window;
    private ProgressDialog progressDialog;
    private Runnable runnable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //想让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法，否则Toolbar没有菜单。
        setHasOptionsMenu(true);
        mPresenter = createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        Intent intent = new Intent(mActivity, LockServer.class);
        mActivity.startService(intent);
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_meun, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showDialog() {
//        showProgressDialog();
        SuccinctProgress.showSuccinctProgress(getActivity(),"hahaha",false,false);
    }

    @Override
    public void hideDialog() {
//        dismissProgressDialog();
        SuccinctProgress.dismiss();
    }

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }
    public void initSwipeLayout(final BaseFragment fragment, final SwipeRefreshLayout refresh, final ObservableScrollView scroolview) {
        //初始化下拉刷新
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fragment.initNet();
                refresh.setRefreshing(false);

            }
        });

        //设置星座内容的点击事件
        refresh.setOnClickListener(this);

        final MainActivity activity = (MainActivity) getActivity();

//        tv_lunckly
        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = activity.binding.tlToolbar.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                activity.binding.tlToolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                final int height = activity.binding.tlToolbar.getHeight();


                scroolview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                        if (y <= height) {
                            float scale = (float) y / height;
                            float alpha = (255 * scale);
                            //只是layout背景透明
                            activity.binding.tlToolbar.setBackgroundColor(Color.argb((int) alpha, 0x15, 0x17, 0x44));
                        }
                    }
                });
            }
        });


    }

    public void initNet() {}

    public void initLucky(View view) {

        tv_lunckly = (TextView) view.findViewById(R.id.tv_lunckly);
        RelativeLayout rl_lunckly = (RelativeLayout) view.findViewById(R.id.rl_lunckly);
        final ImageView iv1 = (ImageView) view.findViewById(R.id.iv_luncky_know1);
        final ImageView iv2 = (ImageView) view.findViewById(R.id.iv_luncky_know2);
        runnable = new Runnable() {
            @Override
            public void run() {
                number++;
                tv_lunckly.setText(luncky[number % 3]);
                tv_lunckly.postDelayed(this, 3000);
            }
        };
        tv_lunckly.postDelayed(runnable, 3000);

        rl_lunckly.setOnClickListener(this);

        startShakeByViewAnim(iv1, 1.0f, 1.5f, 30.0f, 1000);
        startShakeByViewAnim(iv2, 1.0f, 1.5f, 30.0f, 1000);
    }

    @Override
    public void onPause() {
        if (tv_lunckly !=null && runnable != null){
            tv_lunckly.removeCallbacks(runnable);
        }
        super.onPause();
    }

    /**
     * 星座配对初始化 以及设置点击事件
     * @param view
     */
    public void initInClude(View view) {
        rl_aries = (RelativeLayout) view.findViewById(R.id.rl_aries);
        rl_taurus = (RelativeLayout) view.findViewById(R.id.rl_taurus);
        rl_gemini = (RelativeLayout) view.findViewById(R.id.rl_gemini);
        rl_cancer = (RelativeLayout) view.findViewById(R.id.rl_cancer);
        rl_lion = (RelativeLayout) view.findViewById(R.id.rl_lion);
        rl_virgo = (RelativeLayout) view.findViewById(R.id.rl_virgo);
        rl_libra = (RelativeLayout) view.findViewById(R.id.rl_libra);
        rl_scorpio = (RelativeLayout) view.findViewById(R.id.rl_scorpio);
        rl_sag = (RelativeLayout) view.findViewById(R.id.rl_sagittarius);
        rl_caprorn = (RelativeLayout) view.findViewById(R.id.rl_caprorn);
        rl_aquarus = (RelativeLayout) view.findViewById(R.id.rl_aquarus);
        rl_pisces = (RelativeLayout) view.findViewById(R.id.rl_pisces);
        iv_aires = (ImageView) view.findViewById(R.id.iv_aires);
        iv_pair2 = (ImageView) view.findViewById(R.id.iv_pair2);
        iv_pair1 = (ImageView) view.findViewById(R.id.iv_pair1);

        rl_aries.setOnClickListener(this);
        rl_taurus.setOnClickListener(this);
        rl_gemini.setOnClickListener(this);
        rl_cancer.setOnClickListener(this);
        rl_lion.setOnClickListener(this);
        rl_virgo.setOnClickListener(this);
        rl_libra.setOnClickListener(this);
        rl_scorpio.setOnClickListener(this);
        rl_sag.setOnClickListener(this);
        rl_caprorn.setOnClickListener(this);
        rl_aquarus.setOnClickListener(this);
        rl_pisces.setOnClickListener(this);
        iv_aires.setOnClickListener(this);


    }

    public void startShakeByViewAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        Animation rotateAnim = new RotateAnimation(-shakeDegrees, shakeDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(duration);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        rotateAnim.setRepeatCount(Animation.INFINITE);

        AnimationSet smallAnimationSet = new AnimationSet(false);
        smallAnimationSet.addAnimation(rotateAnim);
        smallAnimationSet.setRepeatCount(Animation.INFINITE);

        view.startAnimation(smallAnimationSet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_dismiss:
                if (window != null)
                    window.dismiss();
                break;

            case R.id.rl_lunckly:
                if (tv_lunckly.getText().equals(luncky[0])) {
                    showPopwindowad(R.layout.pop_luncky_number, Gravity.CENTER);
                } else if (tv_lunckly.getText().equals(luncky[1])) {
                    showPopwindowad(R.layout.pop_luncky_direction, Gravity.CENTER);
                } else if (tv_lunckly.getText().equals(luncky[2])) {
                    showPopwindowad(R.layout.pop_luncky_color, Gravity.CENTER);
                }
                break;

            case R.id.rl_aries:
                isSelect(rl_aries, R.string.aries, R.mipmap.aries_map);
                break;
            case R.id.rl_taurus:
                isSelect(rl_taurus, R.string.taurus, R.mipmap.taurus_map);
                break;
            case R.id.rl_gemini:
                isSelect(rl_gemini, R.string.gemini, R.mipmap.gemini_map);
                break;
            case R.id.rl_cancer:
                isSelect(rl_cancer, R.string.cancer, R.mipmap.cancer_map);
                break;
            case R.id.rl_lion:
                isSelect(rl_lion, R.string.lion, R.mipmap.lion_map);
                break;
            case R.id.rl_virgo:
                isSelect(rl_virgo, R.string.virgo, R.mipmap.virgo_map);
                break;
            case R.id.rl_libra:
                isSelect(rl_libra, R.string.libra, R.mipmap.libra_map);
                break;
            case R.id.rl_scorpio:
                isSelect(rl_scorpio, R.string.scorpio, R.mipmap.scorpio_map);
                break;
            case R.id.rl_sagittarius:
                isSelect(rl_sag, R.string.sagittarius, R.mipmap.sagittarius_map);
                break;
            case R.id.rl_caprorn:
                isSelect(rl_caprorn, R.string.caprcorn, R.mipmap.caprorn_map);
                break;
            case R.id.rl_aquarus:
                isSelect(rl_aquarus, R.string.aquarius, R.mipmap.aquarus_map);
                break;
            case R.id.rl_pisces:
                isSelect(rl_pisces, R.string.pisces, R.mipmap.pisces_map);
                break;
        }
    }

    private void isSelect(RelativeLayout layout, int resources, int drawable) {
        relativeLayouts.add(layout);
        if (isDouble && isHorcorpes != mActivity.getString(resources)) {
            iv_pair2.setBackgroundResource(drawable);
            SharedPreferencesUtils.put(getActivity(), "isSecendSelect", drawable);
            layout.setBackgroundResource(R.mipmap.red);
            Intent intent = new Intent(getActivity(), PairActivity.class);
            startActivityForResult(intent, MATCHING_HOROSCOPES);
            isDouble = false;
            isHorcorpes = null;
            return;
        }
        if (isDouble && isHorcorpes == getResources().getString(resources)) {
            layout.setBackgroundResource(R.mipmap.basemap);
            isDouble = false;
        } else {
            layout.setBackgroundResource(R.mipmap.red);
            iv_pair1.setBackgroundResource(drawable);
            SharedPreferencesUtils.put(getActivity(), "isFirstSelect", drawable);
            isDouble = true;
        }
        isHorcorpes = getResources().getString(resources);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MATCHING_HOROSCOPES:
                for (RelativeLayout rl : relativeLayouts) {
                    rl.setBackgroundResource(R.mipmap.basemap);
                }
                relativeLayouts.clear();
                break;
        }
    }

    /**
     * 显示 广告的popupWindow
     */
    public View showPopwindowad(int layout, int gravity) {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);
        ImageView iv_dismiss = (ImageView) view.findViewById(R.id.iv_dismiss);

        iv_dismiss.setOnClickListener(this);
        // 下面是两种方法得到宽度和高度
        // getWindow().getDecorView().getWidth()

        window = new PopupWindow(view);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        backgroundAlpha(0.8f);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        // window.setFocusable(true);

        //点击外面，PopWindowad不消失
        window.setBackgroundDrawable(new BitmapDrawable());

        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(view, gravity, 0, 0);

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        return view;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }
}
