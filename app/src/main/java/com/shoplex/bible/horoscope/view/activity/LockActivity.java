package com.shoplex.bible.horoscope.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shoplex.bible.horoscope.R;
import com.shoplex.bible.horoscope.databinding.ActivityLockBinding;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by qsk on 2017/5/8.
 */

public class LockActivity extends SwipeBackActivity {

    private ActivityLockBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lock);
    }
}
