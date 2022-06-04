package com.lalaalal.paint.figure;

import java.awt.*;
import java.util.ArrayList;

public class Group extends Figure {
    private final ArrayList<Figure> children = new ArrayList<>();

    private boolean showBorder = false;
    
    public Group(ArrayList<Figure> figures) {
        super(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0);

        for (Figure figure : figures) {
            start.x = Math.min(figure.getStartX(), start.x);
            start.y = Math.min(figure.getStartY(), start.y);
            end.x = Math.max(figure.getEndX(), end.x);
            end.y = Math.max(figure.getEndY(), end.y);
            children.add(figure);
        }
    }

    @Override
    protected void normalize() {

    }

    public ArrayList<Figure> getChildren() {
        return children;
    }

    public void setBorder(boolean value) {
        showBorder = value;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        for (Figure figure : children) {
            figure.move(dx, dy);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        for (Figure figure : children) {
            figure.paint(graphics);
        }

        if (showBorder) {
            graphics.setColor(lineColor);
            graphics.drawRect(getStartX(), getStartY(), getWidth(), getHeight());
        }

    }

    @Override
    public Figure copy() {
        ArrayList<Figure> copies = new ArrayList<>();
        for (Figure figure : children) {
            copies.add(figure.copy());
        }
        return new Group(copies);
    }
}
