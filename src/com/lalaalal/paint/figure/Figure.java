package com.lalaalal.paint.figure;

import java.awt.*;

public abstract class Figure {
    public enum Type {
        Rectangle, Oval, Line
    }

    protected Point start;
    protected Point end;

    protected Color backgroundColor = Color.WHITE;
    protected Color lineColor = Color.BLACK;

    public static Figure createFigure(Type type, Point start, Point end) {
        switch (type) {
            case Rectangle:
                return new Rectangle(start, end);
            case Oval:
                return new Oval(start, end);
            case Line:
                return new Line(start, end);
            default:
                return null;
        }
    }

    public Figure(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);

        normalize();
    }

    public Figure(int x1, int y1, int x2, int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

        normalize();
    }

    protected static Point minPoint(Point a, Point b) {
        return minPoint(a.x, a.y, b.x, b.y);
    }

    protected static Point maxPoint(Point a, Point b) {
        return maxPoint(a.x, a.y, b.x, b.y);
    }

    protected static Point minPoint(int x1, int y1, int x2, int y2) {
        return new Point(Math.min(x1, x2), Math.min(y1, y2));
    }

    protected static Point maxPoint(int x1, int y1, int x2, int y2) {
        return new Point(Math.max(x1, x2), Math.max(y1, y2));
    }

    protected void normalize() {
        Point start = this.start;
        Point end = this.end;

        this.start = minPoint(start, end);
        this.end = maxPoint(start, end);
    }

    public int getStartX() {
        return start.x;
    }

    public int getStartY() {
        return start.y;
    }

    public int getEndX() {
        return end.x;
    }

    public int getEndY() {
        return end.y;
    }

    public int getWidth() {
        return end.x - start.x;
    }

    public int getHeight() {
        return end.y - start.y;
    }

    public void setPoint(int x, int y) {
        move(x - getStartX(), y - getStartY());
    }

    public void move(int dx, int dy) {
        start.x += dx;
        start.y += dy;

        end.x += dx;
        end.y += dy;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public boolean contains(Point point) {
        return contains(point.x, point.y);
    }

    public boolean contains(int x, int y) {
        return (start.x <= x && x <= end.x)
                && (start.y <= y && y <= end.y);
    }

    public boolean isContained(Point a, Point b) {
        Point start = minPoint(a, b);
        Point end = maxPoint(a, b);

        return (start.x <= getStartX() && getEndX() <= end.x)
                && (start.y <= getStartY() && getEndY() <= end.y);
    }

    public abstract void paint(Graphics graphics);

    public abstract Figure copy();
}
