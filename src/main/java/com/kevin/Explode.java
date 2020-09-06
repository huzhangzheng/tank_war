package com.kevin;

import java.awt.*;

public class Explode extends GameObject {
    public static int WIDTH = ResourceMgr.INSTANCE.getExplodes()[0].getWidth();
    public static int HEIGHT = ResourceMgr.INSTANCE.getExplodes()[0].getHeight();

    private int x, y;
    private boolean living = true;
    GameModel gm = null;
    private int step = 0;

    public Explode(int x, int y, GameModel tf) {
        this.x = x;
        this.y = y;
        this.gm = tf;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.INSTANCE.getExplodes()[step++],x,y,null);
        if (step >= ResourceMgr.INSTANCE.getExplodes().length) {
            this.gm.remove(this);
        }
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }
}
