package com.lalaalal.paint.component;

import com.lalaalal.kswing.KAdapterFrame;
import com.lalaalal.kswing.KFrame;
import com.lalaalal.kswing.KToolBar;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.component.menu.PaintMenuBar;

public class PaintFrame extends KFrame {
    private final PaintHandler paintHandler = new PaintHandler();

    public PaintFrame(KAdapterFrame adapterFrame) {
        super(adapterFrame);
        setSize(1000, 700);

        setContentPane(new PaintPanel(paintHandler));

        PaintMenuBar menuBar = new PaintMenuBar(paintHandler);
        KToolBar toolBar = new KToolBar();
        PaintToolBar paintToolBar = new PaintToolBar(paintHandler);
        toolBar.add(paintToolBar);
        setMenuBar(menuBar);
        setToolBar(toolBar);
        repaint();
    }
}
