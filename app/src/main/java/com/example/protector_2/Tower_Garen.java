package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Garen extends Tower {
    public Tower_Garen(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 200;
        ingame = 1;
        which_frame = 0;
        max_frame = 29;
        attack_damage = 5;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[30];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.g_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.g_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.g_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.g_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.g_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.g_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.g_7);
        bit_tower[7] = BitmapFactory.decodeResource(getResources(), R.drawable.g_8);
        bit_tower[8] = BitmapFactory.decodeResource(getResources(), R.drawable.g_9);
        bit_tower[9] = BitmapFactory.decodeResource(getResources(), R.drawable.g_10);
        bit_tower[10] = BitmapFactory.decodeResource(getResources(), R.drawable.g_11);
        bit_tower[11] = BitmapFactory.decodeResource(getResources(), R.drawable.g_12);
        bit_tower[12] = BitmapFactory.decodeResource(getResources(), R.drawable.g_13);
        bit_tower[13] = BitmapFactory.decodeResource(getResources(), R.drawable.g_14);
        bit_tower[14] = BitmapFactory.decodeResource(getResources(), R.drawable.g_15);
        bit_tower[15] = BitmapFactory.decodeResource(getResources(), R.drawable.g_16);
        bit_tower[16] = BitmapFactory.decodeResource(getResources(), R.drawable.g_17);
        bit_tower[17] = BitmapFactory.decodeResource(getResources(), R.drawable.g_18);
        bit_tower[18] = BitmapFactory.decodeResource(getResources(), R.drawable.g_19);
        bit_tower[19] = BitmapFactory.decodeResource(getResources(), R.drawable.g_20);
        bit_tower[20] = BitmapFactory.decodeResource(getResources(), R.drawable.g_21);
        bit_tower[21] = BitmapFactory.decodeResource(getResources(), R.drawable.g_22);
        bit_tower[22] = BitmapFactory.decodeResource(getResources(), R.drawable.g_23);
        bit_tower[23] = BitmapFactory.decodeResource(getResources(), R.drawable.g_24);
        bit_tower[24] = BitmapFactory.decodeResource(getResources(), R.drawable.g_25);
        bit_tower[25] = BitmapFactory.decodeResource(getResources(), R.drawable.g_26);
        bit_tower[26] = BitmapFactory.decodeResource(getResources(), R.drawable.g_27);
        bit_tower[27] = BitmapFactory.decodeResource(getResources(), R.drawable.g_28);
        bit_tower[28] = BitmapFactory.decodeResource(getResources(), R.drawable.g_29);
        bit_tower[29] = BitmapFactory.decodeResource(getResources(), R.drawable.g_30);
    }


}
