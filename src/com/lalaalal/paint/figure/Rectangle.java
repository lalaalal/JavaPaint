package com.lalaalal.paint.figure;

import java.awt.*;

public class Rectangle extends Figure {
    public Rectangle(Point start, Point end) {
        super(start, end);
    }

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, x + width, y + height);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(backgroundColor);
        graphics.fillRect(getStartX(), getStartY(), getWidth(), getHeight());

        graphics.setColor(lineColor);
        graphics.drawRect(getStartX(), getStartY(), getWidth(), getHeight());
    }

    @Override
    public Figure copy() {
        Figure copy =  new Rectangle(start, end);
        copy.setBackgroundColor(backgroundColor);
        copy.setLineColor(lineColor);

        return copy;
    }
}
