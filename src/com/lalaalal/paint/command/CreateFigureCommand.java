package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.awt.*;

public class CreateFigureCommand implements Command {
    private final Point start;
    private final Point end;

    private final PaintHandler paintHandler;

    private Figure figure;

    public CreateFigureCommand(Point start, Point end, PaintHandler paintHandler) {
        this.start = start;
        this.end = end;
        this.paintHandler = paintHandler;
    }

    @Override
    public void execute() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figure = Figure.createFigure(figureHandler.getFigureType(), start, end);
        figureHandler.addFigure(figure);
        paintHandler.repaint();
        paintHandler.setNormalMode();
    }

    @Override
    public void undo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.removeFigure(figure);
        paintHandler.repaint();
    }

    @Override
    public void redo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.addFigure(figure);
        paintHandler.repaint();
    }
}
