package com.lalaalal.paint.component;

import com.lalaalal.kswing.*;
import com.lalaalal.paint.PaintHandler;

public class PaintToolBar extends KTabbedPanel {
    public PaintToolBar(PaintHandler paintHandler) {
        KContainer figureTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);
        KButton rectangleButton = new KButton("Rectangle");
        figureTab.add(rectangleButton);

        KContainer colorTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);
        KButton redButton = new KButton("Red");
        colorTab.add(redButton);

        addTab(createHomeTab(paintHandler), "Home");
        addTab(figureTab, "Figure");
        addTab(colorTab, "Color");

        setTab("Home");
    }

    private KContainer createHomeTab(PaintHandler paintHandler) {
        KContainer homeTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);

        TitleContainer commandContainer = new TitleContainer("Command");
        KButton undoButton = new CommandButton(CommandActionListener.CommandType.Undo, paintHandler.getCommandManager());
        KButton redoButton = new CommandButton(CommandActionListener.CommandType.Redo, paintHandler.getCommandManager());
        KVerticalDivider divider = new KVerticalDivider();
        divider.setWidth(0);
        divider.setMargin(0, 10, 0, 10);
        commandContainer.add(undoButton);
        commandContainer.add(redoButton);

        TitleContainer copyPasteContainer = new TitleContainer("Copy & Paste");
        KButton deleteButton = new CopyPasteButton(CopyPasteActionListener.CopyPasteType.Delete, paintHandler);
        KButton copyButton = new CopyPasteButton(CopyPasteActionListener.CopyPasteType.Copy, paintHandler);
        KButton cutButton = new CopyPasteButton(CopyPasteActionListener.CopyPasteType.Cut, paintHandler);
        KButton pasteButton = new CopyPasteButton(CopyPasteActionListener.CopyPasteType.Paste, paintHandler);
        copyPasteContainer.add(deleteButton);
        copyPasteContainer.add(copyButton);
        copyPasteContainer.add(cutButton);
        copyPasteContainer.add(pasteButton);

        homeTab.add(commandContainer);
        homeTab.add(divider);
        homeTab.add(copyPasteContainer);

        return homeTab;
    }
}
