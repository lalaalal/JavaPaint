package com.lalaalal.paint.command;

import com.lalaalal.paint.Subject;

import java.util.Stack;

public class CommandManager extends Subject {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public void execute(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
        notifyObservers();
    }

    public void undo() {
        Command command = undoStack.pop();
        command.undo();
        redoStack.add(command);
        notifyObservers();
    }

    public void redo() {
        Command command = redoStack.pop();
        command.redo();
        undoStack.add(command);
        notifyObservers();
    }

    public boolean undoable() {
        return !undoStack.isEmpty();
    }

    public boolean redoable() {
        return !redoStack.isEmpty();
    }
}
