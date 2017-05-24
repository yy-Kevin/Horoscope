package com.shoplex.bible.horoscope.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shoplex.bible.horoscope.R;

/**
 * Created by qsk on 2017/5/23.
 */

public class SuccinctProgress {


    private static ProgressDialog progress;


    public static void showSuccinctProgress(Context context, String message,
                                            boolean isCanceledOnTouchOutside, boolean isCancelable) {

        progress = new ProgressDialog(context, R.style.succinctProgressDialog);

        progress.setCanceledOnTouchOutside(isCanceledOnTouchOutside);

        progress.setCancelable(isCancelable);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        View view = LayoutInflater.from(context).inflate(
                R.layout.succinct_progress_content, null);

        ImageView mProgressIcon = (ImageView) view
                .findViewById(R.id.progress_icon);
        TextView mProgressText = (TextView) view
                .findViewById(R.id.progress_message);
        mProgressText.setText(message);
        Animation jumpAnimation = AnimationUtils.loadAnimation(context,
                R.anim.succinct_animation);
        mProgressIcon.startAnimation(jumpAnimation);
        progress.show();
        progress.setContentView(view, params);

    }


    public static boolean isShowing() {
        if (progress != null && progress.isShowing()) {
            return true;
        }
        return false;
    }


    public static void dismiss() {
        if (isShowing()) {
            progress.dismiss();
        }
    }
}
