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
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.subscriptions.Subscriptions;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

//https://medium.com/@kurtisnusbaum/rxandroid-basics-part-1-c0d5edcf6850#.78bi9wfra
//https://github.com/klnusbaum/rxandroidexamples
public class MainActivity extends Activity {
    private final String TAG = "MainActivity";
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












    public void demo(View v) {
        Observable.just("one", "two", "three", "four", "five")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just(s + "1");
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just(s + "2");
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "value>>>" + s);
                    }
                });

    }

}
