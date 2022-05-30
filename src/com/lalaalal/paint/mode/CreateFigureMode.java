package com.lalaalal.paint.mode;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.CreateFigureCommand;

import java.awt.*;

public class CreateFigureMode extends Mode {
    public CreateFigureMode(PaintHandler paintHandler) {
        super(paintHandler);
    }

    @Override
    public Command createCommand(Point start, Point end) {
        return new CreateFigureCommand(start, end, paintHandler);
    }
}
