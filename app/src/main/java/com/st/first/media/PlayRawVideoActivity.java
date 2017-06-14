package com.st.first.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.st.first.R;

import java.io.File;

public class PlayRawVideoActivity extends Activity  {
    VideoView videoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        videoView = (VideoView) findViewById(R.id.videoDemo);
        Uri uri = Uri.parse("android.resource://com.st.first/raw/paris");  // Not paris.mov
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(),"Video Completed!", Toast.LENGTH_LONG).show();
            }
        });


        videoView.requestFocus();
        videoView.start();
    }
}
