package com.ui.yogeshblogspot;

import com.service.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    //Called when Submit Content button is Clicked
    public void submitContent(View v)
    {
    String rollNo=((EditText)findViewById(R.id.etRollno)).getText().toString();	 //Getting the value of etRollno and assigning to rollNo local Variable
    String name=((EditText)findViewById(R.id.etname)).getText().toString(); //Getting the value of etname and assigning to name local Variable
    
    //Creating the Object of Student Class
    Student student=new Student();  //Creating the Object of Student Class
    student.setRollNo(Integer.parseInt(rollNo));//assigning the rollNo(local variable)to Student Class rollNo(member variable)
    student.setName(name);//assigning name(locall variable) to Student class name(member variable) 
    //Object Creation Complete
    
    
    Intent in=new Intent(this,DisplayActivity.class);//Creating an Intent Object to invoke DisplayActivity
    in.putExtra("StudentInfo",student);//Passing the object of Student Class.
    startActivity(in);//Invoking Target Activity
    
    
    }
    //Called when user Clicks on Reset Content Button
    public void resetContent(View v)
    {
     ((EditText)findViewById(R.id.etRollno)).setText("");//this will clear the content of etRollno(EditText)
     ((EditText)findViewById(R.id.etname)).setText("");//this will clear the content of etname(EditText)
    }
}