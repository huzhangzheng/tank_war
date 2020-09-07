package com.kevin.fireStragety;

import com.kevin.*;
import com.kevin.fireStragety.BaseStrategy;

public enum  DefaultStragety {
    INSTANCE;

    private BaseStrategy stragety;

    DefaultStragety(){
        stragety = tank -> {
            int bX = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
            int bY = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

//            new Bullet(bX, bY, tank.dir, tank.group, tank.gm);
            GameModel.getInstance().add(
                    new TailDecorator(
                        new RectDecorator(new Bullet(bX, bY, tank.dir, tank.group)))
            );

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
