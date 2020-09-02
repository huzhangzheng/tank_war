package com.kevin;

import com.kevin.abstractFactory.BaseBullet;
import com.kevin.abstractFactory.BaseTank;

import java.awt.*;

public class RectBullet extends BaseBullet {
    private final static int SPEED=5; //the speed of bullet
    public final static int WIDTH= ResourceMgr.INSTANCE.getBulletU().getWidth(); // width of bullet
    public final static int HEIGHT= ResourceMgr.INSTANCE.getBulletU().getHeight();//height of the bullet
    private int x,y; // the coordinate of the bullet
    private Dir dir;
    private boolean living =true;
    TankFrame tf = null;
    private Group group = Group.BAD;
    private Rectangle rec = new Rectangle();

    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHT;

        //after bullet was created , add this bullet into  the  clip
        tf.bullets.add(this);
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public void paint(Graphics g) {
        if(!living) {
            tf.bullets.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
        g.setColor(c);

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

    public void collideWith(BaseTank tank) {
        //bullet won't kill it if the tank is our own group
        if(this.group == tank.getGroup()) return;


        if (rec.intersects(tank.rec)) {
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - RectExplode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - RectExplode.HEIGHT/2;
//            tf.explodes.add(new RectExplode(eX,eY,tf));
            tf.explodes.add(tf.gf.createExplode(eX,eY,tf));
        }
    }

    public void die() {
        this.living=false;
    }

}
