package com.example.protector_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Summon_Activity extends AppCompatActivity {


    public int[] tower_img = {R.drawable.tower1,R.drawable.tower2,R.drawable.tower3,R.drawable.tower4,
            R.drawable.tower5,R.drawable.tower6,R.drawable.tower7, R.drawable.tower8,R.drawable.tower9,R.drawable.tower10};
    ImageView SummonView;
    public int Gold;
    public int [] tower_own;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summon_);
        SummonView = findViewById(R.id.SummonView);
        SummonView.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        Gold = intent.getIntExtra("Gold",-1);
        tower_own = intent.getIntArrayExtra("tower");
        TextView t = findViewById(R.id.goldView);
        t.setText("Gold :" +String.valueOf(Gold));

    }

    public void onBack(View view){
        Intent intent = new Intent();
        intent.putExtra("Gold",Gold);
        intent.putExtra("tower",tower_own);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void onPlayClick(View view){

        if(Gold < 10){
            Toast.makeText(Summon_Activity.this, "Not Enough Money!!", Toast.LENGTH_SHORT).show();
        } else{
            Gold-=10;
            String num = String.valueOf(Gold);
            TextView t = findViewById(R.id.goldView);
            t.setText("Gold: "+num );
            summon();
        }
    }

    private void summon() {
        int randomnum = (int) (Math.random() * 100);
        System.out.println("sdadsddassadasfhdhfhfdshfkjsfjkds");
        System.out.println(randomnum);
        int you_cannt_choudao = randomnum % 100;
        int lantian_baiyun = you_cannt_choudao % 10;
        if(you_cannt_choudao == 44){
            System.out.println(you_cannt_choudao);
            System.out.println("JIN SE CHUAN SHUO");
            showResult(9);
        }else if(you_cannt_choudao == 45 || you_cannt_choudao == 46 || you_cannt_choudao == 47 ||
                you_cannt_choudao == 48){
            System.out.println(you_cannt_choudao);
            System.out.println("kassdin");
            showResult(8);
        }else if(lantian_baiyun == 4){
            System.out.println(you_cannt_choudao);
            System.out.println("karthus");
            showResult(7);
        }else if(lantian_baiyun == 1){
            System.out.println(you_cannt_choudao);
            System.out.println("tydnmere");
            showResult(6);
        }else if(lantian_baiyun == 2){
            System.out.println(you_cannt_choudao);
            System.out.println("garen");
            showResult(5);
        }else if(lantian_baiyun == 3){
            System.out.println(you_cannt_choudao);
            System.out.println("hao");
            showResult(4);
        }else if(lantian_baiyun == 5){
            System.out.println(you_cannt_choudao);
            System.out.println("wang");
            showResult(3);
        }else if(lantian_baiyun == 6){
            System.out.println(you_cannt_choudao);
            System.out.println("diana");
            showResult(2);
        }else if(lantian_baiyun == 7){
            System.out.println(you_cannt_choudao);
            System.out.println("mord");
            showResult(1);
        }else{
            System.out.println(you_cannt_choudao);
            System.out.println("wukong");
            showResult(0);
        }
    }


    private void showResult(int i) {
        SummonView.setImageResource(tower_img[i]);
        SummonView.setVisibility(VideoView.VISIBLE);
        tower_own[i]+=1;
    }


}
