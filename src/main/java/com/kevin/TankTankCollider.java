package com.kevin;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;

            if (t1.getRec().intersects(t2.getRec())) {
                t1.stop(); //这里没有直接return false,而是stop(); 最后还是会return true;
            }
        }

        return true;
    }
}
