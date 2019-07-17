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
