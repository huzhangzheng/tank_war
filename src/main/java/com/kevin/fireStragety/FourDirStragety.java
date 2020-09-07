package com.kevin.fireStragety;

import com.kevin.*;

public enum  FourDirStragety  {
    INSTANCE;

    private BaseStrategy stragety;
    FourDirStragety() {
        stragety = tank -> {
            int bX = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
            int bY = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

            for ( Dir dir : Dir.values()){
                new Bullet(bX, bY, dir, tank.group);
            }
            //audio voice for tank
            if (tank.group == Group.GOOD) {
                new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
            }
        };
    }

    public BaseStrategy getInstance() {
        return stragety;
    }

}
