package com.example.protector_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class shop_Activity extends AppCompatActivity {
    public int gold_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_);
        gold_num = 0;
        TextView t = findViewById(R.id.Gold);
        t.setText("Gold :" +String.valueOf(gold_num));
    }

    public void onBackButtonClick(View View){
        finish();
    }

    public void onPurchaseClick(View View){
        String num = String.valueOf(gold_num+100);
        gold_num+=100;
        TextView t = findViewById(R.id.Gold);
        t.setText("Gold: "+num );
    }

}
