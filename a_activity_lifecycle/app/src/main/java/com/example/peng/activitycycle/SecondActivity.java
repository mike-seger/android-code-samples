package com.example.peng.activitycycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by peng on 10/29/15.
 */
public class SecondActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void nextScreen(View v) {
        Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
        startActivity(i);
    }
}
