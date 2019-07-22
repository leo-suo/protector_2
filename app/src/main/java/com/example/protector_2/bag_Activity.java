package com.example.protector_2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.graphics.*;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class bag_Activity extends AppCompatActivity {
    public String bag_info;
    public int [] your_own_tower;
    public int [] sell_id = {R.id.sell1,R.id.sell2,R.id.sell3,R.id.sell4,R.id.sell5,R.id.sell6,R.id.sell7,R.id.sell8,R.id.sell9,R.id.sell10};
    public int [] upgrade_id ={R.id.Upgrade1,R.id.Upgrade2,R.id.Upgrade3,R.id.Upgrade4,R.id.Upgrade5,R.id.Upgrade6,R.id.Upgrade7,R.id.Upgrade8,R.id.Upgrade9,R.id.Upgrade10};
    public int [] tower_id = {R.id.tower1, R.id.tower2, R.id.tower3,R.id.tower4,R.id.tower5,R.id.tower6,R.id.tower7,R.id.tower8,R.id.tower9,R.id.tower10};
    public int [] tower_price = {1,2,3,4,5,6,7,8,9,10};

    //show numbers of towers you have
    public int [] tower_owns = {1,1,1,1,1,1,1,1,1,1};
    public int [] text_id = {R.id.num_tower1,R.id.num_tower2,R.id.num_tower3,R.id.num_tower4,R.id.num_tower5,R.id.num_tower6,R.id.num_tower7,R.id.num_tower8,R.id.num_tower9,R.id.num_tower10};
    public String gold_info;
    public int num_gold;

    public int []check_id= {R.id.checkBox,R.id.checkBox2,R.id.checkBox3,R.id.checkBox4,R.id.checkBox5,R.id.checkBox6,R.id.checkBox7,R.id.checkBox8,R.id.checkBox9,R.id.checkBox10};
    public int [] cost = {20,25,10,25,25,25,30,30,35,35};


    public static int getCountFromArray(int v, int[] array) {
        int count = 0;
        for (int i : array) {
            if (i == v) {
                count++;
            }
        }
        return count;
    }
    public static int[] deleteArrayCount(int v, int[] array)
    {
        int counts = getCountFromArray(v, array);
        int[] re_array = new int[array.length - counts];
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
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bag_);

        Intent intent = getIntent();
        num_gold = intent.getIntExtra("Gold",-1);
        tower_owns = intent.getIntArrayExtra("tower");


       // readfiles();
       // show_tower(your_own_tower);
        show(tower_owns);
        TextView t = findViewById(R.id.your_gold);
        t.setText("Your Gold: " + String.valueOf(num_gold));

        //show numbers of tower you have
        for(int i = 0; i< text_id.length;i++){
            TextView x = findViewById(text_id[i]);
            x.setText("x"+ tower_owns[i]+ "  "+"Cost: " + cost[i]);
        }




    }


    public void readfiles() {
        //-----------read beg_info----------
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


    public void show(int []tower_own){
        for(int i = 0;i<tower_own.length;i++){
            if(tower_own[i] ==0){
                ImageView tower = findViewById(tower_id[i]);
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                tower.setColorFilter(filter);

            }
            else{
                ImageView tower = findViewById(tower_id[i]);
                tower.setColorFilter(null);
            }
        }
    }



    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent intent){
        super.onActivityResult(requestcode,resultcode,intent);
        num_gold = intent.getIntExtra("Gold",-1);
        tower_owns = intent.getIntArrayExtra("tower");
        for(int i = 0; i< text_id.length;i++){
            TextView x = findViewById(text_id[i]);
            x.setText("x"+ tower_owns[i]+ "  "+"Cost: " + cost[i]);
        }
        show(tower_owns);
        TextView t = findViewById(R.id.your_gold);
        t.setText("Your Gold " + num_gold);
    }

    public void onBuyClick(View view){
        Intent intent = new Intent(bag_Activity.this,shop_Activity.class);
        intent.putExtra("Gold",num_gold);
        intent.putExtra("tower",tower_owns);
        startActivityForResult(intent,0);
    }


    public void onBackClick(View view){
        //check button
        final int num_fight_tower = 6;
        int num_fight_tower_you_choose = 0;
        int [] fight_list = new int[] {0,0,0,0,0,0,0,0,0,0};
        boolean back = true;

        for(int i = 0;i<check_id.length;i++){
            CheckBox cur_check = findViewById(check_id[i]);
            if(cur_check.isChecked()){
                if(num_fight_tower_you_choose<num_fight_tower){
                    if(tower_owns[i]>0){
                        num_fight_tower_you_choose+=1;
                        fight_list[i] = 1;
                    }else{
                        Context context = getApplicationContext();
                        CharSequence text ="You don't have tower" + (i+1);
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context,text,duration).show();
                        back = false;
                    }

                } else{
                    cur_check.setChecked(false);
                    Context context = getApplicationContext();
                    CharSequence text ="You can only choose at most 6 towers in the game!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                    back = false;

                }
            }
            else{
                fight_list[i] = 0;
            }
        }


        if(back == true){
            for(int i =0;i<fight_list.length;i++){
                System.out.println("You are in Bag");
                System.out.println(fight_list[i]);
            }
            Intent intent = new Intent();
            intent.putExtra("Gold",num_gold);
            intent.putExtra("tower",tower_owns);
            intent.putExtra("fight",fight_list);
            intent.putExtra("cost",cost);
            setResult(RESULT_OK,intent);
            finish();
        }

    }

    public void onSellClick(View view){
        for(int i = 0; i<tower_id.length;i++){
            if(sell_id[i] == view.getId()){
                if(tower_owns[i] >0){
                    //price
                    num_gold+= tower_price[i];
                    TextView t = findViewById(R.id.your_gold);
                    t.setText("Your Gold " + num_gold);
                    //update the tower
                    tower_owns[i] -=1;
                    TextView x = findViewById(text_id[i]);
                    x.setText("x"+ tower_owns[i]+ "  "+"Cost: " + cost[i]);
                    show(tower_owns);
                } else
                {
                    ImageView tower = findViewById(tower_id[i]);
                    tower.setColorFilter(null);
                }

            }
        }

    }

    public void onUpgradeClick(View view){
        for(int i =0;i<tower_id.length;i++){
            if(upgrade_id[i]== view.getId()){
                if(tower_owns[i] >=2){
                    if(num_gold >=10){
                        num_gold -=10;
                        tower_owns[i] -=1;
                        cost[i] -=1;
                        if(cost[i]<=0){
                            cost[i] = 0;
                        }

                        TextView t = findViewById(text_id[i]);
                        t.setText("x"+ tower_owns[i]+ "  "+"Cost: " + cost[i]);
                        TextView x = findViewById(R.id.your_gold);
                        x.setText("Your Gold " + num_gold);


                        Context context = getApplicationContext();
                        CharSequence text ="Upgrading successfully! Cost reduce!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context,text,duration).show();
                    } else{
                        Context context = getApplicationContext();
                        CharSequence text ="Upgrading your tower costs 10$";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context,text,duration).show();

                    }
                } else{
                    Context context = getApplicationContext();
                    CharSequence text ="Upgrading your tower costs 2 towers";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                }
            }
        }

    }





}



