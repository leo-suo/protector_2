package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;



public class Enemy_Jinx extends Enemy {
    public Enemy_Jinx(Context context, int x, int y, Block[][] block, int order) {
        super(context, x, y, block, order);
        max_frame = 9;
        attack_damage = 50;
        hp = 100;
        total_hp = 100;
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
        yasuos = new Bitmap[10];
        yasuos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_1);
        yasuos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_2);
        yasuos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_3);
        yasuos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_4);
        yasuos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_5);
        yasuos[5] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_6);
        yasuos[6] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_7);
        yasuos[7] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_8);
        yasuos[8] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_9);
        yasuos[9] = BitmapFactory.decodeResource(getResources(), R.drawable.jinx_10);

    }

}

