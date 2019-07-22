package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Hao extends Tower{
    public Tower_Hao(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 200;
        ingame = 1;
        which_frame = 0;
        max_frame = 20;
        attack_damage = 5;
        cost = 12;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[21];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_7);
        bit_tower[7] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_8);
        bit_tower[8] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_9);
        bit_tower[9] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_10);
        bit_tower[10] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_11);
        bit_tower[11] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_12);
        bit_tower[12] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_13);
        bit_tower[13] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_14);
        bit_tower[14] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_15);
        bit_tower[15] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_16);
        bit_tower[16] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_17);
        bit_tower[17] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_18);
        bit_tower[18] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_19);
        bit_tower[19] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_20);
        bit_tower[20] = BitmapFactory.decodeResource(getResources(), R.drawable.hao_21);

    }
}
