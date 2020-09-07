package com.kevin;

public class TankWallCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;

            if (tank.getRec().intersects(wall.rec)) {
                // 这里没有直接return false,而是stop(); 最后还是会return true;
                tank.back(); //相撞以后，回到上一个坐标点
            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
            return collide(o2, o1);
        }

        return true;
    }
}
