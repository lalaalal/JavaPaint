package com.lalaalal.paint.figure;

import java.awt.*;

public class Oval extends Figure {

    public Oval(Point start, Point end) {
        super(minPoint(start, end), maxPoint(start, end));
    }

    public Oval(int x, int y, int width, int height) {
        super(minPoint(x, y, x + width, y + height), maxPoint(x, y, x + width, y + height));
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(backgroundColor);
        graphics.fillOval(getStartX(), getStartY(), getWidth(), getHeight());

        graphics.setColor(lineColor);
        graphics.drawOval(getStartX(), getStartY(), getWidth(), getHeight());
    }

    @Override
    public Figure copy() {
        return new Oval(start, end);
    }
}
