package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.KCheckableMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.component.listener.DrawActionListener;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.mode.Mode;

public class DrawMenuItem extends KCheckableMenuItem implements Observer {

    private final PaintHandler paintHandler;
    private final Mode mode;
    private final Figure.Type figureType;

    public DrawMenuItem(Figure.Type type, PaintHandler paintHandler) {
        super(type.name());
        this.figureType = type;
        this.paintHandler = paintHandler;
        this.mode = paintHandler.createFigureMode;
        this.addActionListener(new DrawActionListener(paintHandler, type, mode));

        this.paintHandler.addObserver(this);
    }

    @Override
    public void update() {
        Figure.Type selectedType = paintHandler.getFigureHandler().getFigureType();
        setChecked(paintHandler.getCurrentMode() == mode && selectedType == figureType);
    }
}
