package com.example.fragmentintercomm;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment  {
	private Button incrementBtn;
	private Button decrementBtn;
	private int counter = 0;

	Communicator com; // communication interface object

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			com = (Communicator) activity; //Reference to Main Activity
		} catch (ClassCastException castException) {
			Log.e("FragmentOne", "MainActivity didn't implement the Communicator interface");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_one, container,false);
		incrementBtn = (Button) v.findViewById(R.id.btn1);
		decrementBtn = (Button) v.findViewById(R.id.btn2);

		incrementBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				counter++;
				if (com != null) com.respond("Counter: " + counter); //Call the method in the activity class
			}
		});

		decrementBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				counter--;
				if (com != null) com.respond("Counter: " + counter); //Call the method in the activity class
			}
		});

		return v;
	}
}
