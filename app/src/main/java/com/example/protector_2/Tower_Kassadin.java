package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Kassadin extends Tower {
    public Tower_Kassadin(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 250;
        ingame = 1;
        which_frame = 0;
        max_frame = 15;
        attack_damage = 6;
        cost = 14;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[16];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_7);
        bit_tower[7] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_8);
        bit_tower[8] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_9);
        bit_tower[9] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_10);
        bit_tower[10] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_11);
        bit_tower[11] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_12);
        bit_tower[12] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_13);
        bit_tower[13] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_14);
        bit_tower[14] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_15);
        bit_tower[15] = BitmapFactory.decodeResource(getResources(), R.drawable.ka_16);
    }


}
