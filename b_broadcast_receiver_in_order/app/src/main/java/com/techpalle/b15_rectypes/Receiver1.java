package com.techpalle.b15_rectypes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	    // TODO Auto-generated method stub
		String res = getResultData();
		Toast.makeText(context, "RCVR 1.."+res, 0).show();
	}
}
