package com.kevin;

import com.kevin.abstractFactory.BaseTank;

import java.awt.*;
import java.util.Random;

/**
 * 方形坦克
 */
public class RectTank extends BaseTank {


    private int x, y;//coordinate of tank
    private Dir dir = Dir.DOWN;//diretion of tan
    private TankFrame tf; //tank canvas（war field）
    private static final int SPEED = 2; //speed of tank


    public static int WIDTH = ResourceMgr.INSTANCE.getGoodTankD().getWidth(); //width of tank
    public static int HEIGHT = ResourceMgr.INSTANCE.getGoodTankD().getHeight(); // height of tank
    public boolean moving = true;
    public boolean living = true;
    public Group group = Group.BAD;
    private Random random = new Random();


    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rec.x = x; //rec get from BaseTank
        rec.y = y; //rec get from BaseTank
        rec.width = WIDTH;
        rec.height = HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setWIDTH(int WIDTH) {
        RectTank.WIDTH = WIDTH;
    }

    public static void setHEIGHT(int HEIGHT) {
        RectTank.HEIGHT = HEIGHT;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
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

    //方形坦克
    public void paint(Graphics g) {
        //whether the tank is alive
        if (!this.living) tf.tanks.remove(this);

        Color c = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        g.fillRect(x, y, 40, 40);
        g.setColor(c);
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
        if (TankFrame.GAME_WIDTH - x - RectTank.WIDTH < 2) x = TankFrame.GAME_WIDTH - (RectTank.WIDTH + 2);
        if (TankFrame.GAME_HEIGHT - RectTank.HEIGHT - y < 2) y = TankFrame.GAME_HEIGHT - (RectTank.HEIGHT + 2);
//        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
//        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        //ctrl  generate bullet
//        this.tf.b = new Bullet(this.x, this.y, this.dir);
        int bX = this.x + RectTank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + RectTank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        //the bullet was put into clip when fire the tank, to improve
//        this.tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
//        new Bullet(bX, bY, this.dir, this.group, this.tf);
        this.tf.gf.createBullet(bX, bY, this.dir, this.group, this.tf);
        //audio voice for tank
        if (this.group == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public void die() {
        this.living = false;
    }
}
