package com.example.protector_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose_level_Activity extends AppCompatActivity {
    public static int which_map = 0;

    Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but10, but_back;
    public int Gold_num;
    public int[] tower_array;
    public int[] cost_array;
    public int[] fight_array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level_);

        //load some information
        Intent intent = getIntent();
        Gold_num = intent.getIntExtra("Gold",-1);
        tower_array = intent.getIntArrayExtra("tower");
        fight_array = intent.getIntArrayExtra("fight");
        cost_array = intent.getIntArrayExtra("cost");

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

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which_map = 1;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 2;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 3;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 4;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 5;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 6;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 7;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 8;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });

        but9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 9;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);
            }
        });

        but10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                which_map = 10;
                Intent intent = new Intent(Choose_level_Activity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                intent.putExtra("fight",fight_array);
                intent.putExtra("cost",cost_array);
                startActivityForResult(intent,0);

            }
        });


    }


    public void onBackButtonClick(View View){
        Intent intent = new Intent();
        intent.putExtra("Gold",Gold_num);
        intent.putExtra("tower",tower_array);
        intent.putExtra("fight",fight_array);
        intent.putExtra("cost",cost_array);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameView.number_of_tower = 0;
    }


    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent intent){
        super.onActivityResult(requestcode,resultcode,intent);
        Gold_num = intent.getIntExtra("Gold",-1);
        tower_array = intent.getIntArrayExtra("tower");
        fight_array = intent.getIntArrayExtra("fight");
        cost_array = intent.getIntArrayExtra("cost");

    }
}
