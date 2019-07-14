package com.example.protector_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;




public class StartGame extends Activity {
    GameView gameView;
    public String map_info;

    //add comment

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readfiles();
        System.out.println("-----onCreate-----");
    }
    @Override
    protected void onStart() {
        super.onStart();
        gameView = new GameView(this, map_info);
        setContentView(gameView);
        System.out.println("-----onStart-----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("-----onResume-----");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("-----onPause-----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("-----onStop-----");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("-----onRestart-----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("-----onDestroy -----");
    }

    public void readfiles() {
        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.mission2);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if (is != null) {
            try {
                while ((data = reader.readLine()) != null) {
                    sbuffer.append(data + "\n");
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map_info = sbuffer.toString();
    }



}
