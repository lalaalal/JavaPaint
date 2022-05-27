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

        repaint();
    }

    public void setLayout(KLayoutManager layout) {
        this.layout = layout;
    }

    @Override
    public void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        for (KComponent component : children) {
            if (component.contains(event.getX(), event.getY()))
                component.processMouseEvent(event);
        }

    }

    @Override
    protected void measureContentSize(Graphics graphics) {
        int i = 0;
        while (i < children.size()) {
            KComponent component = children.get(i);
            component.measureContentSize(graphics);
            i++;
        }

        if (layout == null)
            return;

        if (width == WRAP_CONTENT)
            measuredWidth = layout.measureWidth(children, this);
        if (height == WRAP_CONTENT)
            measuredHeight = layout.measureHeight(children, this);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        if (layout != null)
            layout.adjustComponents(children, this);

        if (showBorder)
            graphics.drawRect(x, y, getWidth(), getHeight());

        int i = 0;
        while (i < children.size()) {
            KComponent component = children.get(i);
            component.paint(graphics);
            i++;
        }
    }
}
