package com.example.fragmentintercomm;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.Menu;
import com.example.fragmentintercomm.FragmentTwo;

public class MainActivity extends Activity implements Communicator {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void respond(String data) {
		FragmentManager fm=getFragmentManager();
		FragmentTwo f2=(FragmentTwo)fm.findFragmentById(R.id.frag2);
		f2.ChangeData(data);
	}
}
