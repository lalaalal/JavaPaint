package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.util.ArrayList;

public class CutFiguresCommand implements Command {

    private final PaintHandler paintHandler;
    private final FigureHandler figureHandler;
    private final ArrayList<Figure> selectedFigures = new ArrayList<>();

    public CutFiguresCommand(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
        this.figureHandler = paintHandler.getFigureHandler();
    }

    @Override
    public void execute() {
        selectedFigures.addAll(figureHandler.getSelectedFigures());
        figureHandler.cutSelectedFigures();
    }

    @Override
    public void undo() {
        figureHandler.addFigures(selectedFigures);
    }

    @Override
    public void redo() {
        figureHandler.selectFigures(selectedFigures);
        figureHandler.cutSelectedFigures();
    }
}
