package com.example.protector_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class bag_Activity extends AppCompatActivity {
    public String bag_info;
    public int [] your_own_tower;
    public int num_towers = 4;
    public int [] all_towers  = {1,2,3,4};


    public static int getCountFromArray(int v, int[] array) {
        int count = 0;
        for (int i : array) {
            if (i == v) {
                count++;
            }
        }
        return count;
    }
    public static int[] deleteArrayCount(int v, int[] array)//删除数组中v的值
    {
        int counts = getCountFromArray(v, array);
        int[] re_array = new int[array.length - counts];//不知道要删除几个的情况下
        int index = 0;
        for (int i : array) {
            if (i != v) {
                re_array[index] = i;
                index++;
            }
        }
        return re_array;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_);

        readfiles();
        ImageView tower1 = findViewById(R.id.tower1);
        ImageView tower2 = findViewById(R.id.tower2);
        ImageView tower3 = findViewById(R.id.tower3);
        ImageView tower4 = findViewById(R.id.tower4);
        ImageView [] towers  ={tower1,tower2,tower3,tower4};
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
        your_own_tower = new int[length];
        for(int i=0;i<length;i++){
            your_own_tower[i] = Integer.parseInt(Character.toString(bag_info.charAt(i)));
        }

    }

    public void show_tower(int []your_own_tower, ImageView [] towers){
        int length = your_own_tower.length;

        int []not_own_tower = null;
        for(int i = 0; i < length; i++){
            int num = your_own_tower[i];
            not_own_tower = (deleteArrayCount(num,all_towers));
        }


        for(int i=0;i<not_own_tower.length;i++){
            int num = not_own_tower[i]-1;
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            towers[num].setColorFilter(filter);
        }

    }


}



