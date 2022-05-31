package com.lalaalal.paint.mode;

import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.GroupFiguresCommand;

import java.awt.*;

public class GroupFigureMode extends Mode {
    public GroupFigureMode(PaintHandler paintHandler) {
        super(paintHandler);
    }

    @Override
    public Command createCommand(Point start, Point end) {
        return new GroupFiguresCommand(paintHandler);
    }
}
