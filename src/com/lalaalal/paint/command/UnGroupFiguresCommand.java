package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.util.ArrayList;

public class UnGroupFiguresCommand implements Command {

    private final PaintHandler paintHandler;
    private final ArrayList<Figure> backup = new ArrayList<>();
    private final ArrayList<Figure> selectedFigures = new ArrayList<>();

    public UnGroupFiguresCommand(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
    }

    @Override
    public void execute() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        backup.addAll(figureHandler.getFigures());
        selectedFigures.addAll(figureHandler.getSelectedFigures());
        figureHandler.ungroupSelectedFigures();
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
        figureHandler.selectFigures(selectedFigures);
        figureHandler.ungroupSelectedFigures();
    }
}
