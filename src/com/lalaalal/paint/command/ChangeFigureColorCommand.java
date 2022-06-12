package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;

import java.awt.*;
import java.util.ArrayList;

public class ChangeFigureColorCommand implements Command {

    public enum Type {
        Background, Line
    }

    private final PaintHandler paintHandler;
    private final FigureHandler figureHandler;
    private final ArrayList<Figure> figureBackup = new ArrayList<>();
    private final ArrayList<Color> colorBackup = new ArrayList<>();
    private final Color color;
    private final Type type;

    public ChangeFigureColorCommand(PaintHandler paintHandler, Color color, Type type) {
        this.paintHandler = paintHandler;
        this.figureHandler = paintHandler.getFigureHandler();
        this.color = color;
        this.type = type;
    }

    @Override
    public void execute() {
        figureBackup.addAll(figureHandler.getSelectedFigures());
        if (type == Type.Line) {
            for (Figure figure : figureBackup)
                colorBackup.add(figure.getLineColor());
            figureHandler.changeLineColor(color);
        } else {
            for (Figure figure : figureBackup)
                colorBackup.add(figure.getBackgroundColor());
            figureHandler.changeBackgroundColor(color);
        }
    }

    @Override
    public void undo() {
        if (type == Type.Line) {
            for (int i = 0; i < figureBackup.size(); i++) {
                Figure figure = figureBackup.get(i);
                figure.setLineColor(colorBackup.get(i));
            }
        } else {
            for (int i = 0; i < figureBackup.size(); i++) {
                Figure figure = figureBackup.get(i);
                figure.setBackgroundColor(colorBackup.get(i));
            }
        }
    }

    @Override
    public void redo() {
        figureHandler.selectFigures(figureBackup);
        if (type == Type.Line) {
            figureHandler.changeLineColor(color);
        } else {
            figureHandler.changeBackgroundColor(color);
        }
    }
}
