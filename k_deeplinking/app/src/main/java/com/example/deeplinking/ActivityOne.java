package com.example.deeplinking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by pye on 4/19/16.
 *
 *

 adb command to launch activity on the device from shell

 $ adb shell am start
 -W -a android.intent.action.VIEW
 -d <URI> <PACKAGE>


 $ adb shell am start -W -a android.intent.action.VIEW -d "example://activityone?movie=SpiderMan" com.example.deeplinking

 */
public class ActivityOne extends AppCompatActivity {

    TextView tvAction;
    TextView tvData;
    TextView tvQueryParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        tvAction = (TextView) findViewById(R.id.tv_action);
        tvData = (TextView) findViewById(R.id.tv_data);
        tvQueryParam = (TextView) findViewById(R.id.tv_query_param);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Read data from incoming intents
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        tvAction.setText(action);
        if (data != null) {
            tvData.setText(data.toString());
            tvQueryParam.setText(data.getQueryParameter("movie"));
        }
    }
}
