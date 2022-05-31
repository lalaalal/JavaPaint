package com.lalaalal.paint;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.GroupFiguresCommand;
import com.lalaalal.paint.figure.FigureHandler;

public class GroupMenuItem extends KMenuItem implements Observer {
    private final FigureHandler figureHandler;

    public GroupMenuItem(PaintHandler paintHandler) {
        super("Group");
        this.figureHandler = paintHandler.getFigureHandler();
        this.figureHandler.addObserver(this);

        addActionListener(event -> {
            Command command = new GroupFiguresCommand(paintHandler);
            paintHandler.executeCommand(command);
        });
        disable();
    }

    @Override
    public void update() {
        if (!figureHandler.isSelected()
                || figureHandler.getSelectedFigures().size() == 1) {
            disable();
        } else {
            enable();
        }
    }
}
