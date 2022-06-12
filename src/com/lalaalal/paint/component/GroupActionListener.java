package com.lalaalal.paint.component;

import com.lalaalal.kswing.KActionListener;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.GroupFiguresCommand;
import com.lalaalal.paint.command.UnGroupFiguresCommand;

import java.awt.event.ActionEvent;

public class GroupActionListener implements KActionListener {
    public enum GroupType {
        Group, Ungroup
    }

    private final GroupType type;
    private final PaintHandler paintHandler;

    public GroupActionListener(GroupType type, PaintHandler paintHandler) {
        this.type = type;
        this.paintHandler = paintHandler;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Command command = null;
        if (type == GroupType.Group)
            command = new GroupFiguresCommand(paintHandler);
        else
            command = new UnGroupFiguresCommand(paintHandler);

        paintHandler.executeCommand(command);
    }
}
