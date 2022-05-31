package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.util.ArrayList;

public class PasteFiguresCommand implements Command {
    private final PaintHandler paintHandler;
    private final ArrayList<Figure> selectedFigures = new ArrayList<>();
    private final ArrayList<Figure> copiedFigures = new ArrayList<>();

    public PasteFiguresCommand(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
    }

    @Override
    public void execute() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        copiedFigures.addAll(figureHandler.getCopiedFigures());
        selectedFigures.addAll(figureHandler.getSelectedFigures());
        figureHandler.pasteSelectedFigures();
    }

    @Override
    public void undo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.selectFigures(copiedFigures);
        figureHandler.deleteSelectedFigures();
        figureHandler.selectFigures(selectedFigures);
    }

    @Override
    public void redo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.pasteFigures(copiedFigures);
    }
}
