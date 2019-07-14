package com.example.protector_2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class bag_Activity extends AppCompatActivity {
    Bitmap background;
    public String bag_info;
    public int [] your_own_tower;
    public int bag_width = 10;
    public int bag_height = 10;
    Bitmap [][] map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_);

        readfiles();
        ImageView tower1 = findViewById(R.id.tower1);
        ImageView tower2 = findViewById(R.id.tower1);
        ImageView tower3 = findViewById(R.id.tower3);
        ImageView tower4 = findViewById(R.id.tower4);
        ImageView [] towers  ={tower1,tower2,tower3,tower4};
        System.out.println("Hello, you are here!");
        show_tower(your_own_tower,towers);

    }


    public void readfiles() {
        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.bag_info);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if (is != null) {
            try {
                while ((data = reader.readLine()) != null) {
                    sbuffer.append(data + "\n");
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bag_info = sbuffer.toString();

        int length = bag_info.length()-1;
        System.out.println(length);
        your_own_tower = new int[length];
        for(int i=0;i<length;i++){
            your_own_tower[i] = Integer.parseInt(Character.toString(bag_info.charAt(i)));
        }

    }

    public void show_tower(int []your_own_tower, ImageView [] towers){
        //map = new Bitmap[bag_height][bag_width];

        int length = your_own_tower.length;
        for(int i = 0; i < length; i++){
            int num = 4-your_own_tower[i];

            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            towers[num].setColorFilter(filter);
        }


    }


}



