package com.example.peng.robotiumunittest.test;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


@SuppressWarnings("rawtypes")
public class LoginTest2 extends ActivityInstrumentationTestCase2 {
  	private Solo solo;
  	
  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.example.peng.robotiumunittest.LoginActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
  	
  	@SuppressWarnings("unchecked")
    public LoginTest2() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.example.peng.robotiumunittest.LoginActivity'
		solo.waitForActivity("LoginActivity", 2000);
        //Set default small timeout to 15425 milliseconds
		Timeout.setSmallTimeout(15425);
        //Enter the text: 'test@example.com'
		solo.clearEditText((android.widget.EditText) solo.getView("email"));
		solo.enterText((android.widget.EditText) solo.getView("email"), "test@example.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("password"));
        //Enter the text: 'asdfg'
		solo.clearEditText((android.widget.EditText) solo.getView("password"));
		solo.enterText((android.widget.EditText) solo.getView("password"), "asdfg");
        //Click on Sign in or register
		solo.clickOnView(solo.getView("email_sign_in_button"));
	}
}
