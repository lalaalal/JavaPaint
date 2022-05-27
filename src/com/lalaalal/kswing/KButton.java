package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KButton extends KAbstractButton {
    public KButton(String text) {
        super(text);
    }

    public KButton(int width, int height, String text) {
        super(width, height, text);
    }

    public KButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }
}
