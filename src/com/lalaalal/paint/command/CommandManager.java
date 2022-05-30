package com.lalaalal.paint.command;

import java.util.Stack;

public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public void execute(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
    }

    public void undo() {
        Command command = undoStack.pop();
        command.undo();
        redoStack.add(command);
    }

    public void redo() {
        Command command = redoStack.pop();
        command.redo();
        undoStack.add(command);
    }
}
