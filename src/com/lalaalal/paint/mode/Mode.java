package com.lalaalal.paint.mode;

import com.lalaalal.kswing.KMouseListener;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.Command;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Mode implements KMouseListener {
    protected PaintHandler paintHandler;

    protected Point start = new Point();
    protected Point end = new Point();

    public Mode(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        start = event.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        end = event.getPoint();
        Command command = createCommand(start, end);
        paintHandler.executeCommand(command);
    }

    public abstract Command createCommand(Point start, Point end);
}
