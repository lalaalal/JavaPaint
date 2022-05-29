package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KSubMenu extends KMenu {
    public KSubMenu(String text) {
        super(text);
        setWidth(MATCH_PARENT);
        setPadding(KMenuItem.DEFAULT_MENU_ITEM_PADDING);
    }

    @Override
    public boolean contains(int x, int y) {
        return ((this.x < x && x <= this.x + getWidth())
                && (this.y < y && y < this.y + getHeight()))
                || (container.isVisible() && container.contains(x, y));
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        if (contains(event.getX(), event.getY())) {
            open();
        } else {
            close();
        }
        super.processMouseMotionEvent(event);
    }

    @Override
    protected void measureSize(Graphics graphics) {
        super.measureSize(graphics);
        container.setPoint(x + getWidth() + container.margin.left, y + container.margin.top);
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        String arrow = ">";
        int arrowWidth = fontMetrics.stringWidth(arrow);

        graphics.drawString(arrow, x + getWidth() - padding.right - arrowWidth, textY);
    }
}
