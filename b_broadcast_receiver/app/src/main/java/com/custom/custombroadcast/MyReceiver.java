package com.custom.custombroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() == "com.tutorialspoint.CUSTOM_INTENT") {
			Toast.makeText(context, "Intent 1 Detected.", Toast.LENGTH_LONG).show();
		} else if (intent.getAction() == "com.tutorialspoint.CUSTOM_INTENT2") {
			Toast.makeText(context, "Intent 2 Detected.", Toast.LENGTH_LONG).show();
		}
	}
}