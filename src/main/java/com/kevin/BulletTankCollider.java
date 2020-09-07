package com.kevin;

public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {

        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;

            if(bullet.getGroup() == tank.group) return true;


            if (bullet.getRec().intersects(tank.getRec())) {
                bullet.die();
                tank.die();
                int ex = tank.getX()+ tank.WIDTH/2 - bullet.WIDTH/2;
                int ey = tank.getY()+ tank.HEIGHT/2 - bullet.HEIGHT/2;
                GameModel.getInstance().add(new Explode(ex,ey));
            }

            bullet.colideWith(tank);
        }else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return true;
    }
}
