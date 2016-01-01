package com.custom.custombroadcast;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class CustomActivity extends Activity {

	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.custom_broadcast);
	   }

	   // broadcast a custom intent. 
	   public void broadcastIntent(View view) {
	      Intent intent = new Intent();
	      intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
	      sendBroadcast(intent);
	   }

		// broadcast a custom intent.
		public void broadcastIntent2(View view) {
			Intent intent = new Intent();
			intent.setAction("com.tutorialspoint.CUSTOM_INTENT2");
			sendBroadcast(intent);
		}
}