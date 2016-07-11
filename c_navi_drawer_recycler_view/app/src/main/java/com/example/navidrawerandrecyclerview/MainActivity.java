package com.example.navidrawerandrecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private List<NaviMenuItem> menuItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Menu items
        menuItemList = new ArrayList<>();
        NaviMenuItem item = new NaviMenuItem();
        item.setIcon(R.drawable.ic_menu_camera);
        item.setTitle("Camera");
        menuItemList.add(item);

        NaviMenuItem item2 = new NaviMenuItem();
        item2.setIcon(R.drawable.ic_menu_gallery);
        item2.setTitle("Gallery");
        menuItemList.add(item2);

        NaviMenuItem item3 = new NaviMenuItem();
        item3.setIcon(R.drawable.ic_menu_slideshow);
        item3.setTitle("Slide Show");
        menuItemList.add(item3);

        NaviMenuItem item4 = new NaviMenuItem();
        item4.setIcon(R.drawable.ic_menu_manage);
        item4.setTitle("Setting");
        menuItemList.add(item4);

        NaviMenuItem item5 = new NaviMenuItem();
        item5.setIcon(R.drawable.ic_menu_share);
        item5.setTitle("Share");
        menuItemList.add(item5);

        NaviMenuItem item6 = new NaviMenuItem();
        item6.setIcon(R.drawable.ic_menu_send);
        item6.setTitle("Send");
        menuItemList.add(item6);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_navigation_menu);
        NaviDrawerAdapter adapter = new NaviDrawerAdapter(menuItemList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        adapter.setOnItemClickLister(new NaviDrawerAdapter.OnItemSelecteListener() {
            @Override
            public void onItemSelected(View v, int position) {
                Snackbar.make(v, menuItemList.get(position).getTitle() + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
