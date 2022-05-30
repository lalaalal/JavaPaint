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
            figureHandler.unselectFigures();
            return null;
        }

        if (!figureHandler.isSelected() || !figureHandler.isSelectedFiguresContain(start)) {
            figureHandler.selectFigures(start, end);
            if (!figureHandler.isSelected()) {
                figureHandler.selectFigure(start);
                paintHandler.repaint();
                return new MoveFigureCommand(start, end, paintHandler);
            }
            paintHandler.repaint();
            return null;
        }
        return new MoveFigureCommand(start, end, paintHandler);
    }
}
