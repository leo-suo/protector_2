package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Mord extends Tower {
    public Tower_Mord(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 200;
        ingame = 1;
        which_frame = 0;
        max_frame = 18;
        attack_damage = 3;
        cost = 20;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[19];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_7);
        bit_tower[7] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_8);
        bit_tower[8] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_9);
        bit_tower[9] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_10);
        bit_tower[10] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_11);
        bit_tower[11] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_12);
        bit_tower[12] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_13);
        bit_tower[13] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_14);
        bit_tower[14] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_15);
        bit_tower[15] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_16);
        bit_tower[16] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_17);
        bit_tower[17] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_18);
        bit_tower[18] = BitmapFactory.decodeResource(getResources(), R.drawable.mord_19);
    }


}
