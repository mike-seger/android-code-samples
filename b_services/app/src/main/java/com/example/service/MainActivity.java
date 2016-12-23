package com.example.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //registering the BroadcastReceiver for the BasicIntentService
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
    }


    /************************************************************************************************
     * SimpleService example
     * a simple service is referred as unbound service or started service
     * service doesn't have an UI but it is running on the UI thread
     * service is started by calling startService
     * service needs to be stopped manually by calling stopService
     ************************************************************************************************/
    public void startService(View view) {
        startService(new Intent(getBaseContext(), SimpleService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), SimpleService.class));
    }
    /************************************************************************************************
     * end of SimpleService example
     ************************************************************************************************/



    /************************************************************************************************
     * BoundService example
     * The onStart and onStop are activity callbacks, they are here to include bind and unbind calls to
     * the service
     *
     * bound service is started by calling bindService
     * bound service doesn't have an UI and it also running on the UI thread like a regular service
     * bound service will stop when no components are binded to it anymore
     * bound service can have multiple components bind to it
     * bound service has components such as activity bind to it through a ServiceConnection, and use it to for communication
     * bound service need an custom class which extends Binder
     ************************************************************************************************/
    BoundService mboundService;
    boolean mBound = false;
    public void boundService(View view) {
        String randomNum = mboundService.getRandomNum();
        Log.d("boundService", randomNum);
        Toast.makeText(this, randomNum, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mboundService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
    /************************************************************************************************
     * end of BoundService example
     ************************************************************************************************/




    /************************************************************************************************
     * BasicIntentService example
     * Unlike regular service and bound servie, Intent Service is doesn't run on the UI thread.
     * To create an Intent Service class, the class needs to extend from the IntentService.
     * IntentService itself is extended from Service.
     * Intent Service will stop itself when the tasks are completed.
     * Intent Service handle all start asynchronous requests (expressed as Intents) on demand, one at a time before it destroys itself.
     * The communication between intent service can be done through interface, broadcastreceiver, or event bus libraries.
     ************************************************************************************************/
    private ResponseReceiver receiver;
    public void intentService(View view) {
        Log.d("intentService", "calling BasicIntentService");
        Intent msgIntent = new Intent(this, BasicIntentService.class);
        msgIntent.putExtra(BasicIntentService.PARAM_INPUT, "Hello, please go to take a nap.");
        startService(msgIntent);
    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "com.example.service.action.MESSAGE_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {
            TextView result = (TextView) findViewById(R.id.tv_result);
            String text = intent.getStringExtra(BasicIntentService.PARAM_OUTPUT);
            Log.d("onReceive", text);
            result.setText(result.getText().toString() + "\n" + text);
        }
    }
    /************************************************************************************************
     * end of BasicIntentService example
     ************************************************************************************************/

}
