package com.lalaalal.paint.figure;

import java.awt.*;

public class Line extends Figure {
    public Line(Point start, Point end) {
        super(start, end);
    }

    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(lineColor);
        graphics.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
    }

    @Override
    public Figure copy() {
        return new Line(start, end);
    }
}
