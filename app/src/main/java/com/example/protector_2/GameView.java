package com.example.protector_2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import android.graphics.*;
import android.widget.Button;

import java.util.Random;

public class GameView extends View{
    public int worldWidth = 12;
    public int worldHeight = 6;
    public int blockSize = 100;

    public Block[][] block;

    // enemy
    public yasuo yasuo_array[];

    public String map_info;

    int number_of_yasuo = 10;
    int which_yasuo_now = 0; // to make yasuo appear in different order


    Handler handler; // handler is required to schedule a runnable after some delay
    Runnable runnable;
    final int UPDATE_MILLTS = 50;
    Bitmap background;

    Display display;
    Point point;
    int dWith, dHeight;// Device width width and height respectively
    Rect rect;


    // map
    Bitmap [][] map;

    boolean gameStat = true;

    Button tower_1, tower_2, tower_3, tower_4, tower_5;



    public GameView(Context context, String map_info){
        super(context);
        this.map_info = map_info;

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
            }

        }



        // we want to the bird to be displayed at the centre of the screen
        for(int i = 0; i < number_of_yasuo; ++i){
            if(yasuo_array[i].ingame == 1){
                canvas.drawBitmap(yasuo_array[i].yasuos[yasuo_array[i].yasuoFrame], yasuo_array[i].yasuoX,
                        yasuo_array[i].yasuoY, null);
            }
        }

        handler.postDelayed(runnable,UPDATE_MILLTS);
    }



    // Get the touch event

    /*

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            // Here we want the bird to move up by some unit
            //velocity = -30;
            gameStat = true;
        }


        return true;
    }
    */
}
