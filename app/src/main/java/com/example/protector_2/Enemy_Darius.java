package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;



public class Enemy_Darius extends Enemy {
    public Enemy_Darius(Context context, int x, int y, Block[][] block, int order) {
        super(context, x, y, block, order);
        max_frame = 7;
        attack_damage = 10;
        hp = 400;
        define();
    }

    @Override
    public int return_attack_damage(){
        return attack_damage;
    }

    @Override
    public int return_hp(){
        return hp;
    }

    public void define() { // yasuo move init
        yasuos = new Bitmap[8];
        yasuos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.d_1);
        yasuos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.d_2);
        yasuos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.d_3);
        yasuos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.d_4);
        yasuos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.d_5);
        yasuos[5] = BitmapFactory.decodeResource(getResources(), R.drawable.d_6);
        yasuos[6] = BitmapFactory.decodeResource(getResources(), R.drawable.d_7);
        yasuos[7] = BitmapFactory.decodeResource(getResources(), R.drawable.d_8);

    }

}

