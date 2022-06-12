package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.awt.*;
import java.util.ArrayList;

public class MoveFigureCommand implements Command {
    private final Point start;
    private final Point end;
    private final PaintHandler paintHandler;

    private final ArrayList<Figure> selectedFigures = new ArrayList<>();

    public MoveFigureCommand(Point start, Point end, PaintHandler paintHandler) {
        this.start = start;
        this.end = end;
        this.paintHandler = paintHandler;
    }

    @Override
    public void execute() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        selectedFigures.addAll(figureHandler.getSelectedFigures());
        figureHandler.moveSelectedFigures(start, end);
        paintHandler.repaint();
    }

    @Override
    public void undo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.selectFigures(selectedFigures);
        figureHandler.moveSelectedFigures(end, start);
        paintHandler.repaint();
    }

    @Override
    public void redo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.selectFigures(selectedFigures);
        figureHandler.moveSelectedFigures(start, end);
        paintHandler.repaint();
    }
}
