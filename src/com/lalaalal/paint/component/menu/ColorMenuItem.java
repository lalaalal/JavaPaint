package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.ChangeFigureColorCommand;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.figure.FigureHandler;

import java.awt.*;

public class ColorMenuItem extends KMenuItem {
    public ColorMenuItem(String text, Color color, ChangeFigureColorCommand.Type type, PaintHandler paintHandler) {
        super(text);
        FigureHandler figureHandler = paintHandler.getFigureHandler();

        addActionListener(event -> {
            if (!figureHandler.hasSelectedFigures())
                return;

            Command command = new ChangeFigureColorCommand(paintHandler, color, type);
            paintHandler.executeCommand(command);
        });
    }
}
