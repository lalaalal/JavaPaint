package com.lalaalal.paint.figure;

import com.lalaalal.paint.Subject;

import java.awt.*;
import java.util.ArrayList;

public class FigureHandler extends Subject {
    private final ArrayList<Figure> figures = new ArrayList<>();
    private final ArrayList<Figure> selectedFigures = new ArrayList<>();

    private final ArrayList<Figure> copiedFigures = new ArrayList<>();

    private static final Point COPIED_FIGURES_INTERVAL = new Point(20, 20);

    private static final Color SELECTED_AREA_COLOR = Color.GRAY;
    private static final int SELECTED_AREA_MARGIN = 10;

    private static final float[] DASH = { 10, 7f };
    private static final BasicStroke SELECTED_AREA_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, DASH, 0);
    private boolean showGroupBorder = false;

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
        selectFigures(figures);
    }

    public void removeFigure(Figure figure) {
        figures.remove(figure);
        unselectFigures();
    }

    public void removeFigures(ArrayList<Figure> figures) {
        this.figures.removeAll(figures);
        unselectFigures();
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }

    public ArrayList<Figure> getCopiedFigures() {
        return copiedFigures;
    }

    public void setCopiedFigures(ArrayList<Figure> copiedFigures) {
        this.copiedFigures.clear();
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

    public void changeBackgroundColor(Color backgroundColor) {
        for (Figure figure : selectedFigures) {
            figure.setBackgroundColor(backgroundColor);
        }
    }

    public void changeLineColor(Color lineColor) {
        for (Figure figure : selectedFigures) {
            figure.setLineColor(lineColor);
        }
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
        selectFigures(target);
    }

    public void copySelectedFigures() {
        copiedFigures.clear();
        copiedFigures.addAll(selectedFigures);
        notifyObservers();
    }

    public void cutSelectedFigures() {
        copySelectedFigures();
        figures.removeAll(selectedFigures);
        unselectFigures();
    }

    public ArrayList<Figure> pasteCopiedFigures() {
        return pasteFigures(copiedFigures);
    }

    public ArrayList<Figure> pasteFigures(ArrayList<Figure> copiedFigures) {
        ArrayList<Figure> newFigures = new ArrayList<>();

        for (Figure figure : copiedFigures) {
            Figure copy = figure.copy();
            copy.move(COPIED_FIGURES_INTERVAL.x, COPIED_FIGURES_INTERVAL.y);
            newFigures.add(copy);
        }

        this.figures.addAll(newFigures);
        selectFigures(newFigures);
        setCopiedFigures(newFigures);

        return newFigures;
    }

    public boolean hasCopiedFigures() {
        return !copiedFigures.isEmpty();
    }

    public void setShowGroupBorder(boolean value) {
        showGroupBorder = value;
    }

    public boolean isShowGroupBorder() {
        return showGroupBorder;
    }

    public void paint(Graphics graphics) {
        for (Figure figure : figures)
            figure.paint(graphics);

        if (graphics instanceof Graphics2D) {
            paintSelectedArea((Graphics2D) graphics);
            paintGroupBorder((Graphics2D) graphics);
        }

    }

    private void paintSelectedArea(Graphics2D graphics) {
        if (!hasSelectedFigures())
            return;

        Point start = getStartPoint(selectedFigures);
        Point end = getEndPoint(selectedFigures);
        int width = end.x - start.x;
        int height = end.y - start.y;

        graphics.setColor(SELECTED_AREA_COLOR);
        Stroke defaultStroke = graphics.getStroke();
        graphics.setStroke(SELECTED_AREA_STROKE);
        graphics.drawRect(start.x - SELECTED_AREA_MARGIN, start.y - SELECTED_AREA_MARGIN, width + SELECTED_AREA_MARGIN * 2, height + SELECTED_AREA_MARGIN * 2);
        graphics.setStroke(defaultStroke);
    }

    private Point getStartPoint(ArrayList<Figure> figures) {
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;

        for (Figure figure : figures) {
            startX = Math.min(startX, figure.getStartX());
            startY = Math.min(startY, figure.getStartY());
        }

        return new Point(startX, startY);
    }

    private Point getEndPoint(ArrayList<Figure> figures) {
        int endX = 0;
        int endY = 0;

        for (Figure figure : figures) {
            endX = Math.max(endX, figure.getEndX());
            endY = Math.max(endY, figure.getEndY());
        }

        return new Point(endX, endY);
    }

    private void paintGroupBorder(Graphics2D graphics) {
        if (!showGroupBorder)
            return;

        for (Figure figure : figures) {
            if (figure instanceof Group) {
                Stroke defaultStroke = graphics.getStroke();
                graphics.setColor(SELECTED_AREA_COLOR);
                graphics.setStroke(SELECTED_AREA_STROKE);
                graphics.drawRect(figure.getStartX(), figure.getStartY(), figure.getWidth(), figure.getHeight());
                graphics.setStroke(defaultStroke);
            }
        }
    }
}
