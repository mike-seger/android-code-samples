package com.example.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Func1;

/**
 * Created by pye on 6/16/16.
 */
public class Rx5SingleJustMapActivity extends AppCompatActivity {
    private TextView mValueDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx5);
        mValueDisplay = (TextView) findViewById(R.id.value_display);

        /*
        We want to eventually display the value our Single emits, so we need to convert it from an Integer to a String.
        One way we can do this is using map(). Like we said above, maps can take in one value and output another.
        This suites our purpose quite well. Since our Single will emit one Integer of value 4, we’ll use map() to convert
        it to a String, and then our Observer will take care of actually displaying it.
        */
        Single.just(4).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer);
            }
        }).subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String value) {
                mValueDisplay.setText(value);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }


}
