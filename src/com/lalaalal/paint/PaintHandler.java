package com.lalaalal.paint;

import com.lalaalal.kswing.KContainer;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.CommandManager;
import com.lalaalal.paint.figure.Figure;
import com.lalaalal.paint.figure.FigureHandler;
import com.lalaalal.paint.mode.CreateFigureMode;
import com.lalaalal.paint.mode.Mode;
import com.lalaalal.paint.mode.NormalMode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PaintHandler extends Subject {
    public static final String SAVE_FILE_NAME = "save.paint";

    public final Mode normalMode = new NormalMode(this);
    public final Mode createFigureMode = new CreateFigureMode(this);

    private Mode currentMode = normalMode;
    private KContainer paintArea;
    private final FigureHandler figureHandler = new FigureHandler();
    private final CommandManager commandManager = new CommandManager();

    public FigureHandler getFigureHandler() {
        return figureHandler;
    }

    public CommandManager getCommandManager() {
        return commandManager;
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
        notifyObservers();
    }

    public void repaint() {
        paintArea.repaint();
    }

    public void save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FILE_NAME)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            ArrayList<Figure> figures = figureHandler.getFigures();
            for (Figure figure : figures) {
                objectOutputStream.writeObject(figure);
            }

            objectOutputStream.close();
        } catch (Exception ignored) { }
    }

    public void load() {
        figureHandler.clear();
        commandManager.clear();

        try (FileInputStream fileInputStream = new FileInputStream(SAVE_FILE_NAME)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Figure> figures = new ArrayList<>();
            while (fileInputStream.available() > 0) {
                Object object = objectInputStream.readObject();
                if (object instanceof Figure) {
                    figures.add((Figure) object);
                }
            }

            figureHandler.setFigures(figures);

            objectInputStream.close();
        } catch (Exception ignored) { }
    }
}
