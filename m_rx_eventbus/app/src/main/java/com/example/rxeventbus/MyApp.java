package com.example.rxeventbus;

import android.app.Application;
import android.os.Handler;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.util.Log;

import com.example.rxeventbus.rxjava.Events;
import com.example.rxeventbus.rxjava.RxEventBus;

/**
 * Created by pye on 12/12/16.
 */

public class MyApp extends Application {
    private RxEventBus bus;

    @Override
    public void onCreate() {
        bus = new RxEventBus();
        Log.d("before", "" + System.currentTimeMillis());
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                bus.send(new Events.Message("Hey I just took a nap, how are you!"));
            }
        }.start();
        Log.d("after ", "" + System.currentTimeMillis());
    }

    public RxEventBus bus() {
        return bus;
    }
}
