package com.ui.yogeshblogsppot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

 public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
/*Clearing the EditText  */    
 public void resetContent(View v)
  {
    EditText etName=(EditText)findViewById(R.id.etname);//Getting the etname EditText reference from main.xml 
    etName.setText("");//Assiging Blank in EditText
    EditText etAge=(EditText)findViewById(R.id.etage);//Getting the etage EditText reference from main.xml 
    etAge.setText("");//Assiging Blank in EditText
  }
/*This method will call DisplayActivity.class and pass the name and age detail*/
 public void sendData(View v)
  {
	EditText etName=(EditText)findViewById(R.id.etname);//Getting etname EditText Reference
    String name=etName.getText().toString();//Getting the etname value
    EditText etAge=(EditText)findViewById(R.id.etage);////Getting etage EditText Reference
    String age=etAge.getText().toString();//Getting etage vallue
    Intent in=new Intent(this,DisplayActivity.class);//Creating Intent Object to call DisplayActivity.class
    in.putExtra("name",name);//Passing name using Intent's putExtra method
    in.putExtra("age", Integer.parseInt(age));//Passing age using Intent's putExtra method.
    startActivity(in);//Invoking DisplayActivity.class
    
    
   }


}
