package com.lalaalal.paint.figure;

import java.awt.*;

public class Line extends Figure {
    private final int direction;
    public static final int BOTTOM_RIGHT = 1;
    public static final int TOP_RIGHT = -1;

    public Line(Point start, Point end) {
        super(start, end);

        if ((end.y - start.y) * (end.x - start.x) > 0)
            direction = BOTTOM_RIGHT;
        else
            direction = TOP_RIGHT;
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(lineColor);
        if (direction == BOTTOM_RIGHT)
            graphics.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
        else
            graphics.drawLine(getStartX(), getEndY(), getEndX(), getStartY());
    }

    @Override
    public Figure copy() {
        Figure copy = null;
        if (direction == TOP_RIGHT)
            copy = new Line(new Point(start.x, end.y), new Point(end.x, start.y));
        else copy = new Line(start, end);
        copy.setLineColor(lineColor);

        return copy;
    }
}
