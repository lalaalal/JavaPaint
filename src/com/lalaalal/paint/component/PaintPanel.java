package com.lalaalal.paint.component;

import com.lalaalal.kswing.KPanel;
import com.lalaalal.paint.PaintHandler;

import java.awt.*;

public class PaintPanel extends KPanel {
    private final PaintHandler paintHandler;

    public PaintPanel(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
        this.paintHandler.setPaintArea(this);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
        paintHandler.getFigureHandler().paint(graphics);
    }
}
