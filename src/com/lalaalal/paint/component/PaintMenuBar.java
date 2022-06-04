package com.lalaalal.paint.component;

import com.lalaalal.kswing.*;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.UndoMenuItem;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.CutFiguresCommand;
import com.lalaalal.paint.command.DeleteFigureCommand;
import com.lalaalal.paint.command.PasteFiguresCommand;
import com.lalaalal.paint.figure.Figure;

public class PaintMenuBar extends KMenuBar {

    public PaintMenuBar(PaintHandler paintHandler) {
        KMenu fileMenu = new KMenu("File");
        KMenuItem saveMenuItem = new KMenuItem("Save");
        KMenuItem saveAsMenuItem = new KMenuItem("Save As");
        KMenuItem loadMenuItem = new KMenuItem("Load");
        fileMenu.addMenuItem(saveMenuItem);
        fileMenu.addMenuItem(saveAsMenuItem);
        fileMenu.addMenuItem(loadMenuItem);

        KMenu viewMenu = new KMenu("View");
        KMenuItem showGroupBorderMenuItem = new KCheckableMenuItem("Show Group Border");

        viewMenu.addMenuItem(showGroupBorderMenuItem);

        add(fileMenu);
        add(createEditMenu(paintHandler));
        add(viewMenu);
    }

    private KMenu createEditMenu(PaintHandler paintHandler) {
        KMenu editMenu = new KMenu("Edit");
        KMenuItem undoMenuItem = new UndoMenuItem(paintHandler.getCommandManager());
        KMenuItem redoMenuItem = new RedoMenuItem(paintHandler.getCommandManager());
        ObserverMenuItem deleteMenuItem = new ObserverMenuItem(paintHandler.getFigureHandler(), "Delete");
        ObserverMenuItem copyMenuItem = new ObserverMenuItem(paintHandler.getFigureHandler(), "Copy");
        ObserverMenuItem cutMenuItem = new ObserverMenuItem(paintHandler.getFigureHandler(), "Cut");
        ObserverMenuItem pasteMenuItem = new ObserverMenuItem(paintHandler.getFigureHandler(), "Paste");

        deleteMenuItem.addActionListener(event -> {
            Command command = new DeleteFigureCommand(paintHandler);
            paintHandler.executeCommand(command);
        });
        deleteMenuItem.setObserver(() -> {
            if (paintHandler.getFigureHandler().hasSelectedFigures())
                deleteMenuItem.enable();
            else deleteMenuItem.disable();
        });
        deleteMenuItem.disable();
        copyMenuItem.addActionListener(event -> paintHandler.getFigureHandler().copySelectedFigures());
        copyMenuItem.setObserver(() -> {
            if (paintHandler.getFigureHandler().hasSelectedFigures())
                copyMenuItem.enable();
            else copyMenuItem.disable();
        });
        copyMenuItem.disable();
        cutMenuItem.addActionListener(event -> {
            Command command = new CutFiguresCommand(paintHandler);
            paintHandler.executeCommand(command);
        });
        cutMenuItem.setObserver(() -> {
            if (paintHandler.getFigureHandler().hasSelectedFigures())
                cutMenuItem.enable();
            else cutMenuItem.disable();
        });
        cutMenuItem.disable();
        pasteMenuItem.addActionListener(event -> {
            Command command = new PasteFiguresCommand(paintHandler);
            paintHandler.executeCommand(command);
        });
        pasteMenuItem.setObserver(() -> {
            if (paintHandler.getFigureHandler().hasCopiedFigures())
                pasteMenuItem.enable();
            else pasteMenuItem.disable();
        });
        pasteMenuItem.disable();

        KMenuItem groupMenuItem = new GroupMenuItem(paintHandler);
        KMenuItem ungroupMenuItem = new UnGroupMenuItem(paintHandler);

        KSubMenu drawSubMenu = new KSubMenu("Draw");
        KMenuItem rectangleMenuItem = new DrawMenuItem(Figure.Type.Rectangle, paintHandler);
        KMenuItem ovalMenuItem = new DrawMenuItem(Figure.Type.Oval, paintHandler);
        KMenuItem lineMenuItem = new DrawMenuItem(Figure.Type.Line, paintHandler);
        drawSubMenu.addMenuItem(rectangleMenuItem);
        drawSubMenu.addMenuItem(ovalMenuItem);
        drawSubMenu.addMenuItem(lineMenuItem);

        KSubMenu lineColorSubMenu = new KSubMenu("Line(Border) Color");
        KMenuItem blackMenuItem = new KMenuItem("Black");
        KMenuItem redMenuItem = new KMenuItem("Red");
        KMenuItem blueMenuItem = new KMenuItem("Blue");
        KMenuItem yellowMenuItem = new KMenuItem("Yellow");
        KMenuItem greenMenuItem = new KMenuItem("Green");
        KMenuItem otherMenuItem = new KMenuItem("Other");
        lineColorSubMenu.addMenuItem(blackMenuItem);
        lineColorSubMenu.addMenuItem(redMenuItem);
        lineColorSubMenu.addMenuItem(blueMenuItem);
        lineColorSubMenu.addMenuItem(yellowMenuItem);
        lineColorSubMenu.addMenuItem(greenMenuItem);
        lineColorSubMenu.addDivider();
        lineColorSubMenu.addMenuItem(otherMenuItem);

        editMenu.addMenuItem(undoMenuItem);
        editMenu.addMenuItem(redoMenuItem);
        editMenu.addDivider();
        editMenu.addMenuItem(deleteMenuItem);
        editMenu.addMenuItem(copyMenuItem);
        editMenu.addMenuItem(cutMenuItem);
        editMenu.addMenuItem(pasteMenuItem);
        editMenu.addDivider();
        editMenu.addMenuItem(groupMenuItem);
        editMenu.addMenuItem(ungroupMenuItem);
        editMenu.addMenuItem(drawSubMenu);
        editMenu.addMenuItem(lineColorSubMenu);


        return editMenu;
    }
}
