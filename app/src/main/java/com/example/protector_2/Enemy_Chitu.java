package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;



public class Enemy_Chitu extends Enemy {
    public Enemy_Chitu(Context context, int x, int y, Block[][] block, int order) {
        super(context, x, y, block, order);
        max_frame = 4;
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
        yasuos = new Bitmap[5];
        yasuos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.chitu_1);
        yasuos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.chitu_2);
        yasuos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.chitu_3);
        yasuos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.chitu_4);
        yasuos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.chitu_5);

    }

}

