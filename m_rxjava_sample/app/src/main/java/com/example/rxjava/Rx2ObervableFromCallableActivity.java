package com.example.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pye on 6/16/16.
 */
public class Rx2ObervableFromCallableActivity extends AppCompatActivity {
    private Subscription mTvShowSubscription;
    private RecyclerView mTvShowListView;
    private ProgressBar mProgressBar;
    private SimpleStringAdapter mSimpleStringAdapter;
    private RestClient mRestClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx2);
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
        mTvShowListView = (RecyclerView) findViewById(R.id.tv_show_list);
        mTvShowListView.setLayoutManager(new LinearLayoutManager(this));
        mSimpleStringAdapter = new SimpleStringAdapter(this);
        mTvShowListView.setAdapter(mSimpleStringAdapter);

        mRestClient = new RestClient(this);
        createObservable();
    }

    private void createObservable() {
        // Observable.fromCallable() allows us to delay the creation of a value to be emitted by an Observable.
        // This is handy when the value you want to emit from your Observable needs to be created off of the UI thread.
        Observable<List<String>> tvShowObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() {
                return mRestClient.getFavoriteTvShows();
            }
        });

        //subscribeOn() allows us to run our value creation code on a specific thread, namely a thread that is not the UI thread.
        //observeOn() allows us to then observe the emitted values of an Observable on an appropriate thread, namely the main UI thread.
        mTvShowSubscription = tvShowObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<List<String>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(List<String> tvShows) {
                                displayTvShows(tvShows);
                            }
                        });
        Log.d("createObservable", "tvShowObservable is subscribed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //We should always unsubscribe our Observers in order to prevent nasty things from happening when we’re using Observables to load things asynchronously.
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
