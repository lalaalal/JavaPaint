package com.lalaalal.paint;

import com.lalaalal.kswing.*;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.GroupFiguresCommand;
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


        KMenu editMenu = new KMenu("Edit");
        KMenuItem undoMenuItem = new UndoMenuItem(paintHandler.getCommandManager());
        KMenuItem redoMenuItem = new RedoMenuItem(paintHandler.getCommandManager());
        KMenuItem copyMenuItem = new KMenuItem("Copy");
        KMenuItem cutMenuItem = new KMenuItem("Cut");
        KMenuItem pasteMenuItem = new KMenuItem("Paste");

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
        editMenu.addMenuItem(copyMenuItem);
        editMenu.addMenuItem(cutMenuItem);
        editMenu.addMenuItem(pasteMenuItem);
        editMenu.addDivider();
        editMenu.addMenuItem(groupMenuItem);
        editMenu.addMenuItem(ungroupMenuItem);
        editMenu.addMenuItem(drawSubMenu);
        editMenu.addMenuItem(lineColorSubMenu);


        KMenu viewMenu = new KMenu("View");
        KMenuItem showGroupBorderMenuItem = new KCheckableMenuItem("Show Group Border");

        viewMenu.addMenuItem(showGroupBorderMenuItem);

        add(fileMenu);
        add(editMenu);
        add(viewMenu);
    }
}
