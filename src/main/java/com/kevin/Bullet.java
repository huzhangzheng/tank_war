package com.kevin;

import java.awt.*;

public class Bullet {
    private final static int SPEED=1; //the speed of bullet
    private final static int WIDTH=30,HEIGHT=30; // the size of the bullet
    private int x,y; // the coordinate of the bullet
    private Dir dir;
    private boolean live=true;
    TankFrame tf = null;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!this.live) {
            tf.bullets.remove(this);
        }
        Color originColor = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(originColor);
        move();
    }

    private void move() {
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

        //evaluate whether the bullets is alive or not.
        if(x<0 || y<0|| x>this.tf.GAME_WIDTH|| y>this.tf.GAME_HEIGHT) this.live=false;
    }

}
