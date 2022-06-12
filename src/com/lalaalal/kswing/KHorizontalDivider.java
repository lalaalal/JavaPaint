package com.lalaalal.kswing;

import java.awt.*;

public class KHorizontalDivider extends KLabel {
    public KHorizontalDivider() {
        super(MATCH_PARENT, 1, "");
        setBorder(true);
        setBorderColor(Color.GRAY);
    }

    public KHorizontalDivider(int height) {
        super(MATCH_PARENT, height, "");
        setBorder(true);
        setBorderColor(Color.GRAY);
    }
}
