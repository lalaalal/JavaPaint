package com.lalaalal.paint.component;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
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

    public static class UndoMenuItem extends KMenuItem implements Observer {
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
}

