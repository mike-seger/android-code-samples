package in.wptrafficanalayzer.dynamicfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnLoad = (Button) findViewById(R.id.btn_load);
        
        OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				HelloFragment hello = new HelloFragment();
				fragmentTransaction.add(R.id.fragment_container, hello, "HELLO");
				fragmentTransaction.commit();	
			}
		};
        
        btnLoad.setOnClickListener(listener);       
        
    }
}