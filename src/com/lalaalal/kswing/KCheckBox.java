package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KCheckBox extends KAbstractButton {

    private int interval = 5;
    private boolean isChecked = false;
    private final KButton checkIcon = new KButton(" ");

    public KCheckBox(String text) {
        super(text);
        init();
    }

    public KCheckBox(int width, int height, String text) {
        super(width, height, text);
        init();
    }

    public KCheckBox(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
        init();
    }

    private void init() {
        setBorder(false);
        checkIcon.setPadding(0, 5, 0, 5);
        checkIcon.addActionListener(event -> toggle());
        checkIcon.setTextAlignment(Alignment.Center);
    }

    public void setCheck(boolean value) {
        isChecked = value;
        if (isChecked)
            checkIcon.setText("V");
        else
            checkIcon.setText(" ");
    }

    public void toggle() {
        setCheck(!isChecked);
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        checkIcon.processMouseMotionEvent(event);
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        checkIcon.processMouseEvent(event);
    }

    @Override
    protected void measureContentSize(Graphics graphics) {
        checkIcon.setPoint(x + padding.left, y + padding.top);
        checkIcon.measureContentSize(graphics);
        checkIcon.setWidth(checkIcon.getHeight());
        super.measureContentSize(graphics);
    }

    @Override
    protected int measureHorizontalInterval(int textWidth) {
        super.measureHorizontalInterval(textWidth + checkIcon.getWidth() + interval);

        return checkIcon.getWidth() + padding.left + interval;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        checkIcon.paint(graphics);
    }
}
