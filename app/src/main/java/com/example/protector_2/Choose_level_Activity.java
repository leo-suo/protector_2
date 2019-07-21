package com.example.protector_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose_level_Activity extends AppCompatActivity {
    public int which_map = 0;

    Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but10, but_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level_);

        but1 = findViewById(R.id.Game_1);
        but2 = findViewById(R.id.Game_2);
        but3 = findViewById(R.id.Game_3);
        but4 = findViewById(R.id.Game_4);
        but5 = findViewById(R.id.Game_5);
        but6 = findViewById(R.id.Game_6);
        but7 = findViewById(R.id.Game_7);
        but8 = findViewById(R.id.Game_8);
        but9 = findViewById(R.id.Game_9);
        but10 = findViewById(R.id.Game_10);
        but_back = findViewById(R.id.back);

        but1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 1;
            }
        });

        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 2;
            }
        });

        but3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 3;
            }
        });

        but4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 4;
            }
        });

        but5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 5;
            }
        });

        but6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 6;
            }
        });

        but7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 7;
            }
        });

        but8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 8;
            }
        });

        but9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 9;
            }
        });

        but10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 10;
            }
        });


    }
}
