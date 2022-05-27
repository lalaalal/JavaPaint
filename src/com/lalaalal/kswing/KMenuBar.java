package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public class KMenuBar extends KContainer {
    public KMenuBar() {
        super(0, 0, MATCH_PARENT, WRAP_CONTENT);
        setLayout(new KLinearKLayout(KLinearKLayout.Orientation.Horizontal));
        setPadding(0, 0, 0, 0);
        setBorder(true);
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
    public void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);

        for (KComponent component : children) {
            if (component instanceof KMenu) {
                KMenu menu = (KMenu)component;
                if (menu.contains(event.getX(), event.getY()))
                    menu.toggle();
                else
                    menu.close();
            }
        }
        repaint();
    }
}
