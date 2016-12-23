package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * Created by pye on 12/11/16.
 */

public class BoundService extends Service {
    private static String LOG_TAG = "BoundService";
    private IBinder mBinder;
    private Random mGenerator;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        mBinder = new LocalBinder();
        mGenerator = new Random();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
        mGenerator = null;
    }

    public String getRandomNum() {
        return "Random Number: " + mGenerator.nextInt(10000);
    }

    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
