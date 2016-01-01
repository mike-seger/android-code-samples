package com.mathildemouw.simpletodo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class EditActivity extends ActionBarActivity {

    EditText mletView;
    String itemPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mletView = (EditText) findViewById(R.id.mletView);
        String item = getIntent().getStringExtra("item");
        mletView.setText(item);
        itemPos = getIntent().getStringExtra("itemPos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onSubmit(View v) {
        Intent item = new Intent();
        item.putExtra("item", mletView.getText().toString());
        item.putExtra("itemPos", itemPos);
        setResult(RESULT_OK, item); // set result code and bundle data for response
        finish();
    }
}
