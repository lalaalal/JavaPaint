package com.lalaalal.paint.mode;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.MoveFigureCommand;
import com.lalaalal.paint.figure.FigureHandler;

import java.awt.*;

public class NormalMode extends Mode {

    public NormalMode(PaintHandler paintHandler) {
        super(paintHandler);
    }

    @Override
    public Command createCommand(Point start, Point end) {
        FigureHandler figureHandler = paintHandler.getFigureHandler();

        if (start.equals(end)) {
            figureHandler.selectFigure(start);
            return null;
        }

        if (!figureHandler.hasSelectedFigures() || !figureHandler.isSelectedFiguresContain(start))
            return selectFigures(start, end);

        return new MoveFigureCommand(start, end, paintHandler);
    }

    private Command selectFigures(Point start, Point end) {
        FigureHandler figureHandler = paintHandler.getFigureHandler();

        figureHandler.selectFigures(start, end);
        if (!figureHandler.hasSelectedFigures()) {
            figureHandler.selectFigure(start);
            if (!figureHandler.hasSelectedFigures())
                return null;
            paintHandler.repaint();
            return new MoveFigureCommand(start, end, paintHandler);
        }
        paintHandler.repaint();
        return null;
    }
}
