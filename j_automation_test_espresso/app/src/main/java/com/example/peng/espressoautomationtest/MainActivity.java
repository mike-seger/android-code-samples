package com.example.peng.espressoautomationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {



    private EditText edText;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edText = (EditText) findViewById(R.id.ed_text);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSecondActivity();
            }
        });
    }

    private void toSecondActivity(){
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("msg", edText.getText().toString());
        startActivity(i);
    }

}
