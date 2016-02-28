package com.example.peng.activitycycle;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    // UI elements
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(this.getLocalClassName(),"Activity created.");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(this.getLocalClassName(), "Activity started.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(this.getLocalClassName(), "Activity paused.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(this.getLocalClassName(), "Activity stopped.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(this.getLocalClassName(), "Activity resumed.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(this.getLocalClassName(), "Activity restarted.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getLocalClassName(), "Activity destroyed.");
    }

    public void nextScreen(View v) {
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(i);
        Log.i(this.getLocalClassName(), "Started next activity.");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(this.getLocalClassName(), "Restore instance.");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(this.getLocalClassName(), "Save instance.");

    }
}
