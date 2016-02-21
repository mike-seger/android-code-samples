package com.imagination.technologies.multimedia;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoViewActivity extends Activity implements View.OnClickListener {
    @InjectView(R.id.videoView)
    VideoView videoView;
    @InjectView(R.id.playVideo)
    Button playVideo;
    String vidAddress;
    Uri vidUri;
    MediaController vidControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_view_activity);
        ButterKnife.inject(this);
        vidAddress = getString(R.string.urlVideo);
        vidUri = Uri.parse(vidAddress);
        videoView.setVideoURI(vidUri);
        playVideo.setOnClickListener(this);
        vidControl = new MediaController(this);
        vidControl.setAnchorView(videoView);
        videoView.setMediaController(vidControl);
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
            case R.id.playVideo:
                videoView.start();
                break;
            default:
                break;
        }
    }
}
