package com.app.ex;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IdeaActivity extends Activity {
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	
	ProgressDialog progDialog;
	final Context context = this;
    /** Called when the activity is first created. */
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        btn4=(Button)findViewById(R.id.button4);
        btn5=(Button)findViewById(R.id.button5);
        btn6=(Button)findViewById(R.id.button6);
        btn7=(Button)findViewById(R.id.button7);
        
        
       btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//share data between application
				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				String shareBody = "This is the body to be shared";
				shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject of the text");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
				startActivity(Intent.createChooser(shareIntent, "Share via"));
			
			}
		});
        
        
        btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				 Intent intent = new Intent(Intent.ACTION_VIEW,
			        Uri.parse("content://contacts/people/"));
				 startActivity(intent);
			}
		});
        
        btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("content://media/internal/images/media"));
				startActivity(intent);
				
			}
		});
        
        btn4.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View arg0) {
     				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
    				startActivity(browserIntent);
     				
     			}
     		});
        
        btn5.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View arg0) {
     				
     				
     				Intent callIntent = new Intent(Intent.ACTION_CALL);
    				callIntent.setData(Uri.parse("tel:0377778888"));
    				startActivity(callIntent);
     				
     			}
     		});
        btn6.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View arg0) {
     				//Transfer data
    				Intent i = new Intent(getApplicationContext(), NewActivity.class);
    				i.putExtra("Name","Kalpesh");
    				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    				startActivity(i);
     			}
     		});
        btn7.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View arg0) {
     				
     				Intent intent = new Intent(Intent.ACTION_DIAL,
     				          Uri.parse("tel:(+49)12345789"));
     				startActivity(intent);
     				
     			}
     		});
    }
    
    
    
   
}

