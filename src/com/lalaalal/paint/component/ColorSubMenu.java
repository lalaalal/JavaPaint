package com.lalaalal.paint.component;

import com.lalaalal.kswing.KSubMenu;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.figure.FigureHandler;

public class ColorSubMenu extends KSubMenu implements Observer {
    private final FigureHandler figureHandler;

    public ColorSubMenu(String text, FigureHandler figureHandler) {
        super(text);

        this.figureHandler = figureHandler;
        this.figureHandler.addObserver(this);

        disable();
    }

    @Override
    public void update() {
        if (figureHandler.hasSelectedFigures())
            enable();
        else
            disable();
    }
}
