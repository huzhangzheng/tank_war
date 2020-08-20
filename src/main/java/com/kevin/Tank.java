package com.kevin;

import java.awt.*;

/**
 * 设置坦克方向-》然后重画坦克
 */
public class Tank {

    private int x,y;//坦克大小
    private Dir dir = Dir.DOWN;//坦克方向
    private static final int SPEED=10; //坦克的速度


    //坦克方向

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //get direction
    public Dir getDir() {
        return dir;
    }
    //set direction
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    //重画坦克
    public void paint(Graphics graph) {
        graph.fillRect(x,y,50,50);
        move();


    }

    private void move() {
        //change speed based on Direction
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

}
