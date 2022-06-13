package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KFrame extends KContainer {
    private KMenuBar menuBar;
    private KToolBar toolBar;
    private KContainer contentPane;

    private final KAdapterFrame adapterFrame;

    public KFrame(KAdapterFrame adapterFrame) {
        super(0, 0);
        this.adapterFrame = adapterFrame;
        setContentPane(new KPanel());
        contentPane.setBorder(true);
        setPadding(0, 0, 0, 0);
    }

    public void setSize(int width, int height) {
        adapterFrame.setSize(width, height);
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

    public void setToolBar(KToolBar toolBar) {
        this.toolBar = toolBar;
        this.toolBar.setParent(this);
        repaint();
    }

    @Override
    public int getContentWidth() {
        return super.getContentWidth() - adapterFrame.getInsets().left;
    }

    @Override
    public int getContentHeight() {
        if (menuBar != null)
            return super.getContentHeight() - menuBar.getHeight() - adapterFrame.getInsets().top;
        return super.getContentHeight() - adapterFrame.getInsets().top;
    }

    @Override
    public void repaint() {
        paint(adapterFrame.getGraphics());
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        if (menuBar == null || !menuBar.isMenuOpened()) {
            contentPane.processMouseMotionEvent(event);
            super.processMouseMotionEvent(event);
        }

        if (menuBar != null)
            menuBar.processMouseMotionEvent(event);
        if (toolBar != null)
            toolBar.processMouseMotionEvent(event);
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        if (menuBar == null || !menuBar.isMenuOpened()) {
            contentPane.processMouseEvent(event);
            super.processMouseEvent(event);
        }

        if (menuBar != null)
            menuBar.processMouseEvent(event);
        if (toolBar != null)
            toolBar.processMouseEvent(event);
    }

    @Override
    protected void measureSize(Graphics graphics) {
        setWidth(adapterFrame.getWidth());
        setHeight(adapterFrame.getHeight());

        super.measureSize(graphics);
        if (menuBar != null)
            menuBar.measureSize(graphics);
        if (toolBar != null)
            toolBar.measureSize(graphics);
        contentPane.measureSize(graphics);

        int menuBarHeight = 0;
        if (menuBar != null) {
            menuBar.setPoint(adapterFrame.getInsets().left, adapterFrame.getInsets().top);
            menuBarHeight = menuBar.getHeight();
        }
        int toolBarHeight = 0;
        if (toolBar != null) {
            toolBar.setPoint(adapterFrame.getInsets().left, adapterFrame.getInsets().top + menuBarHeight);
            toolBarHeight = toolBar.getHeight();
        }

        contentPane.setPoint(adapterFrame.getInsets().left, menuBarHeight + toolBarHeight + adapterFrame.getInsets().top);
    }

    @Override
    public void paint(Graphics graphics) {
        if (isVisible()) {
            measureSize(graphics);
            paintContent(graphics);
        }
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
        contentPane.paint(graphics);
        if (toolBar != null)
            toolBar.paint(graphics);
        if (menuBar != null)
            menuBar.paint(graphics);
    }
}
