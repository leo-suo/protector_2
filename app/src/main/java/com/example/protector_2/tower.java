package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class tower extends View{
    // map information
    public int worldWidth = 12;
    public int worldHeight = 6;
    public int blockSize = 100;
    public Block[][] block;

    // tower information
    public int towerX;
    public int towerY;

    public tower(Context context, Block[][] block) {
        super(context);
        this.block = block;
    }
}
