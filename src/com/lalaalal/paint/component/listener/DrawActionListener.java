package com.lalaalal.paint.component.listener;

import com.lalaalal.kswing.KActionListener;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;
import com.lalaalal.paint.mode.Mode;

import java.awt.event.ActionEvent;

public class DrawActionListener implements KActionListener {

    private final PaintHandler paintHandler;
    private final Figure.Type figureType;
    private final Mode mode;

    public DrawActionListener(PaintHandler paintHandler, Figure.Type type, Mode mode) {
        this.paintHandler = paintHandler;
        this.figureType = type;
        this.mode = mode;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (paintHandler.getCurrentMode() != mode) {
            FigureHandler figureHandler = paintHandler.getFigureHandler();
            figureHandler.unselectFigures();
            figureHandler.setFigureType(figureType);
            paintHandler.setMode(mode);
        } else {
            paintHandler.setNormalMode();
        }
    }
}
