package com.codexpedia.memoryleaksdemo.samples;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.codexpedia.memoryleaksdemo.R;

public class HandlerRunnableOne extends AppCompatActivity {

    private Handler mLeakyHandler = new Handler();
    private TextView myTextBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);
        myTextBox = (TextView) findViewById(R.id.tv_sample);

        // Post a message and delay its execution for 10 seconds.
        mLeakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myTextBox.setText("Done");
            }
        }, 1000 * 10);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //This resolves the memory leak by removing the handler references.
        mLeakyHandler.removeCallbacksAndMessages(null);
    }
}
