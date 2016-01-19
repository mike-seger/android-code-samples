package com.codexpedia.memoryleaksdemo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by peng on 1/12/16.
 */
public class MemoryLeakDemo extends Application {
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        MemoryLeakDemo application = (MemoryLeakDemo) context.getApplicationContext();
        return application.refWatcher;
    }


    @Override public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }
}
