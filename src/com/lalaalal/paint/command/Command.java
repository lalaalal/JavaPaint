package com.lalaalal.paint.command;

import com.lalaalal.paint.PaintHandler;

public interface Command {
    void execute();
    void undo();
    void redo();
}
