package com.example.protector_2;


public class Block {
    public int groundId= 100;
    public int blockSize = 100;
    public int airId;
    public int x;
    public int y;
    public int have_tower = 0;
    public Tower block_tower;
    // 4 direction
    int direction = 5;
    // right = 0; up = 1; left = 2; down = 3


    // Represent whether it is a end point
    public int end = 0;

    public int kind = 1;

    public Block(int x, int y, int groundid, int airid){
        this.x = x;
        this.y = y;
        this.groundId = groundid;
        this.airId = airid;
        if(groundid == 3){
            end = 1;
        }
        if(groundid == 0){
            kind = 0; // 0 represent it is the wall
        }
    }


    public boolean in_the_block(float point_x, float point_y){
        if(point_x >= x &&
        point_x < x + blockSize &&
        point_y >= y &&
        point_y < y + blockSize){
            return true;
        }else{
            return false;
        }
    }

    public int center_x(){
        return x + blockSize / 2;
    }

    public int center_y(){
        return  y + blockSize / 2;
    }
}