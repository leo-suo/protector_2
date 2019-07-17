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

    public Block[][] block;

    public static Tower[] tower;

    // enemy
    public yasuo yasuo_array[];

    public String map_info;

    int number_of_yasuo = 10;
    int which_yasuo_now = 0; // to make yasuo appear in different order

    public static int number_of_tower = 0;
    // the number of the tower we create


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




    public GameView(Context context){
        super(context);
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

        // create yasuo class
        yasuo_array = new yasuo[number_of_yasuo];
        for (int i = 0; i < number_of_yasuo; ++i) {
            yasuo_array[i] = new yasuo(context, 0, 0,  block, which_yasuo_now);
            which_yasuo_now++;
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
                    }
                }
            }

        }
        return true;
    }

}
