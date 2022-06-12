package com.lalaalal.paint.component;

import com.lalaalal.kswing.KContainer;
import com.lalaalal.kswing.KPanel;
import com.lalaalal.kswing.KTabbedPanel;
import com.lalaalal.paint.PaintHandler;

import java.awt.*;

public class PaintPanel extends KPanel {
    private final PaintHandler paintHandler;

    public PaintPanel(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
        PaintToolBar toolBar = new PaintToolBar(paintHandler);
        KPanel paintArea = new KPanel();
        paintArea.setBorder(true);
        add(toolBar);
        add(paintArea);
        this.paintHandler.setPaintArea(paintArea);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
        paintHandler.getFigureHandler().paint(graphics);
    }
}
