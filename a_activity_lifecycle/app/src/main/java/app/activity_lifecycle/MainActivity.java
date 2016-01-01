package app.activity_lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle","On Start Called");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle","On onRestart Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle","On onPause Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle","On onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle","On onDestroy Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle","On onResume Called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Lifecycle","On onCreate Called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Lifecycle","On onSaveInstanceState Called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("Lifecycle","On onRestoreInstanceState Called");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
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
}
