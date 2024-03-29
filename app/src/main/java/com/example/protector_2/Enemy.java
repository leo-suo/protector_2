package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

abstract public class Enemy extends View implements Enemy_Interface{
    public int worldWidth = 12;
    public int worldHeight = 6;
    public int blockSize = 100;
    public Block[][] block;

    public int max_frame = 0;

    // yasuo position
    public int yasuoX;
    public int yasuoY;
    public int game_finish = 0;

    // 4 direction
    int direction = 0;
    // right = 0; up = 1; left = 2; down = 3
    public int walk = 0;
    boolean hasleft = false;
    boolean hasright = false;
    boolean hasup = false;
    boolean hasdown = false;

    // now, which block yasuo in
    int now_block_x = 0;// This two is the beginning block
    int now_block_y = 0;
    int order;

    int in_which_x = 0;
    int in_which_y = 0; // This two is where is the enemy now!!!!
    // Updating every step

    public int ingame = 0; // to check if yasuo is in the game

    // We need an integer to keep track of the yasuo image frame
    int yasuoFrame = 0;


    Bitmap[] yasuos; // Lets create a Bitmap array for the yasuo

    // yasuo game information
    int hp = 500;
    int total_hp = 500;
    int has_update_dead_info = 0;
    public int attack_damage = 1;

    public void attack_base(){
        if(in_the_end() && !is_dead()){
            GameView.be_attack(attack_damage);
        }
    }

    public void set_hp(int x){
        this.hp = x;
    }

    public void set_damage(int x){
        attack_damage = x;
    }

    @Override
    public int return_attack_damage(){
        return attack_damage;
    }

    @Override
    public int return_hp(){
        return hp;
    }

    public void update_position(){
        for (int j = 0; j < worldHeight; j++) {
            for (int i = 0; i < worldWidth; i++) {
                if(block[j][i].in_the_block(yasuoX, yasuoY)){
                    in_which_x = i;
                    in_which_y = j;
                }
            }
        }
    }

    public boolean in_the_end(){
        if(block[in_which_y][in_which_x].end == 1){
            return true;
        }
        return false;
    }

    public void attack_tower(){
        if(block[in_which_y][in_which_x].have_tower == 1){
            System.out.println("I AM HRERERERERE");
            block[in_which_y][in_which_x].block_tower.be_attack(attack_damage);
            System.out.println(block[in_which_y][in_which_x].block_tower.hp);
            if(block[in_which_y][in_which_x].block_tower.hp <= 0){
                block[in_which_y][in_which_x].have_tower = 0;
            }
        }
    }

    public Enemy(Context context, int x, int y, Block[][] block, int order){
        super(context);

        yasuoX = x;
        yasuoY = y;
        this.order = order;
        this.block = block;

        int start_x = 0;
        int start_y = 0;
        for (int j = 0; j < worldHeight; j++) {
            for (int i = 0; i < worldWidth; i++) {
                if (GameView.block[j][i].groundId == 2) {
                    start_x = GameView.block[j][i].x - order * blockSize - blockSize;
                    start_y = GameView.block[j][i].y;
                    now_block_y = j;
                    now_block_x = i;
                    direction = get_next_dir(j, i);
                    break;

                }
            }
        }

        yasuoX = start_x;
        yasuoY = start_y;
    }


    public void be_attacked(int x){
        hp = hp - x;


    }

    public int center_x(){
        return yasuoX + blockSize / 2;
    }

    public int center_y(){
        return  yasuoY + blockSize / 2;
    }

    public void yasuo_move() {
        if(!in_the_end() && block[in_which_y][in_which_x].have_tower == 0){
            if(ingame == 0){
                check_ingame();
                yasuoX += 5;
            }else{
                if (direction == 0)
                    yasuoX += 5;
                else if (direction == 1)
                    yasuoY -= 5;
                else if (direction == 2)
                    yasuoX -= 5;
                else
                    yasuoY += 5;

                walk += 5;
                if (walk >= blockSize) {
                    if (direction == 0) {
                        now_block_x += 1;
                        hasright = true;

                    } else if (direction == 1) {
                        now_block_y -= 1;
                        hasup = true;
                    } else if (direction == 2) {
                        now_block_x -= 1;
                        hasleft = true;
                    } else {
                        now_block_y += 1;
                        hasdown = true;
                    }

                    if (!hasleft) {
                        try {
                            if (block[now_block_y][now_block_x + 1].groundId == 1) {
                                direction = 0;
                            }
                        } catch (Exception e) {
                        }
                    }

                    if (!hasright) {
                        try {
                            if (block[now_block_y][now_block_x - 1].groundId == 1) {
                                direction = 2;
                            }
                        } catch (Exception e) {
                        }
                    }

                    if (!hasup) {
                        try {
                            if (block[now_block_y + 1][now_block_x].groundId == 1) {
                                direction = 3;
                            }
                        } catch (Exception e) {
                        }
                    }

                    if (!hasdown) {
                        try {
                            if (block[now_block_y - 1][now_block_x].groundId == 1) {
                                direction = 1;
                            }
                        } catch (Exception e) {
                        }
                    }

                    hasleft = false;
                    hasup = false;
                    hasdown = false;
                    hasright = false;
                    walk = 0;
                }

            }
        }
    }

    public void check_ingame(){
        if(yasuoX == block[now_block_y][now_block_x].x){
            ingame = 1;
        }
    }

    public int get_next_dir(int a, int b) {
        try {
            if (block[a][b + 1].groundId == 1) {
                return 0;
            }
        } catch (Exception e) {
        }

        try {
            if (block[a][b - 1].groundId == 1) {
                return 2;
            }
        } catch (Exception e) {
        }

        try {
            if (block[a - 1][b].groundId == 1) {
                return 1;
            }
        } catch (Exception e) {
        }

        try {
            if (block[a + 1][b].groundId == 1) {
                return 3;
            }
        } catch (Exception e) {
        }

        return 5;
    }




    public void notifyOberver(){
        for(int i = 0; i < GameView.number_of_tower; i++){
            //if(!is_dead()){ // if not dead, notify
            GameView.tower[i].update(center_x(), center_y(), this);
            //}
        }
    }

    public boolean is_dead(){
        if(hp > 0){
            return false;
        }else{
            if(has_update_dead_info == 0){
                has_update_dead_info = 1;
                GameView.num_of_dead_yasuo++;
            }
            return true;
        }
    }


    public void get_which_draw() {
        if (yasuoFrame < max_frame){
            yasuoFrame = yasuoFrame + 1;
        }else{
            yasuoFrame = 0;
        }
    }
}

