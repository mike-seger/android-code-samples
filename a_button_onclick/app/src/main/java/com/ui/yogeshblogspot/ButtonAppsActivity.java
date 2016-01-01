package com.ui.yogeshblogspot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ButtonAppsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			TextView tv=(TextView)findViewById(R.id.textView1);
			tv.setText("Button Clicked - Button with Anonymous Handler");
				
			}
		});
    }
    public void clickHandler(View v) {
        TextView tv=(TextView)findViewById(R.id.textView1);
        tv.setText("Button Clicked - Button with Named Handler");
    }

    public void clickHandler2(View v) {
        TextView tv=(TextView)findViewById(R.id.textView1);
        tv.setText("Button Clicked - The third button Handler");
    }
}