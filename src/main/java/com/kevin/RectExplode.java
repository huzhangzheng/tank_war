package com.kevin;

import com.kevin.abstractFactory.BaseExplode;

import java.awt.*;

public class RectExplode extends BaseExplode {
    public static int WIDTH = ResourceMgr.INSTANCE.getExplodes()[0].getWidth();
    public static int HEIGHT = ResourceMgr.INSTANCE.getExplodes()[0].getHeight();

    private int x, y;
    private boolean living = true;
    TankFrame tf = null;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10*step, 10*step);
        step++;

        if(step >= 15)
            tf.explodes.remove(this);

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }
}
