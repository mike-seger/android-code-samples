package com.example.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class Rx8FlapmapActivity extends AppCompatActivity {
    private TextView mValueDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx5);
        mValueDisplay = (TextView) findViewById(R.id.value_display);
        Log.d("Rx8FlapmapActivity", "onCreate");

        flapMapEx1();
        flapMapEx2();
    }


    // Input 2
    // 1st flapMap  2 * 2
    // 2nd flapMap 4 * 3
    // 3nd flapMap 12 * 4
    // result in the subscribe 48
    private void flapMapEx1() {
        Observable.just(2)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        Log.d("Rx8FlapmapActivity", integer + " * 2");
                        return multiplyInt(integer, 2);
                    }
                })
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        Log.d("Rx8FlapmapActivity", integer + " * 3");
                        return multiplyInt(integer, 3);
                    }
                })
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        Log.d("Rx8FlapmapActivity", integer + " * 4");
                        return multiplyInt(integer, 4);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Rx8FlapmapActivity", "flapMapEx111111 onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("Rx8FlapmapActivity", "flapMapEx111111 onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mValueDisplay.setText(mValueDisplay.getText() + integer.toString() + "\n");
                    }
                });
    }

    private Observable<Integer> multiplyInt(final Integer integer, final int mulplier) {
        for (int i=0; i<1000000000; i++) {}
        return Observable.just(new Integer(integer * mulplier));
    }



    // 1,2,3,4,5,6,7,8,9
    // filter out odd numbers 1, 3, 5, 6, 9
    // pass down the even numbers 2, 4, 6, 8 to the last flatMap
    // the last flapMap multiply each even number by 2
    // the onNext in the subscribe prints 4, 8, 12, 16 one by one
    private void flapMapEx2() {
        List<Integer> ints = new ArrayList<>();
        for (int i=1; i<10; i++) {
            ints.add(new Integer(i));
        }

        Log.d("Rx8FlapmapActivity", "flapMapEx2222222 1,2,3,4,5,6,7,8,9");

        Observable.just(ints)
                .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(List<Integer> ints) {
                        return Observable.from(ints);
                    }
                })
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        Log.d("Rx8FlapmapActivity", "flapMapEx2222222 filter out odd numbers.........");
                        return integer.intValue() % 2 == 0;
                    }
                })
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        for (int i = 0; i < 1000000000; i++) {
                        }
                        return multiplyInt(integer, 2);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Rx8FlapmapActivity", "flapMapEx2222222 onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("Rx8FlapmapActivity", "flapMapEx2222222 onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("Rx8FlapmapActivity", "flapMapEx2222222 onNext>>>" + integer.toString());
                    }
                });
    }


}
