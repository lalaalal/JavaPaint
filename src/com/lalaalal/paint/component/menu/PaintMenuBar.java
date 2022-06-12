package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.*;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.*;
import com.lalaalal.paint.component.listener.CommandActionListener;
import com.lalaalal.paint.component.listener.CopyPasteActionListener;
import com.lalaalal.paint.component.listener.GroupActionListener;
import com.lalaalal.paint.figure.Figure;

import java.awt.*;

public class PaintMenuBar extends KMenuBar {

    public PaintMenuBar(PaintHandler paintHandler) {
        KMenu fileMenu = new KMenu("File");
        KMenuItem saveMenuItem = new KMenuItem("Save");
        saveMenuItem.addActionListener(event -> paintHandler.save());
        KMenuItem loadMenuItem = new KMenuItem("Load");
        loadMenuItem.addActionListener(event -> paintHandler.load());

        fileMenu.addMenuItem(saveMenuItem);
        fileMenu.addMenuItem(loadMenuItem);

        KMenu viewMenu = new KMenu("View");
        KCheckableMenuItem showGroupBorderMenuItem = new KCheckableMenuItem("Show Group Border");
        showGroupBorderMenuItem.addActionListener(event -> {
            paintHandler.getFigureHandler().setShowGroupBorder(showGroupBorderMenuItem.isChecked());
        });

        viewMenu.addMenuItem(showGroupBorderMenuItem);

        add(fileMenu);
        add(createEditMenu(paintHandler));
        add(viewMenu);
    }

    private KMenu createEditMenu(PaintHandler paintHandler) {
        KMenu editMenu = new KMenu("Edit");
        KMenuItem undoMenuItem = new CommandMenuItem(CommandActionListener.CommandType.Undo, paintHandler.getCommandManager());
        KMenuItem redoMenuItem = new CommandMenuItem(CommandActionListener.CommandType.Redo, paintHandler.getCommandManager());
        KMenuItem deleteMenuItem = new CopyPasteMenuItem(CopyPasteActionListener.CopyPasteType.Delete, paintHandler);
        KMenuItem copyMenuItem = new CopyPasteMenuItem(CopyPasteActionListener.CopyPasteType.Copy, paintHandler);
        KMenuItem cutMenuItem = new CopyPasteMenuItem(CopyPasteActionListener.CopyPasteType.Cut, paintHandler);
        KMenuItem pasteMenuItem = new CopyPasteMenuItem(CopyPasteActionListener.CopyPasteType.Paste, paintHandler);

        KMenuItem groupMenuItem = new GroupMenuItem(GroupActionListener.GroupType.Group, paintHandler);
        KMenuItem ungroupMenuItem = new GroupMenuItem(GroupActionListener.GroupType.Ungroup, paintHandler);

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
        editMenu.addMenuItem(createDrawSubMenu(paintHandler));
        editMenu.addMenuItem(createLineColorSubMenu(paintHandler));
        editMenu.addMenuItem(createBackgroundColorSubMenu(paintHandler));

        return editMenu;
    }

    private KSubMenu createDrawSubMenu(PaintHandler paintHandler) {
        KSubMenu drawSubMenu = new KSubMenu("Draw");
        KMenuItem rectangleMenuItem = new DrawMenuItem(Figure.Type.Rectangle, paintHandler);
        KMenuItem ovalMenuItem = new DrawMenuItem(Figure.Type.Oval, paintHandler);
        KMenuItem lineMenuItem = new DrawMenuItem(Figure.Type.Line, paintHandler);
        drawSubMenu.addMenuItem(rectangleMenuItem);
        drawSubMenu.addMenuItem(ovalMenuItem);
        drawSubMenu.addMenuItem(lineMenuItem);

        return drawSubMenu;
    }

    private KSubMenu createLineColorSubMenu(PaintHandler paintHandler) {
        KSubMenu lineColorSubMenu = new ColorSubMenu("Line(Border) Color", paintHandler.getFigureHandler());
        KMenuItem blackMenuItem = new ColorMenuItem("Black", Color.BLACK, ChangeFigureColorCommand.Type.Line, paintHandler);
        KMenuItem redMenuItem = new ColorMenuItem("Red", Color.RED, ChangeFigureColorCommand.Type.Line, paintHandler);
        KMenuItem blueMenuItem = new ColorMenuItem("Blue", Color.BLUE, ChangeFigureColorCommand.Type.Line, paintHandler);
        KMenuItem yellowMenuItem = new ColorMenuItem("Yellow", Color.YELLOW, ChangeFigureColorCommand.Type.Line, paintHandler);
        KMenuItem greenMenuItem = new ColorMenuItem("Green", Color.GREEN, ChangeFigureColorCommand.Type.Line, paintHandler);
        lineColorSubMenu.addMenuItem(blackMenuItem);
        lineColorSubMenu.addMenuItem(redMenuItem);
        lineColorSubMenu.addMenuItem(blueMenuItem);
        lineColorSubMenu.addMenuItem(yellowMenuItem);
        lineColorSubMenu.addMenuItem(greenMenuItem);

        return lineColorSubMenu;
    }

    private KSubMenu createBackgroundColorSubMenu(PaintHandler paintHandler) {
        KSubMenu backgroundColorSubMenu = new ColorSubMenu("Background Color", paintHandler.getFigureHandler());
        KMenuItem blackMenuItem = new ColorMenuItem("Black", Color.BLACK, ChangeFigureColorCommand.Type.Background, paintHandler);
        KMenuItem redMenuItem = new ColorMenuItem("Red", Color.RED, ChangeFigureColorCommand.Type.Background, paintHandler);
        KMenuItem blueMenuItem = new ColorMenuItem("Blue", Color.BLUE, ChangeFigureColorCommand.Type.Background, paintHandler);
        KMenuItem yellowMenuItem = new ColorMenuItem("Yellow", Color.YELLOW, ChangeFigureColorCommand.Type.Background, paintHandler);
        KMenuItem greenMenuItem = new ColorMenuItem("Green", Color.GREEN, ChangeFigureColorCommand.Type.Background, paintHandler);
        KMenuItem whiteMenuItem = new ColorMenuItem("White", Color.WHITE, ChangeFigureColorCommand.Type.Background, paintHandler);
        backgroundColorSubMenu.addMenuItem(blackMenuItem);
        backgroundColorSubMenu.addMenuItem(redMenuItem);
        backgroundColorSubMenu.addMenuItem(blueMenuItem);
        backgroundColorSubMenu.addMenuItem(yellowMenuItem);
        backgroundColorSubMenu.addMenuItem(greenMenuItem);
        backgroundColorSubMenu.addMenuItem(whiteMenuItem);

        return backgroundColorSubMenu;
    }
}
