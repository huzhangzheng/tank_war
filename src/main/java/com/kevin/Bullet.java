package com.kevin;

import java.awt.*;

public class Bullet {
    private final static int SPEED=5; //the speed of bullet
    public final static int WIDTH= ResourceMgr.bulletD.getWidth(); // width of bullet
    public final static int HEIGHT= ResourceMgr.bulletD.getHeight();//height of the bullet
    private int x,y; // the coordinate of the bullet
    private Dir dir;
    private boolean living =true;
    TankFrame tf = null;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!this.living) {
            tf.bullets.remove(this);
        }
//        Color originColor = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIDTH,HEIGHT);
//        g.setColor(originColor);
        //repaint the bullet picture
        switch (this.dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        //start to move
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
        if(x<0 || y<0|| x>this.tf.GAME_WIDTH|| y>this.tf.GAME_HEIGHT) this.living =false;
    }

    public void colideWith(Tank tank) {
        Rectangle rectangle1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGHT);
        if (rectangle1.intersects(rectangle2)) {
            this.die();
            tank.die();
        }
    }

    public void die() {
        this.living=false;
    }

}
