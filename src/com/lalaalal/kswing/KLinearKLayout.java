package com.lalaalal.kswing;

import java.util.ArrayList;

public class KLinearKLayout implements KLayoutManager {
    public enum Orientation {
        Vertical, Horizontal
    }

    public Orientation orientation;

    public KLinearKLayout(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public void adjustComponents(ArrayList<KComponent> components, KContainer container) {
        int x = container.x + container.padding.left;
        int y = container.y + container.padding.top;

        if (orientation == Orientation.Vertical)
            adjustVertical(components, x, y);
        else if (orientation == Orientation.Horizontal)
            adjustHorizontal(components, x, y);
    }

    private void adjustVertical(ArrayList<KComponent> components, int x, int y) {
        int prevMargin = 0;
        for (KComponent component : components) {
            y += Math.max(prevMargin, component.margin.top);
            component.setPoint(x, y);
            y += component.getHeight();
            prevMargin = component.margin.bottom;
        }
    }

    private void adjustHorizontal(ArrayList<KComponent> components, int x, int y) {
        int prevMargin = 0;
        for (KComponent component : components) {
            x += Math.max(prevMargin, component.margin.left);
            component.setPoint(x, y);
            x += component.getWidth();
            prevMargin = component.margin.right;
        }
    }

    @Override
    public int measureWidth(ArrayList<KComponent> components, KContainer container) {
        int padding = container.padding.left + container.padding.right;
        int width = 0;
        if (orientation == Orientation.Vertical) {
            for (KComponent component : components) {
                if (component.getWidth() > width)
                    width = component.getWidth() + component.margin.left + component.margin.right;
            }
        } else {
            int prevMargin = 0;
            for (KComponent component : components) {
                width += component.getWidth() + Math.max(prevMargin, component.margin.left);
                prevMargin = component.margin.right;
            }
            width += prevMargin;
        }
        return width + padding;
    }

    @Override
    public int measureHeight(ArrayList<KComponent> components, KContainer container) {
        int padding = container.padding.top + container.padding.bottom;
        int height = 0;
        if (orientation == Orientation.Horizontal) {
            for (KComponent component : components) {
                if (component.getHeight() > height)
                    height = component.getHeight() + component.margin.top + component.margin.bottom;
            }
        } else {
            int prevMargin = 0;
            for (KComponent component : components) {
                height += component.getHeight() + Math.max(prevMargin, component.margin.top);
                prevMargin = component.margin.bottom;
            }
            height += prevMargin;
        }
        return height + padding;
    }


}
