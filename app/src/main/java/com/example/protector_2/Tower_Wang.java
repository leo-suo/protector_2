package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Wang extends Tower {
    public Tower_Wang(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 200;
        ingame = 1;
        which_frame = 0;
        max_frame = 10;
        attack_damage = 5;
        cost = 14;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[11];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.y_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.y_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.y_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.y_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.y_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.y_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.y_7);
        bit_tower[7] = BitmapFactory.decodeResource(getResources(), R.drawable.y_8);
        bit_tower[8] = BitmapFactory.decodeResource(getResources(), R.drawable.y_9);
        bit_tower[9] = BitmapFactory.decodeResource(getResources(), R.drawable.y_10);
        bit_tower[10] = BitmapFactory.decodeResource(getResources(), R.drawable.y_11);
    }

}
