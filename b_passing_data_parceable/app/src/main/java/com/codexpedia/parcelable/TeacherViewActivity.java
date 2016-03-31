package com.codexpedia.parcelable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.codexpedia.parcelable.model.Student;
import com.codexpedia.parcelable.model.Teacher;

import java.util.ArrayList;


public class TeacherViewActivity extends Activity {
	
	private TextView tvName;
	private TextView tvEmail;
	private TextView tvSAge;
	private TextView tvStudents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher);
		Teacher teacher = getIntent().getParcelableExtra("teacher");
		ArrayList<Student> students = teacher.getStudents();
		
		tvName 	= (TextView)findViewById(R.id.tv_sname);
		tvEmail = (TextView)findViewById(R.id.tv_semail);
		tvSAge 	= (TextView) findViewById(R.id.tv_sage);
		tvStudents = (TextView) findViewById(R.id.tv_students);

		if (teacher != null) {
			tvName.setText("Name:" + teacher.getName());
			tvEmail.setText("Email:" + teacher.getEmail());
			tvSAge.setText("Age:" + Integer.toString(teacher.getAge()));
		}

		if (students != null) {
			String sInfo = "";
			for (Student s : students) {
				sInfo = sInfo + s.getName() + "\n";
			}
			tvStudents.setText(sInfo);
		}

	}
}
