package com.kevin;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        TankFrame tf = new TankFrame();
        //initiate 5 enemies, which  will be paint at TankFram class
        for (int i = 0; i < initTankCount; i++) {
//            tf.tanks.add(new Tank(50 + i * 40, 200, Dir.DOWN, Group.BAD,tf));
            tf.tanks.add(tf.gf.createTank(50 + i * 40, 200, Dir.DOWN, Group.BAD,tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while(true){
            Thread.sleep(10);
            tf.repaint();
        }
    }
}
