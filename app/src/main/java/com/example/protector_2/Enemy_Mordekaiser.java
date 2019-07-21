package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;



public class Enemy_Mordekaiser extends Enemy {
    public Enemy_Mordekaiser(Context context, int x, int y, Block[][] block, int order) {
        super(context, x, y, block, order);
        max_frame = 6;
        attack_damage = 50;
        hp = 500;
        total_hp = 500;
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
        yasuos = new Bitmap[7];
        yasuos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.m_1);
        yasuos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.m_2);
        yasuos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.m_3);
        yasuos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.m_4);
        yasuos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.m_5);
        yasuos[5] = BitmapFactory.decodeResource(getResources(), R.drawable.m_6);
        yasuos[6] = BitmapFactory.decodeResource(getResources(), R.drawable.m_7);

    }

}

