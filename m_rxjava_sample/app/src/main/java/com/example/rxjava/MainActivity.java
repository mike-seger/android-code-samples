package com.example.rxjava;

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

public class MainActivity extends Activity {
    private static final String TAG = "RxAndroidSamples";

    private Looper backgroundLooper;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();

        findViewById(R.id.button_run_scheduler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRunSchedulerExampleButtonClicked();
            }
        });
    }

    /*
    When the button is clicked it will log the following after 5 seconds.
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(one)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(two)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(three)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(four)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onNext(five)
        06-13 10:22:46.423 16707-16707/? D/RxAndroidSamples: onCompleted()
    * */
    void onRunSchedulerExampleButtonClicked() {
        sampleObservable()
                // Run on a background thread
                .subscribeOn(AndroidSchedulers.from(backgroundLooper))
                        // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
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
