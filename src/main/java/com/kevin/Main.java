package com.kevin;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        //initiate 5 enemies, which  will be paint at TankFram class
        for (int i = 0; i < 5; i++) {
            tf.tanks.add(new Tank(50 + i * 40, 200, Dir.DOWN, Group.BAD,tf));
        }

        while(true){
            Thread.sleep(10);
            tf.repaint();
        }
    }
}
