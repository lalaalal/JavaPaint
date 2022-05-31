package com.lalaalal.paint;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.command.CommandManager;

public class RedoMenuItem extends KMenuItem implements Observer {
    private final CommandManager commandManager;

    public RedoMenuItem(CommandManager commandManager) {
        super("Redo");
        this.commandManager = commandManager;
        this.commandManager.addObserver(this);

        addActionListener(event -> this.commandManager.redo());
        disable();
    }


    @Override
    public void update() {
        if (commandManager.redoable())
            enable();
        else disable();
    }
}

