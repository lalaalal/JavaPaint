package com.lalaalal.paint.figure;

import com.lalaalal.paint.Subject;

import java.awt.*;
import java.util.ArrayList;

public class FigureHandler extends Subject {
    private final ArrayList<Figure> figures = new ArrayList<>();
    private final ArrayList<Figure> selectedFigures = new ArrayList<>();

    private final ArrayList<Figure> copiedFigures = new ArrayList<>();

    private static final Point COPIED_FIGURES_DELTA = new Point(20, 20);

    private static final Color SELECTED_AREA_COLOR = Color.GRAY;
    private static final int SELECTED_AREA_MARGIN = 10;

    private static final float[] DASH = { 10, 7f };
    private static final BasicStroke SELECTED_AREA_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, DASH, 0);

    private Figure.Type figureType = Figure.Type.Rectangle;

    public Figure.Type getFigureType() {
        return figureType;
    }

    public void setFigureType(Figure.Type figureType) {
        this.figureType = figureType;
    }

    public void createFigure(Point start, Point end) {
        Figure figure = Figure.createFigure(figureType, start, end);
        figures.add(figure);
    }

    public void setFigures(ArrayList<Figure> figures) {
        this.figures.clear();
        this.figures.addAll(figures);
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
        selectFigure(figure);
    }

    public void addFigures(ArrayList<Figure> figures) {
        this.figures.addAll(figures);
    }

    public void removeFigure(Figure figure) {
        figures.remove(figure);
        unselectFigures();
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }

    public ArrayList<Figure> getCopiedFigures() {
        return copiedFigures;
    }

    public void setCopiedFigures(ArrayList<Figure> copiedFigures) {
        this.copiedFigures.addAll(copiedFigures);
    }

    public void selectFigure(Figure figure) {
        selectedFigures.clear();
        selectedFigures.add(figure);
        notifyObservers();
    }

    public void selectFigures(ArrayList<Figure> figures) {
        selectedFigures.clear();
        selectedFigures.addAll(figures);
        notifyObservers();
    }

    public void selectFigure(Point at) {
        selectedFigures.clear();

        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure figure = figures.get(i);
            if (figure.contains(at)) {
                selectedFigures.add(figure);
                break;
            }
        }
        notifyObservers();
    }

    public void selectFigures(Point start, Point end) {
        selectedFigures.clear();

        for (Figure figure : figures) {
            if (figure.isContained(start, end)) {
                selectedFigures.add(figure);
            }
        }
        notifyObservers();
    }

    public ArrayList<Figure> getSelectedFigures() {
        return selectedFigures;
    }

    public boolean hasSelectedFigures() {
        return !selectedFigures.isEmpty();
    }

    public void unselectFigures() {
        selectedFigures.clear();
        notifyObservers();
    }

    public void deleteSelectedFigures() {
        figures.removeAll(selectedFigures);
        unselectFigures();
    }

    public void moveSelectedFigures(Point from, Point to) {
        if (isSelectedFiguresContain(from)) {
            for (Figure figure : selectedFigures)
                figure.move(to.x - from.x, to.y - from.y);
        }
    }

    public void groupSelectedFigures() {
        Group group = new Group(selectedFigures);
        figures.removeAll(selectedFigures);
        figures.add(group);

        unselectFigures();
        selectedFigures.add(group);
        notifyObservers();
    }

    public void ungroupSelectedFigures() {
        for (Figure figure : selectedFigures) {
            if (figure instanceof Group) {
                Group group = (Group) figure;
                figures.remove(group);
                figures.addAll(group.getChildren());
            }
        }

        unselectFigures();
    }

    public boolean hasSelectedGroup() {
        for (Figure figure : selectedFigures) {
            if (figure instanceof Group) {
                return true;
            }
        }

        return false;
    }

    public boolean isSelectedFiguresContain(Point at) {
        for (Figure figure : selectedFigures) {
            if (figure.contains(at))
                return true;
        }

        return false;
    }

    public void moveFigures(ArrayList<Figure> target, Point from, Point to) {
        for (Figure figure : target) {
            if (figures.contains(figure))
                figure.move(to.x - from.x, to.y - from.y);
        }
    }

    public void copySelectedFigures() {
        copiedFigures.clear();
        for (Figure figure : selectedFigures) {
            Figure copy = figure.copy();
            copiedFigures.add(copy);
        }
        notifyObservers();
    }

    public void cutSelectedFigures() {
        copySelectedFigures();
        figures.removeAll(selectedFigures);
        unselectFigures();
    }

    public void pasteSelectedFigures() {
        pasteFigures(copiedFigures);
    }

    public void pasteFigures(ArrayList<Figure> copiedFigures) {
        for (Figure copiedFigure : copiedFigures) {
            copiedFigure.move(COPIED_FIGURES_DELTA.x, COPIED_FIGURES_DELTA.y);
        }

        this.figures.addAll(copiedFigures);
        selectedFigures.clear();
        selectedFigures.addAll(copiedFigures);

        copySelectedFigures();
    }

    public boolean hasCopiedFigures() {
        return !copiedFigures.isEmpty();
    }

    public void paint(Graphics graphics) {
        for (Figure figure : figures)
            figure.paint(graphics);

        if (graphics instanceof Graphics2D)
            paintSelectedArea((Graphics2D) graphics);
    }

    private void paintSelectedArea(Graphics2D graphics) {
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;
        int endX = 0;
        int endY = 0;

        for (Figure figure : selectedFigures) {
            startX = Math.min(startX, figure.getStartX());
            startY = Math.min(startY, figure.getStartY());
            endX = Math.max(endX, figure.getEndX());
            endY = Math.max(endY, figure.getEndY());
        }
        int width = endX - startX;
        int height = endY - startY;

        if (hasSelectedFigures()) {
            graphics.setColor(SELECTED_AREA_COLOR);
            Stroke defaultStroke = graphics.getStroke();
            graphics.setStroke(SELECTED_AREA_STROKE);
            graphics.drawRect(startX - SELECTED_AREA_MARGIN, startY - SELECTED_AREA_MARGIN, width + SELECTED_AREA_MARGIN * 2, height + SELECTED_AREA_MARGIN * 2);
            graphics.setStroke(defaultStroke);
        }
    }
}
