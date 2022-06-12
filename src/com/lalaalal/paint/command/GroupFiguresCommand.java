package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.util.ArrayList;

public class GroupFiguresCommand implements Command {
    private final PaintHandler paintHandler;

    private final ArrayList<Figure> backup = new ArrayList<>();
    private final ArrayList<Figure> selectedFigures = new ArrayList<>();
    private Figure group;

    public GroupFiguresCommand(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
    }


    @Override
    public void execute() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        backup.addAll(figureHandler.getFigures());
        selectedFigures.addAll(figureHandler.getSelectedFigures());
        figureHandler.groupSelectedFigures();

        ArrayList<Figure> figures = figureHandler.getFigures();
        group = figures.get(figures.size() - 1);
    }

    @Override
    public void undo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.setFigures(backup);
        figureHandler.selectFigures(selectedFigures);
    }

    @Override
    public void redo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.removeFigures(selectedFigures);
        figureHandler.addFigure(group);
    }
}
