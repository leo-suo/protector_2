package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Diana extends Tower {
    public Tower_Diana(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 50;
        ingame = 1;
        which_frame = 0;
        max_frame = 30;
        attack_damage = 1;
        hp = 20000;
        total_hp = 20000;
        is_Zhande = 1;
        cost = 11;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[31];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.di_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.di_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.di_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.di_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.di_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.di_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.di_7);
        bit_tower[7] = BitmapFactory.decodeResource(getResources(), R.drawable.di_8);
        bit_tower[8] = BitmapFactory.decodeResource(getResources(), R.drawable.di_9);
        bit_tower[9] = BitmapFactory.decodeResource(getResources(), R.drawable.di_10);
        bit_tower[10] = BitmapFactory.decodeResource(getResources(), R.drawable.di_11);
        bit_tower[11] = BitmapFactory.decodeResource(getResources(), R.drawable.di_12);
        bit_tower[12] = BitmapFactory.decodeResource(getResources(), R.drawable.di_13);
        bit_tower[13] = BitmapFactory.decodeResource(getResources(), R.drawable.di_14);
        bit_tower[14] = BitmapFactory.decodeResource(getResources(), R.drawable.di_15);
        bit_tower[15] = BitmapFactory.decodeResource(getResources(), R.drawable.di_16);
        bit_tower[16] = BitmapFactory.decodeResource(getResources(), R.drawable.di_17);
        bit_tower[17] = BitmapFactory.decodeResource(getResources(), R.drawable.di_18);
        bit_tower[18] = BitmapFactory.decodeResource(getResources(), R.drawable.di_19);
        bit_tower[19] = BitmapFactory.decodeResource(getResources(), R.drawable.di_20);
        bit_tower[20] = BitmapFactory.decodeResource(getResources(), R.drawable.di_21);
        bit_tower[21] = BitmapFactory.decodeResource(getResources(), R.drawable.di_22);
        bit_tower[22] = BitmapFactory.decodeResource(getResources(), R.drawable.di_23);
        bit_tower[23] = BitmapFactory.decodeResource(getResources(), R.drawable.di_24);
        bit_tower[24] = BitmapFactory.decodeResource(getResources(), R.drawable.di_25);
        bit_tower[25] = BitmapFactory.decodeResource(getResources(), R.drawable.di_26);
        bit_tower[26] = BitmapFactory.decodeResource(getResources(), R.drawable.di_27);
        bit_tower[27] = BitmapFactory.decodeResource(getResources(), R.drawable.di_28);
        bit_tower[28] = BitmapFactory.decodeResource(getResources(), R.drawable.di_29);
        bit_tower[29] = BitmapFactory.decodeResource(getResources(), R.drawable.di_30);
        bit_tower[30] = BitmapFactory.decodeResource(getResources(), R.drawable.di_31);
    }


}
