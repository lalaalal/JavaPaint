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
        contentPane.setParent(this);
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
    public int getContentHeight() {
        if (menuBar != null)
            return super.getContentHeight() - menuBar.getHeight() - adapterFrame.getInsets().top;
        return super.getContentHeight() - adapterFrame.getInsets().top;
    }

    @Override
    public void repaint() {
        adapterFrame.repaint();
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        if (menuBar == null || !menuBar.contains(event.getX(), event.getY())) {
            contentPane.processMouseMotionEvent(event);
            super.processMouseMotionEvent(event);
        }

        if (menuBar != null)
            menuBar.processMouseMotionEvent(event);

    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        if (menuBar == null || !menuBar.contains(event.getX(), event.getY())) {
            contentPane.processMouseEvent(event);
            super.processMouseEvent(event);
        }

        if (menuBar != null)
            menuBar.processMouseEvent(event);
    }

    @Override
    protected void measureSize(Graphics graphics) {
        setWidth(adapterFrame.getWidth());
        setHeight(adapterFrame.getHeight());

        super.measureSize(graphics);
        if (menuBar != null)
            menuBar.measureSize(graphics);
        contentPane.measureSize(graphics);

        int menuBarHeight = 0;
        if (menuBar != null) {
            menuBar.setPoint(x, adapterFrame.getInsets().top);
            menuBarHeight = menuBar.getHeight();
        }

        contentPane.setPoint(x, menuBarHeight + adapterFrame.getInsets().top);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
        contentPane.paint(graphics);
        if (menuBar != null)
            menuBar.paint(graphics);
    }
}
