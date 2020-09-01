package com.kevin;

import com.kevin.fireStragety.BaseStrategy;
import com.kevin.fireStragety.DefaultStragety;
import com.kevin.fireStragety.FourDirStragety;

import java.awt.*;
import java.util.Random;

/**
 * 设置坦克方向-》然后重画坦克
 */
public class Tank {
    public int x, y;//coordinate of tank
    public Dir dir = Dir.DOWN;//diretion of tan
    public TankFrame tf; //tank canvas（war field）
    private static final int SPEED = 2; //speed of tank


    public static int WIDTH = ResourceMgr.INSTANCE.getGoodTankD().getWidth(); //width of tank
    public static int HEIGHT = ResourceMgr.INSTANCE.getGoodTankD().getHeight(); // height of tank
    public boolean moving = true;
    public boolean living = true;
    public Group group = Group.BAD;
    private Random random = new Random();
    public Rectangle rec = new Rectangle();
    private BaseStrategy fireStrategy;


    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rec.x = x;
        rec.y = y;
        rec.width = WIDTH;
        rec.height = HEIGHT;

        fireStrategy = this.group == Group.BAD?
                        DefaultStragety.INSTANCE.getInstance():
                        FourDirStragety.INSTANCE.getInstance();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
    public boolean isMoving() {
        return moving;
    }

    //set moving status
    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    //重画坦克
    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, 50, 50);
//        g.setColor(c);

        //whether the tank is alive
        if (!this.living) tf.tanks.remove(this);

        // repaint the tank
        switch (this.dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodTankL() : ResourceMgr.INSTANCE.getBadTankL(), x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodTankU() : ResourceMgr.INSTANCE.getBadTankU(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodTankR() : ResourceMgr.INSTANCE.getBadTankR(), x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.INSTANCE.getGoodTankD() : ResourceMgr.INSTANCE.getBadTankD(), x, y, null);

                break;
        }
        //start to move
        move();
    }

    private void move() {
        if (!this.moving) return;
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

        //bad tank randomly fire
        if (random.nextInt(100) > 95 && this.group == Group.BAD) this.fire();

        //bad tank random diretion after one move
        if (random.nextInt(100) > 95 && this.group == Group.BAD) this.randomDir();

        //bounds check
        boundsCheck();

        //reassign the value to Rectangle
        rec.x = this.x;
        rec.y = this.y;
    }

    private void boundsCheck() {
        if (x < 2) x = 2;
        if (y < 28) y = 28;
        if (TankFrame.GAME_WIDTH - x - Tank.WIDTH < 2) x = TankFrame.GAME_WIDTH - (Tank.WIDTH + 2);
        if (TankFrame.GAME_HEIGHT - Tank.HEIGHT - y < 2) y = TankFrame.GAME_HEIGHT - (Tank.HEIGHT + 2);
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fireStrategy.fire(this);
    }

    public void die() {
        this.living = false;
    }
}
