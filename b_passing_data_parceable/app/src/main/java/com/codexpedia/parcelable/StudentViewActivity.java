package com.codexpedia.parcelable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.codexpedia.parcelable.model.Major;
import com.codexpedia.parcelable.model.Student;


public class StudentViewActivity extends Activity {
	
	private TextView tvSName;
	private TextView tvEmail;
	private TextView tvAge;
	private TextView tvMajor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		Student student = getIntent().getParcelableExtra("student");
		Major major;
		tvSName = (TextView)findViewById(R.id.tv_sname);
		tvEmail = (TextView)findViewById(R.id.tv_semail);
		tvAge = (TextView) findViewById(R.id.tv_sage);
		tvMajor = (TextView) findViewById(R.id.tv_major);
		if (student != null) {
			tvSName.setText("Name:" + student.getName());
			tvEmail.setText("Email:" + student.getEmail());
			tvAge.setText("Age:" + Integer.toString(student.getAge()));

			major = student.getmMajor();
 			tvMajor.setText("Major:" + major.getmSubject());
		}
	}
}
