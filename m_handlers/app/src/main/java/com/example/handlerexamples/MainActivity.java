package com.example.handlerexamples;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvDisplay = (TextView) findViewById(R.id.tv_display);
        mHandler = new Handler(); // The handler is created here in the UI thread

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                takeNap();
                doFakeWork();
                checkInternetConnection();
            }
        });
    }

    private void doFakeWork() {
        new Thread(new Runnable() {
            @Override
            public void run () {
                // Simulating something timeconsuming
                try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

                // post result back to the UI thread
                mHandler.post(new Runnable() {
                    @Override
                    public void run () {
                        tvDisplay.setText("doFakeWork completed\n" + tvDisplay.getText());
                    }
                });
            }
        }).start();
    }

    private void takeNap() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tvDisplay.setText(msg.obj + "\n" + tvDisplay.getText());
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                // sleep for 5 seconds
                try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
                Message msg = new Message();
                msg.obj = "I've taken a nap!";
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void checkInternetConnection() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final boolean hasConnection = isConnectedToServer("http://www.google.com", 1000);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvDisplay.setText("hasConnection: " + hasConnection + "\n" + tvDisplay.getText());
                    }
                });
            }
        });
        t.start();
    }
    //This will return false because the INTERNET permission is not granted in the manifiest file
    private boolean isConnectedToServer(String url, int timeout) {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle your exceptions
            return false;
        }
    }

}