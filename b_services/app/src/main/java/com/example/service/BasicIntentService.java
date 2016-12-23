package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

/**
 * Created by pye on 12/11/16.
 */

public class BasicIntentService extends IntentService {
    public static final String PARAM_INPUT = "INPUT_MSG";
    public static final String PARAM_OUTPUT = "OUTPUT_MSG";

    public BasicIntentService() {
        super("BasicIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msg = intent.getStringExtra(PARAM_INPUT);
        msg = DateFormat.format("MM/dd/yy h:mm:ss aa", System.currentTimeMillis()) + ": " + msg;
        SystemClock.sleep(5000); // 5 seconds
        String resultTxt = msg + "\n" + DateFormat.format("MM/dd/yy h:mm:ss aa", System.currentTimeMillis()) + ": " + "Ok, done!\n";

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUTPUT, resultTxt);
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("IntentService", "onDestroy");
    }
}