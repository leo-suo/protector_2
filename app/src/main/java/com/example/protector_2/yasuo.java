package com.example.protector_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;



public class yasuo extends View {
    // world information
    public int worldWidth = 12;
    public int worldHeight = 6;
    public int blockSize = 100;
    public Block[][] block;

    // yasuo position
    public int yasuoX;
    public int yasuoY;

    // 4 direction
    int direction = 0;
    // right = 0; up = 1; left = 2; down = 3
    public int walk = 0;
    boolean hasleft = false;
    boolean hasright = false;
    boolean hasup = false;
    boolean hasdown = false;

    // now, which block yasuo in
    int now_block_x = 0;
    int now_block_y = 0;
    int order;

    public int ingame = 0; // to check if yasuo is in the game

    // We need an integer to keep track of the yasuo image frame
    int yasuoFrame = 0;

    Bitmap[] yasuos; // Lets create a Bitmap array for the yasuo

    public yasuo(Context context, int x, int y, Block[][] block, int order) {
        super(context);
        yasuoX = x;
        yasuoY = y;
        this.order = order;
        this.block = block;
        define();
    }

    public void define() { // yasuo move init
        yasuos = new Bitmap[6];
        yasuos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.yasuo1);
        yasuos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.yasuo2);
        yasuos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.yasuo3);
        yasuos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.yasuo4);
        yasuos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.yasuo5);
        yasuos[5] = BitmapFactory.decodeResource(getResources(), R.drawable.yasuo6);
        int start_x = 0;
        int start_y = 0;
        for (int y = 0; y < worldHeight; y++) {
            for (int x = 0; x < worldWidth; x++) {
                if (block[y][x].groundId == 2) {
                    start_x = block[y][x].x - order * blockSize / 2 - blockSize;
                    start_y = block[y][x].y;
                    now_block_y = y;
                    now_block_x = x;
                    direction = get_next_dir(y, x);
                    break;

                }
            }
        }

        yasuoX = start_x;
        yasuoY = start_y;
    }

    public void get_which_draw() {
        if (yasuoFrame == 0) {
            yasuoFrame = 1;
        } else if (yasuoFrame == 1) {
            yasuoFrame = 2;
        } else if (yasuoFrame == 2) {
            yasuoFrame = 3;
        } else if (yasuoFrame == 3) {
            yasuoFrame = 4;
        } else if (yasuoFrame == 4) {
            yasuoFrame = 5;
        } else if (yasuoFrame == 5) {
            yasuoFrame = 0;
        }
    }

    public void check_ingame(){
        if(yasuoX == block[now_block_y][now_block_x].x){
            ingame = 1;
        }
    }

    public void yasuo_move() {
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
}
