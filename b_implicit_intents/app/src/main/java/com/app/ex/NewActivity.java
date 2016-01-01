package com.app.ex;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends Activity{
TextView t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main2);
		
		t1=(TextView)findViewById(R.id.textView1);
		
		//Bundle extras = getIntent().getExtras();
		//if (extras != null) {
			
		    String value = getIntent().getExtras().getString("Name");
		    t1.setText("Welcome "+ value);
		//}
	}

}
