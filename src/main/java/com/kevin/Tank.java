package com.kevin;

import java.awt.*;
import java.util.Random;

/**
 * 设置坦克方向-》然后重画坦克
 */
public class Tank {


    private int x,y;//coordinate of tank
    private Dir dir = Dir.DOWN;//diretion of tan
    private TankFrame tf; //tank canvas（war field）
    private static final int SPEED=3; //speed of tank


    public static int WIDTH = ResourceMgr.goodTankD.getWidth(); //width of tank
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight(); // height of tank
    public boolean moving = true;
    public boolean living = true;
    public Group group = Group.BAD;
    private Random random = new Random();



    public Tank(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
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
        Tank.WIDTH = WIDTH;
    }

    public static void setHEIGHT(int HEIGHT) {
        Tank.HEIGHT = HEIGHT;
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
    public  boolean isMoving() {
        return moving;
    }

    //set moving status
    public  void setMoving(Boolean moving) {
        this.moving = moving;
    }

    //重画坦克
    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, 50, 50);
//        g.setColor(c);

        //whether the tank is alive
        if (!this.living)  tf.tanks.remove(this);

        // repaint the tank
        switch (this.dir) {
            case LEFT:
                g.drawImage(this.group== Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL,x, y, null);
                break;
            case UP:
                g.drawImage(this.group== Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU,x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group== Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR,x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group== Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD,x, y, null);

                break;
        }
        //start to move
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

        //bad tank randomly fire
        if(random.nextInt(100)>95 && this.group==Group.BAD) this.fire();

        //bad tank random diretion after one move
        if(random.nextInt(100)>95 && this.group==Group.BAD) this.randomDir();

        //bounds check
        boundsCheck();

    }

    private void boundsCheck() {
        if(x<2) x=2;
        if(y<28) y=28;
        if(TankFrame.GAME_WIDTH - x - Tank.WIDTH <2) x= TankFrame.GAME_WIDTH -(Tank.WIDTH+2);
        if(TankFrame.GAME_HEIGHT - Tank.HEIGHT -y <2) y= TankFrame.GAME_HEIGHT -(Tank.HEIGHT+2);
//        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
//        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        //按下ctrl 键以后生成一个子弹，位置和坦克的位置一模一样
//        this.tf.b = new Bullet(this.x, this.y, this.dir);
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        this.tf.bullets.add(new Bullet(bX, bY, this.dir,this.group,this.tf));

        //audio voice for tank
        if (this.group == Group.GOOD) {
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public void die() {
        this.living= false;
    }
}
