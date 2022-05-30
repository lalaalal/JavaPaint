package com.lalaalal.paint;

import com.lalaalal.kswing.KAdapterFrame;
import com.lalaalal.kswing.KFrame;

import java.awt.*;

public class PaintFrame extends KFrame {
    private final PaintHandler paintHandler = new PaintHandler();

    public PaintFrame(KAdapterFrame adapterFrame) {
        super(adapterFrame);
        setContentPane(new PaintPanel(paintHandler));

        PaintMenuBar menuBar = new PaintMenuBar(paintHandler);
        setMenuBar(menuBar);
    }
}
