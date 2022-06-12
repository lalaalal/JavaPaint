package com.lalaalal.paint.component;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.ChangeFigureColorCommand;
import com.lalaalal.paint.command.Command;

import java.awt.*;

public class ColorMenuItem extends KMenuItem {

    public ColorMenuItem(String text, Color color, ChangeFigureColorCommand.Type type, PaintHandler paintHandler) {
        super(text);

        addActionListener(event -> {
            if (!paintHandler.getFigureHandler().hasSelectedFigures())
                return;

            Command command = new ChangeFigureColorCommand(paintHandler, color, type);
            paintHandler.executeCommand(command);
        });
    }
}
