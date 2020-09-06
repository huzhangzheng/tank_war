package com.kevin.abstractFactory;

import com.kevin.*;

public class DefaultFactory extends BaseFactory {
    @Override
    public Bullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x, y, dir, group, tf);
    }

    @Override
    public Tank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x,y,dir,group,tf);
    }

    @Override
    public Explode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }
}
