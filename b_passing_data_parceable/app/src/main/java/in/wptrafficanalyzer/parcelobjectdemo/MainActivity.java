package in.wptrafficanalyzer.parcelobjectdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText mEtSName;
	EditText mEtSEmail;
	EditText mEtSAge;
	EditText mEtSAddress;
	EditText mEtSCourse;
	Button mBtnOk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Getting a reference to EditText et_sname of the layout activity_main
		mEtSName = (EditText)findViewById(R.id.et_sname);

		// Getting a reference to EditText et_semail of the layout activity_main
		mEtSEmail = (EditText)findViewById(R.id.et_semail);

		// Getting a reference to EditText et_sage of the layout activity_main
		mEtSAge = (EditText)findViewById(R.id.et_sage);
		
		// Getting a reference to EditText et_saddres of the layout activity_main
		mEtSAddress = (EditText)findViewById(R.id.et_saddress);
		
		// Getting a reference to EditText et_scourse of the layout activity_main
		mEtSCourse = (EditText)findViewById(R.id.et_scource);
		
		// Getting a reference to Button btn_ok of the layout activity_main
		mBtnOk = (Button)findViewById(R.id.btn_ok);
		
		// Setting onClick event listener for the "OK" button
		mBtnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Creating an instance of Student class with user input data
				Student student = new Student(mEtSName.getText().toString(),
											mEtSEmail.getText().toString(),
											Integer.parseInt(mEtSAge.getText().toString()), 
											mEtSAddress.getText().toString(), 
											mEtSCourse.getText().toString());
				
				// Creating an intent to open the activity StudentViewActivity
				Intent intent = new Intent(getBaseContext(), StudentViewActivity.class);
				
				// Passing data as a parecelable object to StudentViewActivity
				intent.putExtra("student",student);

				// Opening the activity
				startActivity(intent);				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}