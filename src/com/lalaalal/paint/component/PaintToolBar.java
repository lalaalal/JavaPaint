package com.lalaalal.paint.component;

import com.lalaalal.kswing.*;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.ChangeFigureColorCommand;
import com.lalaalal.paint.component.listener.CommandActionListener;
import com.lalaalal.paint.component.listener.CopyPasteActionListener;
import com.lalaalal.paint.component.listener.GroupActionListener;
import com.lalaalal.paint.figure.Figure;

import java.awt.*;

public class PaintToolBar extends KTabbedPanel {
    private static final BoxModel DIVIDER_MARGIN = new BoxModel(0, 10);

    public PaintToolBar(PaintHandler paintHandler) {
        addTab(createHomeTab(paintHandler), "Home");
        addTab(createFigureTab(paintHandler), "Figure");
        addTab(createColorTab(paintHandler), "Color");
        addTab(createAboutTab(), "About");

        setTab("Home");
    }

    private KContainer createHomeTab(PaintHandler paintHandler) {
        KContainer homeTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);

        TitleContainer saveContainer = new TitleContainer("Save & Load");
        KButton saveButton = new KButton("Save");
        saveButton.setBorder(false);
        saveButton.addActionListener(event -> paintHandler.save());
        KButton loadButton = new KButton("Load");
        loadButton.setBorder(false);
        loadButton.addActionListener(event -> paintHandler.load());
        saveContainer.add(saveButton);
        saveContainer.add(loadButton);

        TitleContainer commandContainer = new TitleContainer("Command");
        KButton undoButton = new CommandButton(CommandActionListener.CommandType.Undo, paintHandler.getCommandManager());
        KButton redoButton = new CommandButton(CommandActionListener.CommandType.Redo, paintHandler.getCommandManager());
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

        homeTab.add(saveContainer);
        homeTab.add(new KVerticalDivider(0, DIVIDER_MARGIN));
        homeTab.add(commandContainer);
        homeTab.add(new KVerticalDivider(0, DIVIDER_MARGIN));
        homeTab.add(copyPasteContainer);

        return homeTab;
    }

    private KContainer createFigureTab(PaintHandler paintHandler) {
        KContainer figureTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);

        TitleContainer drawContainer = new TitleContainer("Draw");
        KButton rectangleButton = new DrawButton(Figure.Type.Rectangle, paintHandler);
        KButton ovalButton = new DrawButton(Figure.Type.Oval, paintHandler);
        KButton lineButton = new DrawButton(Figure.Type.Line, paintHandler);
        drawContainer.add(rectangleButton);
        drawContainer.add(ovalButton);
        drawContainer.add(lineButton);

        TitleContainer groupContainer = new TitleContainer("Group");
        KButton groupButton = new GroupButton(GroupActionListener.GroupType.Group, paintHandler);
        KButton ungroupButton = new GroupButton(GroupActionListener.GroupType.Ungroup, paintHandler);
        groupContainer.add(groupButton);
        groupContainer.add(ungroupButton);

        figureTab.add(drawContainer);
        figureTab.add(new KVerticalDivider(0, DIVIDER_MARGIN));
        figureTab.add(groupContainer);

        return figureTab;
    }

    private KContainer createColorTab(PaintHandler paintHandler) {
        KContainer colorTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);

        colorTab.add(createLineColorContainer(paintHandler));
        colorTab.add(new KVerticalDivider(0, DIVIDER_MARGIN));
        colorTab.add(createBackgroundColorContainer(paintHandler));

        return colorTab;
    }

    private TitleContainer createLineColorContainer(PaintHandler paintHandler) {
        TitleContainer lineColorContainer = new TitleContainer("Line(Border) Color");
        KButton blackButton = new ColorButton("Black", Color.BLACK, ChangeFigureColorCommand.Type.Line, paintHandler);
        KButton redButton = new ColorButton("Red", Color.RED, ChangeFigureColorCommand.Type.Line, paintHandler);
        KButton blueButton = new ColorButton("Blue", Color.BLUE, ChangeFigureColorCommand.Type.Line, paintHandler);
        KButton yellowButton = new ColorButton("Yellow", Color.YELLOW, ChangeFigureColorCommand.Type.Line, paintHandler);
        KButton greenButton = new ColorButton("Green", Color.GREEN, ChangeFigureColorCommand.Type.Line, paintHandler);
        lineColorContainer.add(blackButton);
        lineColorContainer.add(redButton);
        lineColorContainer.add(blueButton);
        lineColorContainer.add(yellowButton);
        lineColorContainer.add(greenButton);

        return lineColorContainer;
    }

    private TitleContainer createBackgroundColorContainer(PaintHandler paintHandler) {
        TitleContainer backgroundColorContainer = new TitleContainer("Background Color");
        KButton blackButton = new ColorButton("Black", Color.BLACK, ChangeFigureColorCommand.Type.Background, paintHandler);
        KButton redButton = new ColorButton("Red", Color.RED, ChangeFigureColorCommand.Type.Background, paintHandler);
        KButton blueButton = new ColorButton("Blue", Color.BLUE, ChangeFigureColorCommand.Type.Background, paintHandler);
        KButton yellowButton = new ColorButton("Yellow", Color.YELLOW, ChangeFigureColorCommand.Type.Background, paintHandler);
        KButton greenButton = new ColorButton("Green", Color.GREEN, ChangeFigureColorCommand.Type.Background, paintHandler);
        KButton whiteButton = new ColorButton("White", Color.WHITE, ChangeFigureColorCommand.Type.Background, paintHandler);
        backgroundColorContainer.add(blackButton);
        backgroundColorContainer.add(redButton);
        backgroundColorContainer.add(blueButton);
        backgroundColorContainer.add(yellowButton);
        backgroundColorContainer.add(greenButton);
        backgroundColorContainer.add(whiteButton);

        return backgroundColorContainer;
    }

    private KContainer createAboutTab() {
        KContainer aboutTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);
        KLabel author = new KLabel("이창협 (WRAP_CONTENT)");
        author.setWidth(WRAP_CONTENT);
        author.setHeight(WRAP_CONTENT);
        author.setMargin(0, 0, 0, 10);
        KLabel className = new KLabel("java35 (MATCH_PARENT)");
        className.setWidth(MATCH_PARENT);
        className.setHeight(WRAP_CONTENT);
        className.setMargin(0, 0, 0, 10);
        KLabel date = new KLabel("2022.06.13 (MATCH_PARENT)");
        date.setWidth(MATCH_PARENT);
        date.setHeight(WRAP_CONTENT);

        aboutTab.add(author);
        aboutTab.add(className);
        aboutTab.add(date);

        return aboutTab;
    }
}
