package com.lalaalal.kswing;

import java.awt.event.MouseEvent;

public class KMenuBar extends KContainer {
    public KMenuBar() {
        super(0, 0, MATCH_PARENT, WRAP_CONTENT);
        setLayout(KLinearKLayout.Horizontal);
        setPadding(0, 0, 0, 0);
        setBorder(true);
    }

    public boolean isMenuOpened() {
        for (KComponent component : children) {
            if (component instanceof KMenu) {
                KMenu menu = (KMenu)component;
                if (menu.isOpened())
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(int x, int y) {
        for (KComponent component : children) {
            if (component instanceof KMenu) {
                KMenu menu = (KMenu)component;
                if (menu.contains(x, y))
                    return true;
            }
        }

        return false;
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        super.processMouseMotionEvent(event);

        if (!isMenuOpened() || !contains(event.getX(), event.getY()))
            return;
        for (KComponent component : children) {
            if (component instanceof KMenu) {
                KMenu menu = (KMenu)component;
                if (menu.contains(event.getX(), event.getY()))
                    menu.open();
                else
                    menu.close();
            }
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        if (event.getID() != MouseEvent.MOUSE_CLICKED)
            return;

        for (KComponent component : children) {
            if (component instanceof KMenu) {
                KMenu menu = (KMenu)component;
                if (menu.contains(event.getX(), event.getY())) {
                    menu.processMouseEvent(event);
                } else {
                    menu.close();
                }
            }
        }
        repaint();
    }
}
