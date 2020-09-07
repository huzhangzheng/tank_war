package com.kevin;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }


    public void add(Collider c) {
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if(!colliders.get(i).collide(o1, o2)){ //分别和linkedList中添加的碰撞类一一对比，如果有一个碰撞类返回false,就不需要接着走下去了，返回false
                return false;
            }
        }
        return true;
    }
}
