package com.lalaalal.kswing;

import java.awt.*;

public class KPanel extends KContainer {
    public KPanel() {
        super(0, 0, MATCH_PARENT, MATCH_PARENT);
        setLayout(new KLinearKLayout(KLinearKLayout.Orientation.Vertical));
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
    }
}
