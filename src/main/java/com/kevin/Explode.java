package com.kevin;

import java.awt.*;

public class Explode extends GameObject {
    public static int WIDTH = ResourceMgr.INSTANCE.getExplodes()[0].getWidth();
    public static int HEIGHT = ResourceMgr.INSTANCE.getExplodes()[0].getHeight();

//    private int x, y;
    private boolean living = true;
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.INSTANCE.getExplodes()[step++],x,y,null);
        if (step >= ResourceMgr.INSTANCE.getExplodes().length) {
            GameModel.getInstance().remove(this);
        }
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
