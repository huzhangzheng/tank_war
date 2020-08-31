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
    private Group group = Group.BAD;
    private Rectangle rec = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHT;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

        //reassign bullet coordinate to the retangle
        rec.x = this.x;
        rec.y = this.y;

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
        //bullet won't kill it if the tank is our own group
        if(this.group == tank.group) return;


        if (rec.intersects(tank.rec)) {
            this.die();
            tank.die();
            int ex = tank.getX()+ tank.WIDTH/2 - this.WIDTH/2;
            int ey = tank.getY()+ tank.HEIGHT/2 - this.HEIGHT/2;
            tf.explodes.add(new Explode(ex,ey,tf));
        }
    }

    public void die() {
        this.living=false;
    }

}
