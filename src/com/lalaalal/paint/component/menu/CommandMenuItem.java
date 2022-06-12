package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.command.CommandManager;
import com.lalaalal.paint.component.CommandActionListener;

public class CommandMenuItem extends KMenuItem implements Observer {
    private final CommandActionListener.CommandType type;
    private final CommandManager commandManager;

    public CommandMenuItem(CommandActionListener.CommandType type, CommandManager commandManager) {
        super(type.name());
        this.type = type;
        this.commandManager = commandManager;
        this.commandManager.addObserver(this);

        addActionListener(new CommandActionListener(type, commandManager));
        disable();
    }


    @Override
    public void update() {
        if (isAvailable())
            enable();
        else disable();
    }

    private boolean isAvailable() {
        if (type == CommandActionListener.CommandType.Undo)
            return commandManager.undoable();
        return commandManager.redoable();
    }
}

