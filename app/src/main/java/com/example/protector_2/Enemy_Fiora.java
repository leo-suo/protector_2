package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;



public class Enemy_Fiora extends Enemy {
    public Enemy_Fiora(Context context, int x, int y, Block[][] block, int order) {
        super(context, x, y, block, order);
        max_frame = 11;
        attack_damage = 1;
        hp = 500;
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
        yasuos = new Bitmap[12];
        yasuos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.f_1);
        yasuos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.f_2);
        yasuos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.f_3);
        yasuos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.f_4);
        yasuos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.f_5);
        yasuos[5] = BitmapFactory.decodeResource(getResources(), R.drawable.f_6);
        yasuos[6] = BitmapFactory.decodeResource(getResources(), R.drawable.f_7);
        yasuos[7] = BitmapFactory.decodeResource(getResources(), R.drawable.f_8);
        yasuos[8] = BitmapFactory.decodeResource(getResources(), R.drawable.f_9);
        yasuos[9] = BitmapFactory.decodeResource(getResources(), R.drawable.f_10);
        yasuos[10] = BitmapFactory.decodeResource(getResources(), R.drawable.f_11);
        yasuos[11] = BitmapFactory.decodeResource(getResources(), R.drawable.f_12);

    }

}

