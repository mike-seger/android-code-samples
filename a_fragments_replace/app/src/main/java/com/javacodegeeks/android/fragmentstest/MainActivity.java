package com.javacodegeeks.android.fragmentstest;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class MainActivity extends Activity {
	private FragmentOne fragmentSimple;
	private final String SIMPLE_FRAGMENT_TAG = "myfragmenttag";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_main);
//Screen orientation change: fragment crashes
		//use android:configChanges="keyboardHidden|orientation|screenSize" this prevents activity from recreated
	}
	 
	public void selectFrag(View view) {
		 Fragment fr;
		 
		 if(view == findViewById(R.id.button1)) {
			 fr = new FragmentTwo();
		 } else if(view == findViewById(R.id.button2)){
			 fr = new FragmentOne();
		 } else {
			 fr = new FragmentThree();
		 }
		 
		 FragmentManager fm = getFragmentManager();
	     FragmentTransaction fragmentTransaction = fm.beginTransaction();
	     fragmentTransaction.replace(R.id.fragment_place, fr);
	     fragmentTransaction.commit();
		 
	}
   
}
