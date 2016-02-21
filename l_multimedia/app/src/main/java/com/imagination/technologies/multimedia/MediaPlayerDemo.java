/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.imagination.technologies.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaPlayerDemo extends Activity {
    private static final String MEDIA = "media";
    private static final int LOCAL_AUDIO = 1;
    private static final int STREAM_AUDIO = 2;
    private static final int RESOURCES_AUDIO = 3;
    private static final int LOCAL_VIDEO = 4;
    private static final int STREAM_VIDEO = 5;
    private static final int RESOURCES_VIDEO = 6;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.mediaplayer_1);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.localaudio)
    public void openLocalAudioMediaPlayer() {
        Intent intent =
                new Intent(MediaPlayerDemo.this,
                        AudioMediaPlayer.class);
        //intent.putExtra(MEDIA, LOCAL_AUDIO);
        startActivity(intent);
    }

    @OnClick(R.id.resourcesaudio)
    public void openAudioStreamPlayer() {
        Intent intent =
                new Intent(MediaPlayerDemo.this.getApplication(),
                        AudioMediaPlayer.class);
        intent.putExtra(MEDIA, RESOURCES_AUDIO);
        startActivity(intent);
    }

    @OnClick(R.id.localvideo)
    public void openLocalVideo() {
        Intent intent =
                new Intent(MediaPlayerDemo.this,
                        MediaPlayerVideo.class);
        intent.putExtra(MEDIA, LOCAL_VIDEO);
        startActivity(intent);
    }


    @OnClick(R.id.streamvideo)
    public void openStreamVideo() {
        Intent intent =
                new Intent(MediaPlayerDemo.this,
                        VideoViewActivity.class);
        intent.putExtra(MEDIA, STREAM_VIDEO);
        startActivity(intent);
    }
}
