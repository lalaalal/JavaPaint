package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KCheckableMenuItem extends KMenuItem {
    private boolean isChecked = false;
    private final KButton checkLabel = new KButton("V");

    private static final BoxModel DEFAULT_CHECK_BUTTON_PADDING = new BoxModel(5, 10);

    public KCheckableMenuItem(String text) {
        super(text);

        checkLabel.setBorder(false);
        checkLabel.setPadding(DEFAULT_CHECK_BUTTON_PADDING);
    }

    public void toggle() {
        isChecked = !isChecked;
    }

    public void setChecked(boolean value) {
        isChecked = value;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    protected void measureSize(Graphics graphics) {
        super.measureSize(graphics);
        checkLabel.setPoint(x, y);
        checkLabel.measureSize(graphics);
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        super.processMouseMotionEvent(event);

        if (contains(event.getX(), event.getY()) && isEnabled())
            checkLabel.setBackgroundColor(hoverColor);
        else
            checkLabel.setBackgroundColor(normalColor);
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_CLICKED)
            toggle();

        super.processMouseEvent(event);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
        if (isChecked)
            checkLabel.paint(graphics);
    }
}
