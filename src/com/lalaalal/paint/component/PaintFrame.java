package com.lalaalal.paint.component;

import com.lalaalal.kswing.KAdapterFrame;
import com.lalaalal.kswing.KFrame;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.component.menu.PaintMenuBar;

public class PaintFrame extends KFrame {
    private final PaintHandler paintHandler = new PaintHandler();

    public PaintFrame(KAdapterFrame adapterFrame) {
        super(adapterFrame);
        setContentPane(new PaintPanel(paintHandler));

        PaintMenuBar menuBar = new PaintMenuBar(paintHandler);
        setMenuBar(menuBar);
    }
}
