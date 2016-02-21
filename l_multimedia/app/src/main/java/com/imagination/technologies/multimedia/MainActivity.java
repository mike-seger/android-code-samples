package com.imagination.technologies.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity implements View.OnClickListener {
    @InjectView(R.id.vvActivity)
    Button vvActivity;
    @InjectView(R.id.mediaPlayerActivity)
    Button mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
        vvActivity.setOnClickListener(this);
        mediaPlayer.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.vvActivity:
                startActivity(new Intent(this, VideoViewActivity.class));
                break;
            case R.id.mediaPlayerActivity:
                startActivity(new Intent(this, AudioMediaPlayer.class));
                break;
            default:
                break;
        }
    }
}
