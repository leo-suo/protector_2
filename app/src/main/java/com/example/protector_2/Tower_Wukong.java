package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower_Wukong extends Tower {
    public Tower_Wukong(Context context, Block[][] block, int x, int y){
        super(context, block, x, y);
        kind = 1;
        attack_range = 200;
        ingame = 1;
        which_frame = 0;
        max_frame = 6;
        attack_damage = 5;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[7];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.w_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.w_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.w_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.w_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.w_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.w_6);
        bit_tower[6] = BitmapFactory.decodeResource(getResources(), R.drawable.w_7);
    }


}
