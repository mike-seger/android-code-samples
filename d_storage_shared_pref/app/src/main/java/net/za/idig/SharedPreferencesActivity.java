package net.za.idig;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class SharedPreferencesActivity extends Activity {

	View myLayout;
	String userName = null;
	int backgroundColor = 0;
	SharedPreferences applicationSharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		applicationSharedPreferences = getSharedPreferences(
				"applicationPreferences", MODE_WORLD_READABLE);
		myLayout = findViewById(R.id.layout);
		myLayout.setBackgroundColor(getUserBackgroundColor());

		Button viewSharedPreferencesButton = (Button) findViewById(R.id.next_activity);
		viewSharedPreferencesButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent explicitIntent = new Intent(
						SharedPreferencesActivity.this, ViewSharedPrefs.class);
				startActivity(explicitIntent);
			}
		});
	}

	private int getUserBackgroundColor() {
		backgroundColor = 0;
		String userName = applicationSharedPreferences.getString("userName",
				"Jack Be Nimble");
		if (userName.equals("Jack")) {
			backgroundColor = getResources().getColor(R.color.Khaki);
		}
		if (userName.equals("Peter")) {
			backgroundColor = getResources().getColor(R.color.Teal);
		}
		if (userName.equals("Paul")) {
			backgroundColor = getResources().getColor(R.color.Tomato);
		}
		return backgroundColor;
	}

	public void onRadioButtonClicked(View v) {
		RadioButton rb = (RadioButton) v;
		userName = (String) rb.getText();
		if (userName.equals("Jack")) {
			backgroundColor = getResources().getColor(R.color.Khaki);
		}
		if (userName.equals("Peter")) {
			backgroundColor = getResources().getColor(R.color.Teal);
		}
		if (userName.equals("Paul")) {
			backgroundColor = getResources().getColor(R.color.Tomato);
		}
		myLayout.setBackgroundColor(backgroundColor);
		SharedPreferences.Editor myPrefrencesEditor = applicationSharedPreferences
				.edit();
		myPrefrencesEditor.putString("userName", userName);
		myPrefrencesEditor.commit();
	}
}