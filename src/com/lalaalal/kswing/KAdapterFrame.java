package com.lalaalal.kswing;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class KAdapterFrame extends JFrame {
    private KFrame frame = null;

    public KAdapterFrame() {
        setSize(800, 500);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setKFrame(@NotNull KFrame frame) {
        this.frame = frame;
        frame.setWidth(getWidth());
        frame.setHeight(getHeight());

    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (frame != null)
            frame.paint(graphics);
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent mouseEvent) {
        if (frame != null)
            frame.processMouseMotionEvent(mouseEvent);
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        if (frame != null)
            frame.processMouseEvent(mouseEvent);
    }
}
