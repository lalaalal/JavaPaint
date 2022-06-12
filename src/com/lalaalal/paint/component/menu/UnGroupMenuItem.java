package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.UnGroupFiguresCommand;
import com.lalaalal.paint.figure.FigureHandler;

public class UnGroupMenuItem extends KMenuItem implements Observer {
    private final FigureHandler figureHandler;

    public UnGroupMenuItem(PaintHandler paintHandler) {
        super("Ungroup");
        this.figureHandler = paintHandler.getFigureHandler();
        this.figureHandler.addObserver(this);

        addActionListener(event -> {
            Command command = new UnGroupFiguresCommand(paintHandler);
            paintHandler.executeCommand(command);
        });
        disable();
    }

    @Override
    public void update() {
        if (!figureHandler.hasSelectedFigures() || !figureHandler.hasSelectedGroup()) {
            disable();
        } else {
            enable();
        }
    }
}
