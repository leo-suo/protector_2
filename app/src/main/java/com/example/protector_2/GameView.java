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

    public static int total_hp = 1000;

    // enemy
    public Enemy yasuo_array[];

    public String map_info;

    int number_of_yasuo = 10;
    int which_yasuo_now = 0; // to make yasuo appear in different order

    public static int number_of_tower = 0;
    // the number of the tower we create

    static StartGame sg;

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


    public  static  void is_finish(){
        //if(total_hp <= 0){
            sg.end();
        //}
    }

    public GameView(Context context){

        super(context);

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
            }

        }



        // we want to the bird to be displayed at the centre of the screen
        for(int i = 0; i < number_of_yasuo; ++i){
            if(yasuo_array[i].ingame == 1 && yasuo_array[i].hp > 0){
                canvas.drawBitmap(yasuo_array[i].yasuos[yasuo_array[i].yasuoFrame], yasuo_array[i].yasuoX,
                        yasuo_array[i].yasuoY, null);
            }
        }

        // draw tower
        for(int i = 0; i < number_of_tower; ++i){
            if(tower[i].ingame == 1){
                Paint paint = new Paint();
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
                            Tower_Garen new_t = new Tower_Garen(context, block, block[j][i].x, block[j][i].y);
                            tower[number_of_tower] = new_t;
                            number_of_tower++;
                        }
                        if (StartGame.mouse == 2){
                            Tower_Diana new_t = new Tower_Diana(context, block, block[j][i].x, block[j][i].y);
                            tower[number_of_tower] = new_t;
                            number_of_tower++;
                        }
                        if (StartGame.mouse == 3){
                            Tower_Karthus new_t = new Tower_Karthus(context, block, block[j][i].x, block[j][i].y);
                            tower[number_of_tower] = new_t;
                            number_of_tower++;
                        }
                        if (StartGame.mouse == 4){
                            Tower_Mord new_t = new Tower_Mord(context, block, block[j][i].x, block[j][i].y);
                            tower[number_of_tower] = new_t;
                            number_of_tower++;
                        }
                        if (StartGame.mouse == 5){
                            Tower_Kassadin new_t = new Tower_Kassadin(context, block, block[j][i].x, block[j][i].y);
                            tower[number_of_tower] = new_t;
                            number_of_tower++;
                        }
                        if (StartGame.mouse == 6){
                            Tower_Tryndamere new_t = new Tower_Tryndamere( context, block, block[j][i].x, block[j][i].y);
                            tower[number_of_tower] = new_t;
                            number_of_tower++;
                        }
                    }
                }
            }

        }
        return true;
    }

}
