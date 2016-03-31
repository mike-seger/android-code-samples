package com.codexpedia.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.codexpedia.parcelable.model.Major;
import com.codexpedia.parcelable.model.Student;
import com.codexpedia.parcelable.model.Teacher;

import java.util.ArrayList;


public class MainActivity extends Activity {
	
	private Button mBtnStudent;
	private Button mBtnTeacher;
	private ArrayList<Student> students;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		students = new ArrayList<>();
		students.add(new Student("Amy", "amy@test.com", 18, new Major("Biology", 90)));
		students.add(new Student("Ben", "ben@test.com", 18, new Major("Chemistry", 90)));

		mBtnStudent = (Button)findViewById(R.id.btn_student);
		mBtnStudent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Student student = students.get(0);

				Intent intent = new Intent(getBaseContext(), StudentViewActivity.class);
				intent.putExtra("student", student);
				startActivity(intent);
			}
		});

		mBtnTeacher = (Button)findViewById(R.id.btn_teacher);
		mBtnTeacher.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Teacher teacher = new Teacher("Aristotle", "aristotle@test.com", 30, students);
				Intent intent = new Intent(getBaseContext(), TeacherViewActivity.class);
				intent.putExtra("teacher", teacher);
				startActivity(intent);
			}
		});

	}

}