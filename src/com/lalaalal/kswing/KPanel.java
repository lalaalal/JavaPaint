package com.lalaalal.kswing;

import java.awt.*;

public class KPanel extends KContainer {
    public KPanel() {
        super(0, 0, MATCH_PARENT, MATCH_PARENT);
        setLayout(KLinearKLayout.Vertical);
        setPadding(0, 0, 0, 0);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
    }
}
