package com.example.peng.espressoautomationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    private TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvMsg = (TextView) findViewById(R.id.tv_msg);
        String msg = getIntent().getStringExtra("msg");
        tvMsg.setText(msg);
    }
}
