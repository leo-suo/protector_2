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
        max_frame = 5;
        attack_damage = 5;
        define();

    }

    public void define() { // yasuo move init
        bit_tower = new Bitmap[6];
        bit_tower[0] = BitmapFactory.decodeResource(getResources(), R.drawable.garen_1);
        bit_tower[1] = BitmapFactory.decodeResource(getResources(), R.drawable.garen_2);
        bit_tower[2] = BitmapFactory.decodeResource(getResources(), R.drawable.garen_3);
        bit_tower[3] = BitmapFactory.decodeResource(getResources(), R.drawable.garen_4);
        bit_tower[4] = BitmapFactory.decodeResource(getResources(), R.drawable.garen_5);
        bit_tower[5] = BitmapFactory.decodeResource(getResources(), R.drawable.garen_6);
    }


}
