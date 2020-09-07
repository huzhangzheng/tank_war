package com.kevin;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameModel {
    Tank tank = new Tank(200, 300, Dir.DOWN, Group.GOOD,this);
//    ArrayList<Bullet> bullets = new ArrayList<>(); //bullets box
//    ArrayList<Tank> tanks =  new ArrayList<>(); //enemies
//    ArrayList<Explode> explodes =  new ArrayList<>(); //explodes
    ArrayList<GameObject>  objects = new ArrayList<>();
    ColliderChain chain = new ColliderChain(); //责任链

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }

    }

    public void add(GameObject go){
        objects.add(go);
    }

    public void remove(GameObject go){
        objects.remove(go);
    }

    public void paint(Graphics g) {
        //paint my good tank
        tank.paint(g);

       //paint other GameObjects
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // Collide
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j <objects.size() ; j++) {
                chain.collide(objects.get(i), objects.get(j));
            }
        }
    }

    public Tank getMainTank() {
        return tank;
    }
}
