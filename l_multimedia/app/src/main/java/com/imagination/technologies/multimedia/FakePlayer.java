package com.imagination.technologies.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class FakePlayer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_player);
    }

    protected void onResume() {
        super.onResume();
        startPlayer();
    }

    public void onStop() {
        super.onStop();
        stopPlayer();
    }


    public void startPlayer() {
        Intent i=new Intent(this, PlayerService.class);
        i.putExtra(PlayerService.EXTRA_PLAYLIST, "song.mp3");
        i.putExtra(PlayerService.EXTRA_SHUFFLE, true);
        startService(i);
    }

    public void stopPlayer() {
        stopService(new Intent(this, PlayerService.class));
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
}
