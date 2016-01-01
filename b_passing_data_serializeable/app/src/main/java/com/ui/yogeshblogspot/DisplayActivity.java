package com.ui.yogeshblogspot;

import com.service.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		
		//Getting the Student class object from Intent
		Student student=(Student)getIntent().getSerializableExtra("StudentInfo");
		TextView txtRoll=(TextView)findViewById(R.id.txtrollno);//getting the txtrollno TextView Reference to assign rollNo
		TextView txtName=(TextView)findViewById(R.id.txtname);//getting the txtrollno TextView Reference to assign rollNo
		txtRoll.setText(student.getRollNo().toString());//Displaying student.rollNo value to TextView
		txtName.setText(student.getName());//Displaying student.name value to TextView
	}
	//This method will be Called when user clicks on Button Available on the Screen
	public void backToMain(View v)
	{
		//Calling Launcher Activity(Main.class) to Take Another value from user
		Intent in=new Intent(this,Main.class);
		startActivity(in);
		
	}
}
