package com.example.protector_2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Summon_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summon_);
        VideoView v = findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback;
        Uri uri = Uri.parse(path);
        v.setVideoURI(uri);
    }

    public void onPlayClick(View view){
        Button play = findViewById(R.id.play_bt);
        if(play.getText() == "Play"){
            VideoView videoView = findViewById(R.id.videoView);
            videoView.start();
            play.setText("Stop");
        } else{
            VideoView videoView = findViewById(R.id.videoView);
            videoView.pause();
            play.setText("Play");
        }

    }
}
