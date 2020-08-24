package com.kevin;

import java.awt.*;

public class Bullet {
    private final static int SPEED=1; //the speed of bullet
    private final static int WIDTH=30,HEIGHT=30; // the size of the bullet
    private int x,y; // the coordinate of the bullet
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color originColor = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(originColor);
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
    }

}
