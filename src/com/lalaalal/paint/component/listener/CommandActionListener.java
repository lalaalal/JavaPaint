package com.lalaalal.paint.component.listener;

import com.lalaalal.kswing.KActionListener;
import com.lalaalal.paint.command.CommandManager;

import java.awt.event.ActionEvent;

public class CommandActionListener implements KActionListener {
    public enum CommandType {
        Undo, Redo
    }

    private final CommandType commandType;
    private final CommandManager commandManager;

    public CommandActionListener(CommandType commandType, CommandManager commandManager) {
        this.commandType = commandType;
        this.commandManager = commandManager;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (commandType == CommandType.Undo)
            commandManager.undo();
        else
            commandManager.redo();
    }
}
