package com.example.protector_2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

abstract public class Tower extends View{
    // map information
    public int worldWidth = 12;
    public int worldHeight = 6;
    public int blockSize = 100;
    public Block[][] block;
    Bitmap[] bit_tower;
    public int total_hp = 100;
    public int is_Zhande = 0;

    public int attacking = 0;
    public Enemy who_i_attack;

    public static int cost = 10;

    public int kind = 0;
    // if kind = 0, it means that we can put it into roads
    // if kind = 1, it means that we can't out it into roads

    // tower information
    public int towerX;
    public int towerY;

    public int which_frame = 0;
    public int max_frame = 5;

    public int attack_range = 0;
    // the attack range of this tower
    public int attack_damage = 0;

    public int hp = 100;
    // hp of this tower

    public int ingame = 0;
    public void be_attack(int attack){
        hp = hp - attack;
    }

    public boolean is_dead(){
        if (hp <= 0){
            return true;
        }
        return false;
    }

    public Tower(Context context, Block[][] block, int x, int y) {
        super(context);
        this.block = block;
        towerX = x;
        towerY = y;
    }

    public int get_kind(){
        return kind;
    }

    public void get_which_draw() {
        if (which_frame < max_frame){
            which_frame = which_frame + 1;
        }else{
            which_frame = 0;
        }
    }

    public int center_x(){
        return towerX + blockSize / 2;
    }

    public int center_y(){

        return  towerY + blockSize / 2;
    }

    public void update(int x, int y, Enemy who_notify_me) {
        if(!who_notify_me.is_dead()){
            double dis = Math.sqrt(Math.pow((x - center_x()), 2)+ Math.pow(y - center_y(), 2));
            if (attacking == 0) { // now attack nobody
                if (dis <= attack_range ) { // in range
                    attacking = 1; // set have already attack one
                    who_notify_me.be_attacked(attack_damage);
                    who_i_attack = who_notify_me;
                }
            }else if(attacking == 1 && who_notify_me == who_i_attack){ // I have already attacked someone
                if(dis > attack_range){ // out of range
                    attacking = 0;
                }else if (dis <= attack_range){
                    who_i_attack.be_attacked(attack_damage);
                    if(who_i_attack.hp <= 0){
                        attacking = 0;
                    }
                }

            }
        }else {
            if(who_notify_me == who_i_attack){
                attacking = 0;
            }
        }
    }

}

