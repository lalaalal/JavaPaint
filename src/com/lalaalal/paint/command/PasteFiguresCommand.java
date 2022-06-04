package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.util.ArrayList;

public class PasteFiguresCommand implements Command {
    private final PaintHandler paintHandler;
    private ArrayList<Figure> copies;
    private final ArrayList<Figure> selectedFigures = new ArrayList<>();

    public PasteFiguresCommand(PaintHandler paintHandler) {
        this.paintHandler = paintHandler;
    }

    @Override
    public void execute() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        selectedFigures.addAll(figureHandler.getSelectedFigures());
        copies = figureHandler.pasteCopiedFigures();
        paintHandler.repaint();
    }

    @Override
    public void undo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.removeFigures(copies);
        figureHandler.selectFigures(selectedFigures);
        paintHandler.repaint();
    }

    @Override
    public void redo() {
        FigureHandler figureHandler = paintHandler.getFigureHandler();
        figureHandler.addFigures(copies);
        paintHandler.repaint();
    }
}
