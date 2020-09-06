package com.kevin;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TankFrame extends Frame {
    private GameModel gm = new GameModel();



    Image offScreenImage = null;
    static int GAME_WIDTH = 1000, GAME_HEIGHT = 700;

    /**
     * description of tank war field，包括大小，名字，各种事件监听
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
        gm.paint(g);
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
                gm.getMainTank().setMoving(false);
            } else {
                gm.getMainTank().setMoving(true);

                if (bL) gm.getMainTank().setDir(Dir.LEFT);
                if (bR) gm.getMainTank().setDir(Dir.RIGHT);
                if (bU) gm.getMainTank().setDir(Dir.UP);
                if (bD) gm.getMainTank().setDir(Dir.DOWN);
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
                    gm.getMainTank().fire();
                    break;
                default:
                    break;
            }
            //按键松开后重置方向
            setMainTankDir();

        }
    }
}
