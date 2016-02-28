package com.example.fragmentintercomm;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;

public class MainActivity extends Activity implements Communicator {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void respond(String data) {
		FragmentManager fm = getFragmentManager();
		FragmentTwo f2 = (FragmentTwo) fm.findFragmentById(R.id.frag2);
		f2.updateDisplay(data);
	}
}
