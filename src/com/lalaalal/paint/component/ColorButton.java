package com.lalaalal.paint.component;

import com.lalaalal.kswing.KButton;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.ChangeFigureColorCommand;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.figure.FigureHandler;

import java.awt.*;

public class ColorButton extends KButton implements Observer {
    private final FigureHandler figureHandler;

    public ColorButton(String text, Color color, ChangeFigureColorCommand.Type type, PaintHandler paintHandler) {
        super(text);
        setBorder(false);

        figureHandler = paintHandler.getFigureHandler();

        addActionListener(event -> {
            if (!figureHandler.hasSelectedFigures())
                return;

            Command command = new ChangeFigureColorCommand(paintHandler, color, type);
            paintHandler.executeCommand(command);
        });

        figureHandler.addObserver(this);
        disable();
    }

    @Override
    public void update() {
        if (figureHandler.hasSelectedFigures())
            enable();
        else
            disable();
    }
}
