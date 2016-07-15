package com.example.customdialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AlertDialog.Builder alertDialog;
    private DialogFragment fragmentDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initAlertDialog();
        initFragmentDialog();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_custom_dialog) {
            openCustomDialog();
        } else if (id == R.id.nav_alert_dialog) {
            alertDialog.show();
        } else if (id == R.id.nav_fragment_dialog) {
            fragmentDialog.show(getSupportFragmentManager(), "tag");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openCustomDialog() {
        CustomDialog customDialog = new CustomDialog(this, "Do you want to play again?", "Yes", "No");
        customDialog.setImage(R.drawable.ic_menu_camera);
        int nFont = (int) getResources().getDimension(R.dimen.font_size_16sp);
        customDialog.show();
        
        Button btn = (Button) customDialog.findViewById(R.id.btn_no);
        Button btn1 = (Button) customDialog.findViewById(R.id.btn_yes);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, nFont);
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, nFont);

        customDialog.setListener(new CustomDialogCompleteListener() {
            @Override
            public void onComplete(int nSucceeded) {
                if (CustomDialog.BTN_YES == nSucceeded) {
                    //yes
                    Toast.makeText(getBaseContext(), "YES", Toast.LENGTH_SHORT).show();
                } else {
                    //skip or no
                    Toast.makeText(getBaseContext(), "NO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initAlertDialog() {
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Confirm Delete...");
        alertDialog.setMessage("Are you sure you want delete your account?");
        alertDialog.setIcon(R.drawable.ic_error_black);
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getApplicationContext(), "YES, delele it", Toast.LENGTH_SHORT).show();
                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getApplicationContext(), "NO, don't delete it", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
    }

    private void initFragmentDialog() {
        fragmentDialog = new MyFragmentDialog();
        Bundle args = new Bundle();
        args.putString("title", "Confirm delete");
        args.putString("message", "Are you sure you want to delete your account");
        fragmentDialog.setArguments(args);
    }


}
