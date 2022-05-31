package com.lalaalal.paint;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.command.CommandManager;


public class UndoMenuItem extends KMenuItem implements Observer {
    private final CommandManager commandManager;

    public UndoMenuItem(CommandManager commandManager) {
        super("Undo");
        this.commandManager = commandManager;
        this.commandManager.addObserver(this);

        addActionListener(event -> this.commandManager.undo());
        disable();
    }


    @Override
    public void update() {
        if (commandManager.undoable())
            enable();
        else disable();
    }
}
