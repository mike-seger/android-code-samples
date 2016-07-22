package com.example.rxjava;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by pye on 6/16/16.
 */
public class Rx7ZipActivity extends AppCompatActivity {
    private static final String TAG = "Rx7ZipActivity";
    private Subscription mTvShowSubscription;
    private RecyclerView mTvShowListView;
    private ProgressBar mProgressBar;
    private SimpleStringAdapter mSimpleStringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx7);
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
        mTvShowListView = (RecyclerView) findViewById(R.id.tv_show_list);
        mTvShowListView.setLayoutManager(new LinearLayoutManager(this));
        mSimpleStringAdapter = new SimpleStringAdapter(this);
        mTvShowListView.setAdapter(mSimpleStringAdapter);
        createObservable();
    }


    private void createObservable() {
        mTvShowSubscription =
                Observable
                .zip(getStrings("One", "Two").subscribeOn(Schedulers.newThread()),
                        getStrings("Three", "Four").subscribeOn(Schedulers.newThread()),
                        getStrings("Five", "Six").subscribeOn(Schedulers.newThread()),
                        mergeStringLists())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        displayTvShows(strings);
                    }
                });

    }

    private Func3<List<String>, List<String>, List<String>, List<String>> mergeStringLists() {
        return new Func3<List<String>, List<String>, List<String>, List<String>>() {
            @Override
            public List<String> call(List<String> strings, List<String> strings2, List<String> strings3) {
                Log.d(TAG, "...");

                for (String s : strings2) {
                    strings.add(s);
                }

                for (String s : strings3) {
                    strings.add(s);
                }

                return strings;
            }
        };
    }

    private void sleep() {
        try {
            // "Simulate" the delay of network.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void longRunning() {

        for (int i=0; i<1000000000; i++) {

        }
    }

    private Observable<List<String>> getStrings(final String str1, final String str2) {
        return Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() {
                longRunning();
                Log.d(TAG, Thread.currentThread().getName() + " " + str1 + " " + str2);
                List<String> strings = new ArrayList<>();
                strings.add(str1);
                strings.add(str2);
                return strings;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTvShowSubscription != null && !mTvShowSubscription.isUnsubscribed()) {
            mTvShowSubscription.unsubscribe();
        }
    }

    private void displayTvShows(List<String> tvShows) {
        mSimpleStringAdapter.setStrings(tvShows);
        mProgressBar.setVisibility(View.GONE);
        mTvShowListView.setVisibility(View.VISIBLE);
    }

}
