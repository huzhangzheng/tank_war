package com.kevin;

import java.awt.*;

public class RectDecorator  extends GODecorator{

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;  // 如果不赋值，x 默认从GODecorator 取值，而GODecorator 从GameObject取默认值 为0. 下面y 同理。
        this.y = go.y;

        //画未装饰以前的物品
        go.paint(g);

        //开始装饰
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(x,y,go.getWidth()+5,go.getHeight()+5);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
