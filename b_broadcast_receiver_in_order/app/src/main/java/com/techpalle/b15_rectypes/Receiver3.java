package com.techpalle.b15_rectypes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.widget.Toast;

public class Receiver3 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	    // TODO Auto-generated method stub
		Toast.makeText(context, "RCVR 3", 0).show();
		//abortBroadcast();
		setResultData("Hey..");
	}
}
