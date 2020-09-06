package com.kevin.fireStragety;

import com.kevin.Audio;
import com.kevin.Bullet;
import com.kevin.Group;
import com.kevin.Tank;
import com.kevin.fireStragety.BaseStrategy;

public enum  DefaultStragety {
    INSTANCE;

    private BaseStrategy stragety;

    DefaultStragety(){
        stragety = tank -> {
            int bX = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
            int bY = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

            new Bullet(bX, bY, tank.dir, tank.group, tank.gm);

            //audio voice for tank
            if (tank.group == Group.GOOD) {
                new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
            }
        };
    }

    public BaseStrategy getInstance() {
        return  stragety;
    }

}
