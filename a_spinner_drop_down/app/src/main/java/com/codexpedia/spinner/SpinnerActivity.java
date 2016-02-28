package com.codexpedia.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.ui.yogeshblogspot.R;

public class SpinnerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initSpinner1();
		initSpinner2();
    }

	private void initSpinner1() {
		Spinner sp=(Spinner)findViewById(R.id.spinner1);
		String[] courses={"Android","IPhone","Windows Mobile","Blackberry","J2me"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,courses);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> av, View v,
									   int position, long itemId) {
				// TODO Auto-generated method stub
				String item=av.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), "Selected Item is "+item, Toast.LENGTH_LONG).show();
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void initSpinner2() {
		Spinner sp2=(Spinner)findViewById(R.id.spinner2);
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> av, View v,
									   int position, long itemId) {
				// TODO Auto-generated method stub
				String item=av.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(),"Selected Item is "+item,Toast.LENGTH_LONG).show();
			}

			public void onNothingSelected(AdapterView<?> av) {
				// TODO Auto-generated method stub

			}
		});
	}

    public void getDetail(View v) {
    	Spinner spinner1=(Spinner)findViewById(R.id.spinner1);
    	Spinner spinner2=(Spinner)findViewById(R.id.spinner2);
    	
    	Toast.makeText(getApplicationContext(), "Spinner 1- "+spinner1.getSelectedItem().toString()+"\nSpinner 2-"+spinner2.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
    }
}