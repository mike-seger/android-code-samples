package com.example.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

//https://medium.com/@kurtisnusbaum/rxandroid-basics-part-1-c0d5edcf6850#.78bi9wfra
//https://github.com/klnusbaum/rxandroidexamples
public class MainActivity extends Activity {
    private Looper backgroundLooper;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
    public void rx1Ex(View v) {
        startActivity(new Intent(this, Rx1DeferActivity.class));
    }

    public void rx2Ex(View v) {
        startActivity(new Intent(this, Rx2ObervableFromCallableActivity.class));
    }

    public void rx3Ex(View v) {
        startActivity(new Intent(this, Rx3SingleFromCallableActivity.class));
    }

    public void rx4Ex(View v) {
        startActivity(new Intent(this, Rx4PublishSubjectActivity.class));
    }

    public void rx5Ex(View v) {
        startActivity(new Intent(this, Rx5SingleJustMapActivity.class));
    }

    public void rx6Ex(View v) {
        startActivity(new Intent(this, Rx6DebounceMapActivity.class));
    }

    public void rx7Ex(View v) {
        startActivity(new Intent(this, Rx7ZipActivity.class));
    }
}
