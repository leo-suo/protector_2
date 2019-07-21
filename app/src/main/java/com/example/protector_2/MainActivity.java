package com.example.protector_2;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {
    private Button play, bag, shop;
    public int Gold_num;
    public int Exp;
    public int Ap;
    public int [] tower_array;

    public static final String FILE = "player_info.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        /*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
                */
        Gold_num = -1;
        readfile();
        if(Gold_num == -1){
            Gold_num = 100;
        }

        tower_array = new int []{1,0,3,0,5,0,7,0,9,0};

        for(int i = 0; i < 10; i++){
            System.out.println(tower_array[i]);
        }
        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        StartGame.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                startActivityForResult(intent,0);

            }
        });


        bag = findViewById(R.id.bag);
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        bag_Activity.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                startActivityForResult(intent,0);

            }
        });

        shop = findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        shop_Activity.class);
                intent.putExtra("Gold",Gold_num);
                intent.putExtra("tower",tower_array);
                startActivityForResult(intent,0);

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String text = String.valueOf(Gold_num);
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch(FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fos != null){
                try{
                    fos.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent intent){
        super.onActivityResult(requestcode,resultcode,intent);
        Gold_num = intent.getIntExtra("Gold",-1);
        tower_array = intent.getIntArrayExtra("tower");
    }



    public void readfile(){
        FileInputStream fis = null;
        try{
            fis = openFileInput(FILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            int i = 0;
            while((text = br.readLine())!=null){
                if(i == 0){
                    Gold_num = Integer.valueOf(text);
                    i+=1;
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
