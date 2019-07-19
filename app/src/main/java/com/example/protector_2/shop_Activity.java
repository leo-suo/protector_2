package com.example.protector_2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class shop_Activity extends AppCompatActivity {
    public int gold_num;
    public int[] tower_id = {R.id.t1_bt,R.id.t2_bt,R.id.t3_bt,R.id.t4_bt,R.id.t5_bt,R.id.t6_bt,R.id.t7_bt,
            R.id.t8_bt,R.id.t9_bt,R.id.t10_bt};


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
        Intent intent = getIntent();
        gold_num = intent.getIntExtra("Gold",-1);
        TextView t = findViewById(R.id.Gold_text);
        t.setText("Gold :" +String.valueOf(gold_num));

    }

    public void onBackButtonClick(View View){
        Intent intent = new Intent();
        intent.putExtra("Gold",gold_num);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void onSummonClick(View View){
        Intent intent = new Intent(shop_Activity.this,
                Summon_Activity.class);
        startActivity(intent);
    }

    public void onPurchaseClick(View view){
        int p = 0;
        for(int i = 0; i < tower_id.length; i++){
            if(tower_id[i] == view.getId()){
                p = (i+1)*10;
            }
        }
        if (p <= gold_num){
            gold_num-=p;
            String num = String.valueOf(gold_num);
            TextView t = findViewById(R.id.Gold_text);
            t.setText("Gold: "+num );
        } else{
            Context context = getApplicationContext();
            CharSequence text = "Not Enough Gold!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }
    }


}
