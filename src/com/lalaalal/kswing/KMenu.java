package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KMenu extends KAbstractButton {
    public static final BoxModel DEFAULT_MENU_PADDING = new BoxModel(5, 10);
    public static final BoxModel DEFAULT_MENU_CONTAINER_PADDING = new BoxModel(0);
    public static final int DEFAULT_CONTAINER_WIDTH = 200;

    protected final KContainer container = new KContainer(0, 0, DEFAULT_CONTAINER_WIDTH, WRAP_CONTENT);

    public KMenu(String text) {
        super(text);
        setPadding(DEFAULT_MENU_PADDING);
        setBorder(false);
        setTextAlignment(Alignment.Left);

        container.setLayout(KLinearKLayout.Vertical);
        container.setPadding(DEFAULT_MENU_CONTAINER_PADDING);
        container.setVisible(false);
        container.setBorder(true);
    }

    public boolean isOpened() {
        return container.isVisible();
    }

    public void open() {
        if (!container.isVisible()) {
            container.setVisible(true);
            repaint();
        }
    }

    public void close() {
        if (container.isVisible()) {
            container.setVisible(false);
            repaint();
        }
    }

    public boolean toggle() {
        if (container.isVisible())
            close();
        else open();

        return container.isVisible();
    }

    public void addMenuItem(KComponent component) {
        container.add(component);

        repaint();
    }

    public void addDivider() {
        addMenuItem(new KHorizontalDivider());
    }

    @Override
    public void setParent(KContainer container) {
        super.setParent(container);
        this.container.setParent(container);
    }

    @Override
    public boolean contains(int x, int y) {
        return ((this.x < x && x < this.x + getWidth())
                && (this.y < y && y <= this.y + getHeight()))
                || (container.isVisible() && container.contains(x, y));
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        super.processMouseMotionEvent(event);
        if (isOpened())
            container.processMouseMotionEvent(event);
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        if (isOpened())
            container.processMouseEvent(event);

        toggle();
    }

    @Override
    protected void measureSize(Graphics graphics) {
        super.measureSize(graphics);
        container.setPoint(x + container.margin.left, y + getHeight() + container.margin.top);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);

        container.paint(graphics);
    }
}
