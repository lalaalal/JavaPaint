package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KContainer extends KComponent {
    protected final ArrayList<KComponent> children = new ArrayList<>();
    private KLayoutManager layout = null;

    public KContainer(int x, int y) {
        super(x, y, WRAP_CONTENT, WRAP_CONTENT);
        setBorder(false);
    }

    public KContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
        setBorder(false);
    }

    public void add(KComponent component) {
        children.add(component);
        component.setParent(this);
        component.move(x, y);

        repaint();
    }

    public int getMatchParentWidth() {
        if (layout == null)
            return getContentWidth();
        return layout.calculateMatchParentWidth(children, this);
    }

    public int getMatchParentHeight() {
        if (layout == null)
            return getContentHeight();
        return layout.calculateMatchParentHeight(children, this);
    }

    public void setLayout(KLayoutManager layout) {
        this.layout = layout;
    }

    @Override
    public boolean contains(int x, int y) {
        for (KComponent component : children) {
            if (component.contains(x, y))
                return true;
        }

        return super.contains(x, y);
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        super.processMouseMotionEvent(event);
        for (KComponent component : children) {
            component.processMouseMotionEvent(event);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        for (KComponent component : children) {
            if (component.contains(event.getX(), event.getY()))
                component.processMouseEvent(event);
        }

    }

    @Override
    protected void measureSize(Graphics graphics) {
        super.measureSize(graphics);

        int i = 0;
        while (i < children.size()) {
            KComponent component = children.get(i);
            component.measureSize(graphics);
            i++;
        }

        if (layout == null)
            return;

        if (getWidthProperty() == WRAP_CONTENT)
            measuredWidth = layout.measureWidth(children, this);
        if (getHeightProperty() == WRAP_CONTENT)
            measuredHeight = layout.measureHeight(children, this);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        if (layout != null)
            layout.adjustComponents(children, this);

        int i = 0;
        while (i < children.size()) {
            KComponent component = children.get(i);
            component.paint(graphics);
            i++;
        }

        if (showBorder)
            graphics.drawRect(x, y, getWidth(), getHeight());
    }
}
