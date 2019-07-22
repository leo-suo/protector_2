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
        int randomnum = (int) (Math.random() * 100);
        System.out.println("sdadsddassadasfhdhfhfdshfkjsfjkds");
        System.out.println(randomnum);
        int you_cannt_choudao = randomnum % 100;
        int lantian_baiyun = you_cannt_choudao % 10;
        if(you_cannt_choudao == 44){
            System.out.println(you_cannt_choudao);
            System.out.println("JIN SE CHUAN SHUO");
        }else if(you_cannt_choudao == 45 || you_cannt_choudao == 46 || you_cannt_choudao == 47 ||
                you_cannt_choudao == 48){
            System.out.println(you_cannt_choudao);
            System.out.println("kassdin");
        }else if(lantian_baiyun == 4){
            System.out.println(you_cannt_choudao);
            System.out.println("karthus");
        }else if(lantian_baiyun == 1){
            System.out.println(you_cannt_choudao);
            System.out.println("tydnmere");
        }else if(lantian_baiyun == 2){
            System.out.println(you_cannt_choudao);
            System.out.println("garen");
        }else if(lantian_baiyun == 3){
            System.out.println(you_cannt_choudao);
            System.out.println("hao");
        }else if(lantian_baiyun == 5){
            System.out.println(you_cannt_choudao);
            System.out.println("wang");
        }else if(lantian_baiyun == 6){
            System.out.println(you_cannt_choudao);
            System.out.println("diana");
        }else if(lantian_baiyun == 7){
            System.out.println(you_cannt_choudao);
            System.out.println("mord");
        }else{
            System.out.println(you_cannt_choudao);
            System.out.println("wukong");
        }

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
