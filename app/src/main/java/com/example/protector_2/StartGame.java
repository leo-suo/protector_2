package com.example.protector_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;




public class StartGame extends Activity {
    static public int mouse = 0;
    public int Gold_num;
    public int Cost_num;

    GameView gv;
    public static String map_info;

    Button but1, but2, but3, but4, but5, but6;

    int which_map;
    //add comment

    int time = 0;

    private boolean run = false;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readfiles();
        System.out.println("-----onCreate-----");
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activitiy_startgame);


        Intent intent = getIntent();
        Gold_num = intent.getIntExtra("Gold",-1);
        TextView t = findViewById(R.id.Gold_text);
        t.setText("Gold :" +String.valueOf(Gold_num));

        Cost_num = GameView.cost;
        TextView t_1 = findViewById(R.id.Cost_text);
        t_1.setText("Cost :" +String.valueOf(Cost_num));


        // GameView
        LinearLayout root = (LinearLayout)findViewById(R.id.root);
        gv = new GameView(this);
        root.addView(gv);

        but1 = findViewById(R.id.tower_1);
        but2 = findViewById(R.id.tower_2);
        but3 = findViewById(R.id.tower_3);
        but4 = findViewById(R.id.tower_4);
        but5 = findViewById(R.id.tower_5);
        but6 = findViewById(R.id.tower_6);

        but1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mouse = 1;
            }
        });
        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mouse = 2;
            }
        });
        but3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mouse = 3;
            }
        });
        but4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mouse = 4;
            }
        });
        but5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mouse = 5;
            }
        });
        but6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mouse = 6;
            }
        });

        run = true;
        handler.postDelayed(task, 150);
    }

    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (run) {
                Cost_num = GameView.cost;
                TextView t_1 = findViewById(R.id.Cost_text);
                t_1.setText("Cost :" +String.valueOf(Cost_num));
                handler.postDelayed(this, 150);
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        //gameView = findViewById(R.id.GameView);
        //gameView = new GameView(this);
        //setContentView(gameView);
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
        GameView.number_of_tower = 0;
        System.out.println("-----onDestroy -----");
    }

    public void end(int is_win){
        if(is_win == 1){
            Gold_num += 100;
        }
        Intent intent = new Intent();
        intent.putExtra("Gold",Gold_num);
        setResult(RESULT_OK,intent);
        System.out.println("I am HERERER");
        finish();
        System.exit(0);

    }

    public void readfiles() {
        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.mission1);
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
