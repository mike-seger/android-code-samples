package com.imagination.technologies.multimedia;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class AudioMediaPlayer extends Activity implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener {
    private static final String TAG = MainActivity.class.getName();

    @InjectView(R.id.seekBar1) SeekBar seekbar;
    @InjectView(R.id.textView4) TextView songName;
    @InjectView(R.id.textView1) TextView startTimeField;
    @InjectView(R.id.textView2) TextView endTimeField;
    @InjectView(R.id.imageButton1) ImageButton playButton;
    @InjectView(R.id.imageButton2) ImageButton pauseButton;

    private static int oneTimeOnly = 0;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private static final String PLAYING = "_playing_";
    private static final String SEEK_VALUE = "_seek_";
    private boolean playing;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putBoolean(PLAYING, mediaPlayer.isPlaying());
        savedInstanceState.putInt(SEEK_VALUE, mediaPlayer.getCurrentPosition());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_player);
        ButterKnife.inject(this);

        songName.setText("song.mp3");
        seekbar.setClickable(false);
        pauseButton.setEnabled(false);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);

        if (savedInstanceState != null) {
            playing = savedInstanceState.getBoolean(PLAYING, false);
            startTime = savedInstanceState.getInt(SEEK_VALUE, 0);

            if (playing) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        play(null);
                    }
                });
            }
        }
    }

    public void onStop() {
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void play(View view) {
        Toast.makeText(getApplicationContext(), "Playing sound",
                Toast.LENGTH_SHORT).show();

        mediaPlayer.start();

        finalTime = mediaPlayer.getDuration();
        if (!playing)
            startTime = mediaPlayer.getCurrentPosition();
        else
            mediaPlayer.seekTo((int)startTime);
        playing = false;

        if(oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }

        updateTimeField(finalTime, endTimeField);
        updateTimeField(startTime, startTimeField);

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateSongTime, 100);

        play(true);
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if (isFinishing() || mediaPlayer == null) return;
            startTime = mediaPlayer.getCurrentPosition();
            updateTimeField(startTime, startTimeField);
            seekbar.setProgress((int)startTime);
            if (!mediaPlayer.isPlaying()) return;
            myHandler.postDelayed(this, 100);
        }
    };

    private void updateTimeField(double time, TextView field) {
        field.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) time),
                TimeUnit.MILLISECONDS.toSeconds((long) time) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) time))));
    }

    public void pause(View view){
        Toast.makeText(getApplicationContext(), "Pausing sound",
                Toast.LENGTH_SHORT).show();
        if (!mediaPlayer.isPlaying()) return;
        mediaPlayer.pause();
        play(false);
    }

    public void forward(View view){
        int temp = (int)startTime;

        if((temp + forwardTime) <= finalTime) {
            startTime = startTime + forwardTime;
            mediaPlayer.seekTo((int) startTime);
        }
    }

    public void rewind(View view) {
        int temp = (int)startTime;

        if((temp - backwardTime) > 0)
            startTime = startTime - backwardTime;
        else
            startTime = 0;

        mediaPlayer.seekTo((int) startTime);
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
    public void onCompletion(MediaPlayer mp) {
        play(false);
    }

    private void play(boolean play) {
        if (play) {
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
        } else {
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        oneTimeOnly = 0;
    }
}
