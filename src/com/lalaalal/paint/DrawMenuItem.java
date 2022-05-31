package com.lalaalal.paint;

import com.lalaalal.kswing.KActionListener;
import com.lalaalal.kswing.KCheckableMenuItem;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;
import com.lalaalal.paint.mode.Mode;

import java.awt.event.ActionEvent;

public class DrawMenuItem extends KCheckableMenuItem implements Observer {
    private static class DrawActionListener implements KActionListener {

        private final PaintHandler paintHandler;
        private Figure.Type figureType;
        private final Mode mode;

        public DrawActionListener(PaintHandler paintHandler, Figure.Type type, Mode mode) {
            this.paintHandler = paintHandler;
            this.figureType = type;
            this.mode = mode;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source instanceof KCheckableMenuItem) {
                KCheckableMenuItem checkableMenuItem = (KCheckableMenuItem)source;
                if (checkableMenuItem.isChecked()) {
                    FigureHandler figureHandler = paintHandler.getFigureHandler();
                    figureHandler.unselectFigures();
                    figureHandler.setFigureType(figureType);
                    paintHandler.setMode(mode);
                }

            }
        }
    }

    private final PaintHandler paintHandler;
    private final Mode mode;
    private final Figure.Type figureType;

    public DrawMenuItem(Figure.Type type, PaintHandler paintHandler) {
        super(type.name());
        this.figureType = type;
        this.paintHandler = paintHandler;
        this.mode = paintHandler.createFigureMode;
        this.addActionListener(new DrawActionListener(paintHandler, type, mode));

        this.paintHandler.addObserver(this);
    }

    @Override
    public void update() {
        if (paintHandler.getCurrentMode() != mode || paintHandler.getFigureHandler().getFigureType() != figureType) {
            setChecked(false);
        }
    }
}
