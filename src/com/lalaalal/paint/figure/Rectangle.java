package com.lalaalal.paint.figure;

import java.awt.*;

public class Rectangle extends Figure {
    public Rectangle(Point start, Point end) {
        super(minPoint(start, end), maxPoint(start, end));
    }

    public Rectangle(int x, int y, int width, int height) {
        super(minPoint(x, y, x + width, y + height), maxPoint(x, y, x + width, y + height));
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
        return new Rectangle(start, end);
    }
}
