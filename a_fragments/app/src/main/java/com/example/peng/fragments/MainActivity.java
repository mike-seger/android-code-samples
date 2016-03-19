package com.example.peng.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private String fragTag = "";
    private Fragment mCurrentFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            fragTag = savedInstanceState.getString("frag_tag");
//            mCurrentFrag = getFragmentManager().getFragment(savedInstanceState, fragTag);
            mCurrentFrag = getFragmentManager().findFragmentByTag(fragTag);
        }

        if (mCurrentFrag == null) {
            mCurrentFrag = new FragmentMain();
            fragTag = "fragment_main";
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_place, mCurrentFrag, fragTag)
                    .addToBackStack(null)
                    .commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnVal = true;
        Fragment fr = null;
        switch (item.getItemId()) {
            case R.id.action_main:
                fr = new FragmentMain();
                fragTag = "fragment_main";
                break;
            case R.id.action_a:
                fr = new FragmentA();
                fragTag = "fragment_a";
                break;
            case R.id.action_b:
                fr = new FragmentB();
                fragTag = "fragment_b";
                break;
            case R.id.action_c:
                fr = new FragmentC();
                fragTag = "fragment_c";
                break;
            default:
                returnVal = false;
                break;
        }

        if (returnVal) {
            mCurrentFrag = fr;
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction
                    .replace(R.id.fragment_place, fr, fragTag)
                    .addToBackStack(null)
                    .commit();
            return returnVal;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(this.getLocalClassName(), " onSaveInstanceState.");
        outState.putString("frag_tag", fragTag);
//        getFragmentManager().putFragment(outState, fragTag, mCurrentFrag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(this.getLocalClassName(), " onDestroy.");
    }
}

