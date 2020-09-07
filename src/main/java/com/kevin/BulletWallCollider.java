package com.kevin;

public class BulletWallCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;

            if (bullet.getRec().intersects(wall.rec)) {
                // 这里没有直接return false,而是stop(); 最后还是会return true;
                bullet.die(); //相撞以后，子弹死，墙还在
            }
        }else if(o1 instanceof Wall && o2 instanceof Bullet){
            return collide(o2, o1);
        }

        return true;
    }
}
