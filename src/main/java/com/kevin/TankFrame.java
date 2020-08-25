package com.kevin;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TankFrame extends Frame {
    Tank tank = new Tank(200, 200, Dir.DOWN, this);
    Bullet b = new Bullet(300,300,Dir.DOWN,this);
    ArrayList<Bullet> bullets = new ArrayList<>();

    Image offScreenImage = null;
    static int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    /**
     * 这个就是坦克战场的描述，包括大小，名字，各种事件监听
     * constructor
     *
     * @throws HeadlessException
     */
    public TankFrame() {
        setTitle("tank war");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            //click the close button .the tank frame will close
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets count:" + bullets.size(), 10, 60);
        g.setColor(c);

        tank.paint(g);
        //这种iterator遍历方式会在Bullet类中删除元素的时候，产生 java.util.ConcurrentModificationException
//        for (Bullet b: bullets) {b.paint(g);}
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bU = false;
        boolean bD = false;
        boolean bL = false;
        boolean bR = false;


        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed");
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }

            System.out.println(String.format("key pressed %s %s %s %s:", bU, bD, bL, bR));

            //按键结束以后设置方向
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);

                if (bL) tank.setDir(Dir.LEFT);
                if (bR) tank.setDir(Dir.RIGHT);
                if (bU) tank.setDir(Dir.UP);
                if (bD) tank.setDir(Dir.DOWN);
            }


        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(String.format("key released %s %s %s %s:", bU, bD, bL, bR));
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            //按键松开后重置方向
            setMainTankDir();

        }
    }
}
