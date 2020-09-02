package com.kevin.abstractFactory;

import com.kevin.Dir;
import com.kevin.Group;

import java.awt.*;

public abstract class BaseTank {
    int x, y;
    Dir dir;
    Group group;
    public Rectangle rec = new Rectangle();


    public abstract Group getGroup();
    public abstract void paint(Graphics g);

    public abstract int getX();

    public abstract int getY();

    public abstract void die();
}
