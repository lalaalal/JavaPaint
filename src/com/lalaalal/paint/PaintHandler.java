package com.lalaalal.paint;

import com.lalaalal.kswing.KContainer;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.CommandManager;
import com.lalaalal.paint.figure.FigureHandler;
import com.lalaalal.paint.mode.CreateFigureMode;
import com.lalaalal.paint.mode.Mode;
import com.lalaalal.paint.mode.NormalMode;

public class PaintHandler extends Subject {
    public final Mode normalMode = new NormalMode(this);
    public final Mode createFigureMode = new CreateFigureMode(this);

    private Mode currentMode = normalMode;
    private KContainer paintArea;
    private final FigureHandler figureHandler = new FigureHandler();
    private final CommandManager commandManager = new CommandManager();

    public FigureHandler getFigureHandler() {
        return figureHandler;
    }

    public Mode getCurrentMode() {
        return currentMode;
    }

    public void setNormalMode() {
        setMode(normalMode);
    }

    public void setPaintArea(KContainer paintArea) {
        this.paintArea = paintArea;
        paintArea.addMouseListener(currentMode);
    }

    public void executeCommand(Command command) {
        if (command != null)
            commandManager.execute(command);
    }

    public void setMode(Mode mode) {
        if (paintArea != null) {
            paintArea.removeMouseListener(currentMode);
            paintArea.addMouseListener(mode);
        }

        this.currentMode = mode;
        figureHandler.unselectFigures();
        notifyObservers();
    }

    public void repaint() {
        paintArea.repaint();
    }
}
