package com.example.rxjava;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by pye on 6/17/16.
 */
public class Rx1DeferActivity extends AppCompatActivity {
    private static final String TAG = "Rx1DeferActivity";
    private Looper backgroundLooper;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx1);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();
    }

    public void defer(View v) {
        onRunSchedulerExampleButtonClicked();
    }

    /*
    When the button is clicked it will log the following after 5 seconds.
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(one)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(two)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(three)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(four)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(five)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onCompleted()
    */
    void onRunSchedulerExampleButtonClicked() {
        sampleObservable()
                // Run on a background thread
                .subscribeOn(AndroidSchedulers.from(backgroundLooper))
                .observeOn(AndroidSchedulers.mainThread()) // Be notified on the main thread
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override
                    public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                });
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    // Do some long running operation
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                Log.d(TAG, "The sleep is over, now produce something");
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
