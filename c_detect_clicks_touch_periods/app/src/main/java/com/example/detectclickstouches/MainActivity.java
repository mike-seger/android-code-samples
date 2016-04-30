package com.example.detectclickstouches;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvClicks;
    private TextView tvTouches;
    private long timeElapsed = 0L;
    private int clickCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvClicks = (TextView) findViewById(R.id.tv_clicks);
        tvTouches = (TextView) findViewById(R.id.tv_touches);

        initClicksListener();
        initTouchListener();
    }

    private void initClicksListener() {
        tvClicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCounter++;
                Log.d("setOnTouchListener", "Number of Clicks: "  + clickCounter);
                Toast.makeText(getApplicationContext(), "Number of Clicks: "  + clickCounter, Toast.LENGTH_SHORT).show();
                //TODO do something when a certain number clicks is done
            }
        });
    }

    private void initTouchListener() {
        tvTouches.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        timeElapsed = event.getDownTime();
                        Log.d("setOnTouchListener", "ACTION_DOWN at>>>" + event.getDownTime());
                        break;
                    case MotionEvent.ACTION_UP:
                        timeElapsed = event.getEventTime() - timeElapsed;
                        Log.d("setOnTouchListener", "ACTION_UP at>>>" + event.getEventTime());
                        Log.d("setOnTouchListener", "Period of time the view is pressed>>>" + timeElapsed);
                        Toast.makeText(getApplicationContext(), "Period of time the view is pressed in milliseconds>>>" + timeElapsed, Toast.LENGTH_SHORT).show();
                        timeElapsed = 0L;
                        //TODO do something when a certain period of time has passed
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
