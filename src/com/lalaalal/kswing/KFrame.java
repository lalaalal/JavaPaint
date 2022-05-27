package com.lalaalal.kswing;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KFrame extends KContainer {
    private KMenuBar menuBar;
    private KContainer contentPane;

    private final KAdapterFrame adapterFrame;

    public KFrame(@NotNull KAdapterFrame adapterFrame) {
        super(0, 0);
        this.adapterFrame = adapterFrame;
        setContentPane(new KPanel());
        setPadding(0, 0, 0, 0);
    }

    public KContainer getContentPane() {
        return contentPane;
    }

    public void setContentPane(KContainer container) {
        this.contentPane = container;
        contentPane.setParent(this);
        repaint();
    }

    public void setMenuBar(KMenuBar menuBar) {
        this.menuBar = menuBar;
        this.menuBar.setParent(this);
        repaint();
    }

    @Override
    public void repaint() {
        adapterFrame.repaint();
    }

    @Override
    public void processMouseEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            if (menuBar != null && menuBar.contains(event.getX(), event.getY()))
                menuBar.processMouseEvent(event);
        }

        super.processMouseEvent(event);
    }

    @Override
    protected void measureContentSize(Graphics graphics) {
        super.measureContentSize(graphics);
        if (menuBar != null)
            menuBar.measureContentSize(graphics);
        contentPane.measureContentSize(graphics);

        int menuBarHeight = 0;
        if (menuBar != null) {
            menuBar.setPoint(x, adapterFrame.getInsets().top);
            menuBarHeight = menuBar.getHeight();
        }

        contentPane.setPoint(x, menuBarHeight + adapterFrame.getInsets().top);
        setWidth(adapterFrame.getWidth());
        setHeight(adapterFrame.getHeight());
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
        if (menuBar != null)
            menuBar.paint(graphics);
        contentPane.paint(graphics);
    }
}
