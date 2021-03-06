package com.lalaalal.kswing;

import java.util.ArrayList;

public class KLinearKLayout implements KLayoutManager {
    public enum Orientation {
        Vertical, Horizontal
    }

    public final static KLinearKLayout Horizontal = new KLinearKLayout(Orientation.Horizontal);
    public final static KLinearKLayout Vertical = new KLinearKLayout(Orientation.Vertical);

    public Orientation orientation;

    private KLinearKLayout(Orientation orientation) {
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
            component.setPoint(x + component.margin.left, y);
            y += component.getHeight();
            prevMargin = component.margin.bottom;
        }
    }

    private void adjustHorizontal(ArrayList<KComponent> components, int x, int y) {
        int prevMargin = 0;
        for (KComponent component : components) {
            x += Math.max(prevMargin, component.margin.left);
            component.setPoint(x, y + component.margin.top);
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

    @Override
    public int calculateMatchParentWidth(ArrayList<KComponent> components, KContainer container) {
        if (orientation == Orientation.Vertical)
            return container.getContentWidth();

        int numWidthMatchParent = 0;
        int usedWidth = 0;
        int prevMargin = 0;
        for (KComponent component : components) {
            if (component.getWidthProperty() == KComponent.MATCH_PARENT)
                numWidthMatchParent += 1;
            else
                usedWidth += component.getWidth();
            usedWidth += Math.max(prevMargin, component.margin.left);
            prevMargin = component.margin.right;
        }

        return (container.getContentWidth() - usedWidth) / numWidthMatchParent;
    }

    @Override
    public int calculateMatchParentHeight(ArrayList<KComponent> components, KContainer container) {
        if (orientation == Orientation.Horizontal)
            return container.getContentHeight();

        int numHeightMatchParent = 0;
        int usedHeight = 0;
        int prevMargin = 0;
        for (KComponent component : components) {
            if (component.getHeightProperty() == KComponent.MATCH_PARENT)
                numHeightMatchParent += 1;
            else
                usedHeight += component.getHeight();
            usedHeight += Math.max(prevMargin, component.margin.top);
            prevMargin = component.margin.bottom;
        }

        return (container.getContentHeight() - usedHeight) / numHeightMatchParent;
    }


}
