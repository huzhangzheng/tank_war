package com.kevin;

import com.kevin.abstractFactory.BaseExplode;

import java.awt.*;

public class Explode extends BaseExplode {
    public static int WIDTH = ResourceMgr.INSTANCE.getExplodes()[0].getWidth();
    public static int HEIGHT = ResourceMgr.INSTANCE.getExplodes()[0].getHeight();

    private int x, y;

    //private boolean living = true;

    private int step = 0;
    private TankFrame tf;

    public Explode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }



    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.INSTANCE.getExplodes()[step++], x, y, null);

        if(step >= ResourceMgr.INSTANCE.getExplodes().length)
            tf.explodes.remove(this);


    }



}
