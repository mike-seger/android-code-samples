package app.leak_canary_demo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by kalpesh on 20/09/2015.
 */
public class LeakApplication extends Application {
    private RefWatcher refWatcher;
    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        LeakApplication application = (LeakApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}