package com.example.protector_2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import android.graphics.*;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Random;
import java.util.jar.Attributes;

public class GameView extends View{
    int i = StartGame.mouse;
    public int worldWidth = 12;
    public int worldHeight = 6;
    public int blockSize = 100;

    public static Block[][] block;

    public static Tower[] tower;

    public static int total_hp = 5000;

    public static int cost = 100;

    // What tower we have
    public String[] index;
    public int total_tower_but = 0;


    // enemy
    public Enemy yasuo_array[];

    public String map_info;

    static int number_of_yasuo = 0;
    public static int num_of_dead_yasuo = 0;


    public static int number_of_tower = 0;
    // the number of the tower we create

    StartGame sg;

    Handler handler; // handler is required to schedule a runnable after some delay
    Runnable runnable;
    final int UPDATE_MILLTS = 50;
    Bitmap background;

    Display display;
    Point point;
    int dWith, dHeight;// Device width width and height respectively
    Rect rect;
    Context context;

    // map
    Bitmap [][] map;

    boolean gameStat = true;


    public  void is_finish(){
        if(num_of_dead_yasuo == number_of_yasuo){
            sg.end(1);
        }else if(total_hp <= 0){
            sg.end(0);
        }
    }
    public static void be_attack(int att){
        total_hp = total_hp - att;
    }

    public GameView(Context context){

        super(context);


        index = new String[6];
        for(int i = 0; i < 10; i++){
            if(StartGame.fight_array[i] == 1){


                if(i == 0){
                    index[total_tower_but] = "wukong";
                }else if(i == 1){
                    index[total_tower_but] = "mord";
                }else if(i == 2){
                    index[total_tower_but] = "diana";
                }else if(i == 3){
                    index[total_tower_but] = "wang";
                }else if(i == 4){
                    index[total_tower_but] = "hao";
                }else if(i == 5){
                    index[total_tower_but] = "garren";
                }else if(i == 6){
                    index[total_tower_but] = "tynda";
                }else if(i == 7){
                    index[total_tower_but] = "karthus";
                }else if(i == 8){
                    index[total_tower_but] = "kassdin";
                }else{
                    index[total_tower_but] = "tang";
                }
                ++total_tower_but;
            }
        }

        if(total_tower_but != 6){
            while(total_tower_but < 6){
                index[total_tower_but] = "NULL";
                total_tower_but++;
            }
        }




        this.sg = (StartGame) context;
        this.context = context;
        this.map_info = StartGame.map_info;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate(); // call onDraw()
            }
        };

        block = new Block[worldHeight][worldWidth];
        map = new Bitmap[worldHeight][worldWidth];
        for(int y = 0; y < worldHeight; y++){
            for (int x = 0; x < worldWidth; x++){

                block[y][x] = new Block(x * blockSize,y * blockSize + (blockSize / 2),
                        map_info.charAt(13 * y + x) - '0',
                            1);
                if(block[y][x].groundId == 0){
                    map[y][x] = BitmapFactory.decodeResource(getResources(),R.drawable.tilesetground);
                }else{
                    map[y][x] = BitmapFactory.decodeResource(getResources(),R.drawable.tilesetair);
                }
            }
        }

        number_of_yasuo = map_info.charAt(78) - '0';

        // create yasuo class
        yasuo_array = new Enemy[number_of_yasuo];

        for(int i = 0; i < number_of_yasuo; ++i){
            int start_num = 78 + i * 5;

            // Concrete Class
            if(map_info.charAt(start_num + 1) == '7'){
                yasuo_array[i] = new yasuo(context, 0, 0,  block, i);
            }else if(map_info.charAt(start_num + 1) == '2'){
                yasuo_array[i] = new Enemy_Chitu(context, 0, 0,  block, i);
            }else if(map_info.charAt(start_num + 1) == '3'){
                yasuo_array[i] = new Enemy_Darius(context, 0, 0,  block, i);
            }else if(map_info.charAt(start_num + 1) == '4'){
                yasuo_array[i] = new Enemy_Fiora(context, 0, 0,  block, i);
            }else if(map_info.charAt(start_num + 1) == '5'){
                yasuo_array[i] = new Enemy_Jinx(context, 0, 0,  block, i);
            }else if(map_info.charAt(start_num + 1) == '6'){
                yasuo_array[i] = new Enemy_Lee(context, 0, 0,  block, i);
            }else{
                yasuo_array[i] = new Enemy_Mordekaiser(context, 0, 0,  block, i);
            }


            // Decorator
            Enemy_Interface n_e = yasuo_array[i];
            if(map_info.charAt(start_num + 2) == '1'){
                n_e = new Decorator_Armor(n_e);
            }
            if(map_info.charAt(start_num + 3) == '1'){
                n_e = new Decorator_Dagger(n_e);
            }
            if(map_info.charAt(start_num + 4) == '1'){
                n_e = new Decorator_Helmet(n_e);
            }
            if(map_info.charAt(start_num + 5) == '1'){
                n_e = new Decorator_Sword(n_e);
            }

            //Enemy_Interface n_e = new Decorator_Armor(new Decorator_Dagger(yasuo_array[i]));
            yasuo_array[i].set_damage(n_e.return_attack_damage());
            yasuo_array[i].set_hp(n_e.return_hp());
        }



        // bird
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWith = point.x;
        dHeight = point.y;
        rect = new Rect(0,0,dWith,dHeight);



        // Tower activity

        tower = new Tower[worldWidth * worldHeight];
    }

    public GameView(Context context, AttributeSet set){
        super(context, set);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // we will draw our view here

        // Draw the background on the canvas
        canvas.drawBitmap(background,null,rect,null);//fixed
        for(int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                canvas.drawBitmap(map[y][x],block[y][x].x,block[y][x].y,null);
            }
        }

        for(int i = 0; i < number_of_yasuo; ++i){
            yasuo_array[i].get_which_draw();
        }
        if (gameStat) {

            // The bird should be on the screen
            //change direction

            //move
            for(int i = 0; i < number_of_yasuo; ++i){
                yasuo_array[i].yasuo_move();
                yasuo_array[i].notifyOberver();
                yasuo_array[i].update_position();
                yasuo_array[i].attack_base();
                yasuo_array[i].attack_tower();
            }
            is_finish();

        }



        // we want to the bird to be displayed at the centre of the screen
        for(int i = 0; i < number_of_yasuo; ++i){
            if(yasuo_array[i].ingame == 1 && yasuo_array[i].hp > 0){
                Paint mPaint = new Paint();

                mPaint.setColor(Color.BLACK);
                mPaint.setStyle(Paint.Style.STROKE);
                //mPaint.setStrokeWidth(10f);
                int left = yasuo_array[i].yasuoX;
                int top = yasuo_array[i].yasuoY;
                int right = yasuo_array[i].yasuoX + 75;
                int bottom = yasuo_array[i].yasuoY + 10;
                Rect rect = new Rect(left, top, right, bottom);
                canvas.drawRect(rect, mPaint);

                mPaint.setColor(Color.RED);
                mPaint.setStyle(Paint.Style.FILL);
                //mPaint.setStrokeWidth(10f);
                left = yasuo_array[i].yasuoX;
                top = yasuo_array[i].yasuoY;
                right = yasuo_array[i].yasuoX + 75 * (100 * yasuo_array[i].hp / yasuo_array[i].total_hp) / 100;
                bottom = yasuo_array[i].yasuoY + 10;
                rect = new Rect(left, top, right, bottom);
                canvas.drawRect(rect, mPaint);

                //canvas.drawRect(, yasuo_array[i].yasuoY, yasuo_array[i].yasuoX - 500, yasuo_array[i].yasuoY+50, mPaint);

                canvas.drawBitmap(yasuo_array[i].yasuos[yasuo_array[i].yasuoFrame], yasuo_array[i].yasuoX,
                        yasuo_array[i].yasuoY, mPaint);
            }
        }

        // draw tower
        for(int i = 0; i < number_of_tower; ++i){
            if(tower[i].ingame == 1 && !tower[i].is_dead()){
                Paint paint = new Paint();
                if(tower[i].is_Zhande == 1){
                    Paint mPaint = new Paint();

                    mPaint.setColor(Color.BLACK);
                    mPaint.setStyle(Paint.Style.STROKE);
                    //mPaint.setStrokeWidth(10f);
                    int left = tower[i].towerX;
                    int top = tower[i].towerY - 10;
                    int right = tower[i].towerX + 75;
                    int bottom = tower[i].towerY;
                    Rect rect = new Rect(left, top, right, bottom);
                    canvas.drawRect(rect, mPaint);

                    mPaint.setColor(Color.GREEN);
                    mPaint.setStyle(Paint.Style.FILL);
                    //mPaint.setStrokeWidth(10f);
                    left = tower[i].towerX;
                    top = tower[i].towerY - 10;
                    right = tower[i].towerX + 75 * (100 * tower[i].hp / tower[i].total_hp) / 100;
                    bottom = tower[i].towerY ;
                    rect = new Rect(left, top, right, bottom);
                    canvas.drawRect(rect, mPaint);

                }
                canvas.drawBitmap(tower[i].bit_tower[tower[i].which_frame], tower[i].towerX,
                        tower[i].towerY, null);
                tower[i].get_which_draw();

                if(tower[i].attacking == 1 ){
                    canvas.drawLine(tower[i].center_x(),
                            tower[i].center_y(),
                            tower[i].who_i_attack.center_x(),
                            tower[i].who_i_attack.center_y(),
                            paint);
                }
            }
        }

        handler.postDelayed(runnable,UPDATE_MILLTS);
    }



    // Get the touch event

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float f_x;
        float f_y;

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            // Here we want the bird to move up by some unit
            //velocity = -30;
            f_x = event.getX();
            f_y = event.getY();
            for(int j = 0; j < worldHeight; j++){
                for (int i = 0; i < worldWidth; i++){
                    if(block[j][i].in_the_block(f_x, f_y)){
                        if (StartGame.mouse == 1){
                            if(index[0] == "wukong"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[0] >= 0){
                                    cost = cost - StartGame.cost_array[0];
                                    Tower_Wukong n_t = new Tower_Wukong(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "mord"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[1] >= 0){
                                    cost = cost - StartGame.cost_array[1];
                                    Tower_Mord n_t = new Tower_Mord(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "diana"  && block[j][i].kind == 1){
                                if(cost - StartGame.cost_array[2] >= 0){
                                    cost = cost - StartGame.cost_array[2];
                                    Tower_Diana n_t = new Tower_Diana(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                    block[j][i].have_tower = 1;
                                    block[j][i].block_tower = n_t;
                                }
                            }else if(index[0] == "wang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[3] >= 0){
                                    cost = cost - StartGame.cost_array[3];
                                    Tower_Wang n_t = new Tower_Wang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "hao"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[4] >= 0){
                                    cost = cost - StartGame.cost_array[4];
                                    Tower_Hao n_t = new Tower_Hao(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "garren"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[5] >= 0){
                                    cost = cost - StartGame.cost_array[5];
                                    Tower_Garen n_t = new Tower_Garen(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "tynda"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[6] >= 0){
                                    cost = cost - StartGame.cost_array[6];
                                    Tower_Tryndamere n_t = new Tower_Tryndamere(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "karthus"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[7] >= 0){
                                    cost = cost - StartGame.cost_array[7];
                                    Tower_Karthus n_t = new Tower_Karthus(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "kassdin"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[8] >= 0){
                                    cost = cost - StartGame.cost_array[8];
                                    Tower_Kassadin n_t = new Tower_Kassadin(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[0] == "tang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[9] >= 0){
                                    cost = cost - StartGame.cost_array[9];
                                    Tower_Tang n_t = new Tower_Tang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }

                        }
                        if (StartGame.mouse == 2){
                            // This tower can only put in the tower

                            if(index[1] == "wukong"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[0] >= 0){
                                    cost = cost - StartGame.cost_array[0];
                                    Tower_Wukong n_t = new Tower_Wukong(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "mord"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[1] >= 0){
                                    cost = cost - StartGame.cost_array[1];
                                    Tower_Mord n_t = new Tower_Mord(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "diana"  && block[j][i].kind == 1){
                                if(cost - StartGame.cost_array[2] >= 0){
                                    cost = cost - StartGame.cost_array[2];
                                    Tower_Diana n_t = new Tower_Diana(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                    block[j][i].have_tower = 1;
                                    block[j][i].block_tower = n_t;
                                }
                            }else if(index[1] == "wang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[3] >= 0){
                                    cost = cost - StartGame.cost_array[3];
                                    Tower_Wang n_t = new Tower_Wang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "hao"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[4] >= 0){
                                    cost = cost - StartGame.cost_array[4];
                                    Tower_Hao n_t = new Tower_Hao(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "garren"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[5] >= 0){
                                    cost = cost - StartGame.cost_array[5];
                                    Tower_Garen n_t = new Tower_Garen(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "tynda"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[6] >= 0){
                                    cost = cost - StartGame.cost_array[6];
                                    Tower_Tryndamere n_t = new Tower_Tryndamere(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "karthus"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[7] >= 0){
                                    cost = cost - StartGame.cost_array[7];
                                    Tower_Karthus n_t = new Tower_Karthus(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "kassdin"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[8] >= 0){
                                    cost = cost - StartGame.cost_array[8];
                                    Tower_Kassadin n_t = new Tower_Kassadin(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[1] == "tang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[9] >= 0){
                                    cost = cost - StartGame.cost_array[9];
                                    Tower_Tang n_t = new Tower_Tang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }
                        }
                        if (StartGame.mouse == 3){
                            if(index[2] == "wukong"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[0] >= 0){
                                    cost = cost - StartGame.cost_array[0];
                                    Tower_Wukong n_t = new Tower_Wukong(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "mord"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[1] >= 0){
                                    cost = cost - StartGame.cost_array[1];
                                    Tower_Mord n_t = new Tower_Mord(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "diana"  && block[j][i].kind == 1){
                                if(cost - StartGame.cost_array[2] >= 0){
                                    cost = cost - StartGame.cost_array[2];
                                    Tower_Diana n_t = new Tower_Diana(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                    block[j][i].have_tower = 1;
                                    block[j][i].block_tower = n_t;
                                }
                            }else if(index[2] == "wang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[3] >= 0){
                                    cost = cost - StartGame.cost_array[3];
                                    Tower_Wang n_t = new Tower_Wang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "hao"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[4] >= 0){
                                    cost = cost - StartGame.cost_array[4];
                                    Tower_Hao n_t = new Tower_Hao(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "garren"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[5] >= 0){
                                    cost = cost - StartGame.cost_array[5];
                                    Tower_Garen n_t = new Tower_Garen(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "tynda"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[6] >= 0){
                                    cost = cost - StartGame.cost_array[6];
                                    Tower_Tryndamere n_t = new Tower_Tryndamere(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "karthus"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[7] >= 0){
                                    cost = cost - StartGame.cost_array[7];
                                    Tower_Karthus n_t = new Tower_Karthus(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "kassdin"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[8] >= 0){
                                    cost = cost - StartGame.cost_array[8];
                                    Tower_Kassadin n_t = new Tower_Kassadin(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[2] == "tang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[9] >= 0){
                                    cost = cost - StartGame.cost_array[9];
                                    Tower_Tang n_t = new Tower_Tang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }

                        }
                        if (StartGame.mouse == 4){
                            if(index[3] == "wukong"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[0] >= 0){
                                    cost = cost - StartGame.cost_array[0];
                                    Tower_Wukong n_t = new Tower_Wukong(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "mord"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[1] >= 0){
                                    cost = cost - StartGame.cost_array[1];
                                    Tower_Mord n_t = new Tower_Mord(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "diana"  && block[j][i].kind == 1){
                                if(cost - StartGame.cost_array[2] >= 0){
                                    cost = cost - StartGame.cost_array[2];
                                    Tower_Diana n_t = new Tower_Diana(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                    block[j][i].have_tower = 1;
                                    block[j][i].block_tower = n_t;
                                }
                            }else if(index[3] == "wang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[3] >= 0){
                                    cost = cost - StartGame.cost_array[3];
                                    Tower_Wang n_t = new Tower_Wang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "hao"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[4] >= 0){
                                    cost = cost - StartGame.cost_array[4];
                                    Tower_Hao n_t = new Tower_Hao(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "garren"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[5] >= 0){
                                    cost = cost - StartGame.cost_array[5];
                                    Tower_Garen n_t = new Tower_Garen(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "tynda"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[6] >= 0){
                                    cost = cost - StartGame.cost_array[6];
                                    Tower_Tryndamere n_t = new Tower_Tryndamere(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "karthus"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[7] >= 0){
                                    cost = cost - StartGame.cost_array[7];
                                    Tower_Karthus n_t = new Tower_Karthus(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "kassdin"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[8] >= 0){
                                    cost = cost - StartGame.cost_array[8];
                                    Tower_Kassadin n_t = new Tower_Kassadin(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[3] == "tang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[9] >= 0){
                                    cost = cost - StartGame.cost_array[9];
                                    Tower_Tang n_t = new Tower_Tang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }

                        }
                        if (StartGame.mouse == 5){
                            if(index[4] == "wukong"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[0] >= 0){
                                    cost = cost - StartGame.cost_array[0];
                                    Tower_Wukong n_t = new Tower_Wukong(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "mord"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[1] >= 0){
                                    cost = cost - StartGame.cost_array[1];
                                    Tower_Mord n_t = new Tower_Mord(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "diana"  && block[j][i].kind == 1){
                                if(cost - StartGame.cost_array[2] >= 0){
                                    cost = cost - StartGame.cost_array[2];
                                    Tower_Diana n_t = new Tower_Diana(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                    block[j][i].have_tower = 1;
                                    block[j][i].block_tower = n_t;
                                }
                            }else if(index[4] == "wang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[3] >= 0){
                                    cost = cost - StartGame.cost_array[3];
                                    Tower_Wang n_t = new Tower_Wang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "hao"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[4] >= 0){
                                    cost = cost - StartGame.cost_array[4];
                                    Tower_Hao n_t = new Tower_Hao(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "garren"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[5] >= 0){
                                    cost = cost - StartGame.cost_array[5];
                                    Tower_Garen n_t = new Tower_Garen(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "tynda"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[6] >= 0){
                                    cost = cost - StartGame.cost_array[6];
                                    Tower_Tryndamere n_t = new Tower_Tryndamere(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "karthus"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[7] >= 0){
                                    cost = cost - StartGame.cost_array[7];
                                    Tower_Karthus n_t = new Tower_Karthus(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "kassdin"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[8] >= 0){
                                    cost = cost - StartGame.cost_array[8];
                                    Tower_Kassadin n_t = new Tower_Kassadin(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[4] == "tang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[9] >= 0){
                                    cost = cost - StartGame.cost_array[9];
                                    Tower_Tang n_t = new Tower_Tang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }

                        }
                        if (StartGame.mouse == 6 ){
                            if(index[5] == "wukong"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[0] >= 0){
                                    cost = cost - StartGame.cost_array[0];
                                    Tower_Wukong n_t = new Tower_Wukong(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "mord"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[1] >= 0){
                                    cost = cost - StartGame.cost_array[1];
                                    Tower_Mord n_t = new Tower_Mord(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "diana"  && block[j][i].kind == 1){
                                if(cost - StartGame.cost_array[2] >= 0){
                                    cost = cost - StartGame.cost_array[2];
                                    Tower_Diana n_t = new Tower_Diana(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                    block[j][i].have_tower = 1;
                                    block[j][i].block_tower = n_t;
                                }
                            }else if(index[5] == "wang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[3] >= 0){
                                    cost = cost - StartGame.cost_array[3];
                                    Tower_Wang n_t = new Tower_Wang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "hao"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[4] >= 0){
                                    cost = cost - StartGame.cost_array[4];
                                    Tower_Hao n_t = new Tower_Hao(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "garren"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[5] >= 0){
                                    cost = cost - StartGame.cost_array[5];
                                    Tower_Garen n_t = new Tower_Garen(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "tynda"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[6] >= 0){
                                    cost = cost - StartGame.cost_array[6];
                                    Tower_Tryndamere n_t = new Tower_Tryndamere(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "karthus"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[7] >= 0){
                                    cost = cost - StartGame.cost_array[7];
                                    Tower_Karthus n_t = new Tower_Karthus(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "kassdin"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[8] >= 0){
                                    cost = cost - StartGame.cost_array[8];
                                    Tower_Kassadin n_t = new Tower_Kassadin(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }else if(index[5] == "tang"  && block[j][i].kind == 0){
                                if(cost - StartGame.cost_array[9] >= 0){
                                    cost = cost - StartGame.cost_array[9];
                                    Tower_Tang n_t = new Tower_Tang(context, block, block[j][i].x, block[j][i].y);
                                    tower[number_of_tower] = n_t;
                                    number_of_tower++;
                                }
                            }

                        }
                    }
                }
            }

        }
        return true;
    }

}
