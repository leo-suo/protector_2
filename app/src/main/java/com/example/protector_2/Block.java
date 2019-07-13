package com.example.protector_2;


public class Block {
    public int groundId= 100;
    public int blockSize = 100;
    public int airId;
    public int x;
    public int y;
    // 4 direction
    int direction = 5;
    // right = 0; up = 1; left = 2; down = 3
    public int center_x = x + blockSize / 2;
    public int center_y = y + blockSize / 2;

    public Block(int x, int y, int groundid, int airid){
        this.x = x;
        this.y = y;
        this.groundId = groundid;
        this.airId = airid;
    }

    public boolean in_this_block(int a, int b){
        if(a >= x && a < x + blockSize && b >= y && b < y + blockSize){
            return true;
        }else {
            return false;
        }
    }
}