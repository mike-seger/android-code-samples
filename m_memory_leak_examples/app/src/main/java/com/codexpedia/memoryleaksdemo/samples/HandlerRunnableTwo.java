package com.codexpedia.memoryleaksdemo.samples;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.codexpedia.memoryleaksdemo.R;

import java.lang.ref.WeakReference;

public class HandlerRunnableTwo extends AppCompatActivity {

    private Handler mLeakyHandler = new Handler();
    private TextView tvText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);
        tvText = (TextView) findViewById(R.id.tv_sample);

        // Post a message and delay its execution for 10 seconds.
        mLeakyHandler.postDelayed(new MyRunnable(tvText), 1000 * 10);
    }


//    private class MyRunnable implements Runnable {
//        @Override
//        public void run() {
//            tvText.setText("Done");
//        }
//    }
    private static class MyRunnable implements Runnable {
        WeakReference<TextView> tvText;
        public MyRunnable(TextView tvText) {
            this.tvText = new WeakReference<TextView>(tvText);
        }

        @Override
        public void run() {
            TextView mText = tvText.get();
            if (mText != null) {
                mText.setText("Done");
            }
        }
    }
}
