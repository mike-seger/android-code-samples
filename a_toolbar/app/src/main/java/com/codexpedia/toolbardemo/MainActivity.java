package com.codexpedia.toolbardemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_list);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Content");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_frame, new ContentFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment frag = null;
        if (id == R.id.action_content) {
            toolbar.setTitle("Content");
            frag = new ContentFragment();
        } else if (id == R.id.action_detail) {
            toolbar.setTitle("Detail");
            frag = new DetailFragment();
        }

        if (frag != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_fragment_frame, frag)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
