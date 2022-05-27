package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KMenu extends KAbstractButton {
    private final KContainer container = new KContainer(0, 0);

    public KMenu(String text) {
        super(text);
        setPadding(5, 10, 5, 10);

        container.setLayout(new KLinearKLayout(KLinearKLayout.Orientation.Vertical));
        container.setPadding(0, 0, 0, 0);
        container.setVisible(false);
    }

    public void open() {
        container.setVisible(true);
    }

    public void close() {
        container.setVisible(false);
    }

    public void toggle() {
        container.setVisible(!container.isVisible());
    }

    public void addMenuItem(KComponent component) {
        container.add(component);
    }

    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y)
                || (container.isVisible() && container.contains(x, y));
    }

    @Override
    public void processMouseEvent(MouseEvent event) {
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
