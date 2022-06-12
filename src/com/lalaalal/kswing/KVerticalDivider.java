package com.lalaalal.kswing;

import java.awt.*;

public class KVerticalDivider extends KLabel {
    public KVerticalDivider() {
        super(1, MATCH_PARENT, "");
        setBorder(true);
        setBorderColor(Color.GRAY);
    }

    public KVerticalDivider(int width) {
        super(width, MATCH_PARENT, "");
        setBorder(true);
        setBorderColor(Color.GRAY);
    }

    public KVerticalDivider(int width, BoxModel margin) {
        super(width, MATCH_PARENT, "");
        setBorder(true);
        setBorderColor(Color.GRAY);
        setMargin(margin);
    }
}
