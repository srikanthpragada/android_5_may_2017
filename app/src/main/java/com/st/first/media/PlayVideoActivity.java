package com.st.first.media;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.st.first.R;

public class PlayVideoActivity extends Activity  {
    VideoView videoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        videoView = (VideoView) findViewById(R.id.videoDemo);
        File root =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File file = new File(root,"paris.mov");
        if ( file.exists())
           Log.d("Media", file.toString() + " Found");
        else
           Log.d("Media", file.toString() + " Not Found");

        videoView.setVideoPath( file.toString());


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
