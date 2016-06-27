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
                .zip(Observable.just(getStrings12()),
                        Observable.just(getStrings34()),
                        Observable.just(getStrings56()),
                        mergeStringLists())
                .subscribeOn(AndroidSchedulers.mainThread())
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
    private List<String> getStrings12() {
        Log.d(TAG, "One Two");
        sleep();

        List<String> strings = new ArrayList<>();
        strings.add("One");
        strings.add("Two");
        return strings;
    }

    private List<String> getStrings34() {
        Log.d(TAG, "Three Four");
        sleep();
        List<String> strings = new ArrayList<>();
        strings.add("Three");
        strings.add("Four");
        return strings;
    }

    private List<String> getStrings56() {
        Log.d(TAG, "Five Six");
        sleep();
        List<String> strings = new ArrayList<>();
        strings.add("Five");
        strings.add("Six");
        return strings;
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
