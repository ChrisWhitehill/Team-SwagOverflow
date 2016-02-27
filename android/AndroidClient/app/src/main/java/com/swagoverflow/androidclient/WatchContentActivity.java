package com.swagoverflow.androidclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.widget.MediaController;
import android.widget.VideoView;

public class WatchContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_content);

        Intent intent = getIntent();
        String uri = intent.getStringExtra(Constants.URI);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(uri));
        videoView.requestFocus();
        videoView.start();
    }

}
