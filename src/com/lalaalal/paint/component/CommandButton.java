package com.lalaalal.paint.component;

import com.lalaalal.kswing.KButton;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.command.CommandManager;

public class CommandButton extends KButton implements Observer {

    private final CommandActionListener.CommandType type;
    private final CommandManager commandManager;

    public CommandButton(CommandActionListener.CommandType type, CommandManager commandManager) {
        super(type.name());

        setBorder(false);

        this.type = type;
        this.commandManager = commandManager;
        addActionListener(new CommandActionListener(type, commandManager));

        commandManager.addObserver(this);
        disable();
    }

    @Override
    public void update() {
        if (isAvailable())
            enable();
        else
            disable();
    }

    private boolean isAvailable() {
        if (type == CommandActionListener.CommandType.Undo)
            return commandManager.undoable();
        return commandManager.redoable();
    }
}
