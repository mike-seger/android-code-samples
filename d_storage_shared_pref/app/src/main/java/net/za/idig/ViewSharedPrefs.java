package net.za.idig;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewSharedPrefs extends Activity {

	String userName;
	int backgroundColor;
	TextView displayCounterTextView;
	View myLayout;
	int counter = 0;
	SharedPreferences applicationSharedPreferences;
	SharedPreferences activitySharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_prefs);

		applicationSharedPreferences = getSharedPreferences(
				"applicationPreferences", MODE_WORLD_READABLE);
		activitySharedPreferences = getPreferences(MODE_WORLD_READABLE);
		counter = activitySharedPreferences.getInt("counter", 0);
		displayCounterTextView = (TextView) findViewById(R.id.textView_counter);
		displayCounterTextView.setText(Integer.toString(counter));
		myLayout = findViewById(R.id.layout2);
		myLayout.setBackgroundColor(getUserBackgroundColor());

		final SharedPreferences.Editor activityPreferencesEditor = activitySharedPreferences
				.edit();

		Button clearApplicationPreferencesButton = (Button) findViewById(R.id.button1);
		clearApplicationPreferencesButton
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						SharedPreferences.Editor mySharedPreferencesEditor = applicationSharedPreferences
								.edit();
						mySharedPreferencesEditor.clear();
						mySharedPreferencesEditor.commit();
						myLayout.setBackgroundColor(getUserBackgroundColor());
					}
				});

		Button backButton = (Button) findViewById(R.id.button2);
		backButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				counter++;
				activityPreferencesEditor.putInt("counter", counter);
				activityPreferencesEditor.commit();
				Intent explicitIntent = new Intent(ViewSharedPrefs.this,
						SharedPreferencesActivity.class);
				startActivity(explicitIntent);
			}
		});

		Button resetCounterButton = (Button) findViewById(R.id.button3);
		resetCounterButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				activityPreferencesEditor.clear();
				activityPreferencesEditor.commit();
				counter = 0;
				displayCounterTextView.setText(Integer.toString(counter));
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
}
