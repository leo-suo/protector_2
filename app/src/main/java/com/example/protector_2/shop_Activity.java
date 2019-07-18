package com.example.protector_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class shop_Activity extends AppCompatActivity {
    public int gold_num;
    public int[] tower_id = {R.id.t1_bt,R.id.t2_bt,R.id.t3_bt,R.id.t4_bt,R.id.t5_bt,R.id.t6_bt,R.id.t7_bt,
            R.id.t8_bt,R.id.t9_bt,R.id.t10_bt,R.id.t11_bt,R.id.t12_bt,R.id.t13_bt,R.id.t14_bt,R.id.t15_bt};


    public class tower{
        int price;
        int Rid;
        public tower(int Pirce, int Rid){
            this.price=Pirce;
            this.Rid = Rid;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_);
        read_gold_files();
        TextView t = findViewById(R.id.Gold_text);
        t.setText("Gold :" +String.valueOf(gold_num));

    }

    public void onBackButtonClick(View View){
        finish();
    }

    public void onSummonClick(View View){
        Intent intent = new Intent(shop_Activity.this,
                Summon_Activity.class);
        startActivity(intent);
    }

    public void onPurchaseClick(View View){
        String num = String.valueOf(gold_num+100);
        gold_num-=100;
        TextView t = findViewById(R.id.Gold_text);
        t.setText("Gold: "+num );
    }


    public void read_gold_files(){
        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.gold_info);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            try {
                while ((data = reader.readLine()) != null) {
                    sbuffer.append(data);
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        gold_num = Integer.parseInt(sbuffer.toString());
    }

}
