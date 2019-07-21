package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;



public class Enemy_Lee extends Enemy {
    public Enemy_Lee(Context context, int x, int y, Block[][] block, int order) {
        super(context, x, y, block, order);
        max_frame = 5;
        attack_damage = 25;
        hp = 250;
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
        yasuos = new Bitmap[6];
        yasuos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.lee_1);
        yasuos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.lee_2);
        yasuos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.lee_3);
        yasuos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.lee_4);
        yasuos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.lee_5);
        yasuos[5] = BitmapFactory.decodeResource(getResources(), R.drawable.lee_6);

    }

}

