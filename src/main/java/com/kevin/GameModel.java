package com.kevin;

import java.awt.*;
import java.util.ArrayList;

public class GameModel {
    Tank tank = new Tank(200, 300, Dir.DOWN, Group.GOOD,this);
    ArrayList<Bullet> bullets = new ArrayList<>(); //bullets box
    ArrayList<Tank> tanks =  new ArrayList<>(); //enemies
    ArrayList<Explode> explodes =  new ArrayList<>(); //explodes

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }

    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets count:" + bullets.size(), 10, 60);
        g.drawString("enemies count:" + tanks.size(), 10, 80);
        g.drawString("explosion count:" + tanks.size(), 10, 100);
        g.setColor(c);

        //paint my good tank
        tank.paint(g);

        //paint enemies tanks
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        //check whether the bullet collide with enemies tank and make it dead
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).colideWith(tanks.get(j));
            }
        }

        //这种iterator遍历方式会在Bullet类中删除元素的时候，产生 java.util.ConcurrentModificationException
//        for (Bullet b: bullets) {b.paint(g);}
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //paint a explosion for fun
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }

    public Tank getMainTank() {
        return tank;
    }
}
