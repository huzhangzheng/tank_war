package com.kevin;

import java.awt.*;

/**
 * 设置坦克方向-》然后重画坦克
 */
public class Tank {

    private int x,y;//坦克大小
    private Dir dir = Dir.DOWN;//坦克方向
    private TankFrame tf; //tank画布（战场）的引用
    private static final int SPEED=1; //坦克的速度


    private boolean moving = false;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }


    //get direction
    public Dir getDir() {
        return dir;
    }
    //set direction
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    //moving status
    public  boolean isMoving() {
        return moving;
    }

    //set moving status
    public  void setMoving(Boolean moving) {
        this.moving = moving;
    }

    //重画坦克
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(c);
        move();
    }

    private void move() {
        if(!this.moving) return;
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

    public void fire() {
        //按下ctrl 键以后生成一个子弹，位置和坦克的位置一模一样
//        this.tf.b = new Bullet(this.x, this.y, this.dir);
        this.tf.bullets.add(new Bullet(this.x, this.y, this.dir,this.tf));
    }
}
