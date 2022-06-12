package com.lalaalal.paint.figure;

import java.awt.*;

public class Oval extends Figure {

    public Oval(Point start, Point end) {
        super(start, end);
    }

    public Oval(int x, int y, int width, int height) {
        super(x, y, x + width, y + height);
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
        Figure copy = new Oval(start, end);
        copy.setBackgroundColor(backgroundColor);
        copy.setLineColor(lineColor);

        return copy;
    }
}
