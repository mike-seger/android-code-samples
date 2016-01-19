package com.codexpedia.memoryleaksdemo.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ListenerLeak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the listener
        Utility.getInstance().setListener(new Utility.UpdateListener() {
            @Override
            public void onUpdate() {
                Log.d("ListenerLeak", "Something is updated!");
            }
        });

        //Starting a background thread
        Utility.getInstance().startNewTread();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //comment this line out will create memory leak
        Utility.getInstance().setListener(null);
    }
}
