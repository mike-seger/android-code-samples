package com.ui.yogeshblogsppot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
//This Activity will receive data from Main.class Activity
public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);//Setting the Layout
		String name=getIntent().getExtras().getString("name");//Getting name detail That is attached with this intent by Previous Activity
	     Integer age=getIntent().getExtras().getInt("age");//Getting age detail that is attached with this intent by Previous Activity
	    TextView txtname=(TextView)findViewById(R.id.txtname);//Finding reference of txtname TextView to display name that is passed by previous Activity
	    txtname.setText(name);//Setting name to txtname TextView
	    TextView txtage=(TextView)findViewById(R.id.txtage);//Finding reference of txtage TextView to display name that is passed by previous Activity
	    txtage.setText(age.toString());//Setting age to txtage TextBox
	    
		
	}
	
	
	
	

}
