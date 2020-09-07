package com.kevin;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;

            if (t1.getRec().intersects(t2.getRec())) {
// 这里没有直接return false,而是stop(); 最后还是会return true;
                t1.back(); //相撞以后，回到上一个坐标点
                t2.back(); //相撞以后，回到上一个坐标点
//                t1.stop();
            }
        }

        return true;
    }
}
