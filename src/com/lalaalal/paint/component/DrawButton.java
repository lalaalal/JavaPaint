package com.lalaalal.paint.component;

import com.lalaalal.kswing.KButton;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.mode.Mode;

public class DrawButton extends KButton implements Observer {

    private final PaintHandler paintHandler;
    private final Mode mode;
    private final Figure.Type figureType;

    public DrawButton(Figure.Type type, PaintHandler paintHandler) {
        super(type.name());
        this.figureType = type;
        this.paintHandler = paintHandler;
        this.mode = paintHandler.createFigureMode;
        this.addActionListener(new DrawActionListener(paintHandler, type, mode));

        this.paintHandler.addObserver(this);
        setBorder(false);
        setMargin(0, 0, 0, 1);
    }

    @Override
    public void update() {
        Figure.Type selectedType = paintHandler.getFigureHandler().getFigureType();
        setBorder(paintHandler.getCurrentMode() == mode && selectedType == figureType);
    }
}
