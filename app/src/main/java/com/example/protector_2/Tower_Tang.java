package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Tang extends Tower{
    public Tower_Tang(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 200;
        ingame = 1;
        which_frame = 0;
        max_frame = 11;
        attack_damage = 7;
        cost = 14;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[12];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.a_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.a_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.a_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.a_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.a_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.a_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.a_7);
        bit_tower[7] = BitmapFactory.decodeResource(getResources(), R.drawable.a_8);
        bit_tower[8] = BitmapFactory.decodeResource(getResources(), R.drawable.a_9);
        bit_tower[9] = BitmapFactory.decodeResource(getResources(), R.drawable.a_10);
        bit_tower[10] = BitmapFactory.decodeResource(getResources(), R.drawable.a_11);
        bit_tower[11] = BitmapFactory.decodeResource(getResources(), R.drawable.a_12);
    }
}
