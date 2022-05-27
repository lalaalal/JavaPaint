package com.lalaalal.kswing;

import java.awt.*;

public class KButton extends KAbstractButton {
    private Color backgroundColor = Color.WHITE;

    public KButton(String text) {
        super(text);
    }

    public KButton(int width, int height, String text) {
        super(width, height, text);
    }

    public KButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    protected void paintContent(Graphics graphics) {
        graphics.setColor(backgroundColor);
        graphics.fillRect(x, y, getWidth(), getHeight());

        super.paintContent(graphics);
    }
}
