package com.lalaalal.kswing;

public class KButton extends KAbstractButton {
    public static final BoxModel DEFAULT_BUTTON_PADDING = new BoxModel(10, 15);

    public KButton(String text) {
        super(text);
        setPadding(DEFAULT_BUTTON_PADDING);
    }

    public KButton(int width, int height, String text) {
        super(width, height, text);
    }

    public KButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }
}
