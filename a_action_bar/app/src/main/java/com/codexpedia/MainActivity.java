package com.codexpedia;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import info.androidhive.actionbar.R;

public class MainActivity extends Activity {

	private ActionBar actionBar;
	private TextView tvAction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvAction = (TextView) findViewById(R.id.tv_action);

		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false); // Hide the action bar title
		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setHomeAsUpIndicator(R.drawable.ic_action_list);

		actionBar.setIcon(R.drawable.ic_action_list);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * On selecting action bar icons
	 **/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				setActionText("actionbar home button clicked!");
				break;
			case R.id.btn_edit:
				setActionText("edit button clicked!");
				break;
			case R.id.btn_save:
				setActionText("save button clicked!");
				break;
			case R.id.btn_help:
				setActionText("help button clicked!");
				break;
			default:
				super.onOptionsItemSelected(item);
				break;
		}
		return true;
	}

	private void setActionText(String s) {
		tvAction.setText(s);
	}
}
