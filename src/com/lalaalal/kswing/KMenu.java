package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KMenu extends KAbstractButton {
    private static final int DEFAULT_CONTAINER_WIDTH = 150;

    private final KContainer container = new KContainer(0, 0, DEFAULT_CONTAINER_WIDTH, WRAP_CONTENT);

    public KMenu(String text) {
        super(text);
        setPadding(5, 10, 5, 10);
        setBorder(false);

        container.setLayout(new KLinearKLayout(KLinearKLayout.Orientation.Vertical));
        container.setPadding(0, 0, 0, 0);
        container.setVisible(false);
        container.setBorder(true);
    }

    public void open() {
        container.setVisible(true);
        setBackgroundColor(Color.GRAY);
    }

    public void close() {
        container.setVisible(false);
        setBackgroundColor(Color.WHITE);
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
        addMenuItem(new KMenuItemDivider());
    }

    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y)
                || (container.isVisible() && container.contains(x, y));
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        super.processMouseMotionEvent(event);
        container.processMouseMotionEvent(event);
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        container.processMouseEvent(event);
    }

    @Override
    protected void measureContentSize(Graphics graphics) {
        super.measureContentSize(graphics);
        container.setPoint(x, y + getHeight() + container.margin.top);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);

        container.paint(graphics);
    }
}
