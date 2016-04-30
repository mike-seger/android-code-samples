package com.example.apprestart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.apprestart.services.RestartService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void restartApp(View v) {
        Intent mServiceIntent = new Intent(this, RestartService.class);
        startService(mServiceIntent);
    }
}
