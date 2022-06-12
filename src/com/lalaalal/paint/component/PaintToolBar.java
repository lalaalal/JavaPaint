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

    public PaintToolBar(PaintHandler paintHandler) {

        addTab(createHomeTab(paintHandler), "Home");
        addTab(createFigureTab(paintHandler), "Figure");
        addTab(createColorTab(paintHandler), "Color");

        setTab("Home");
    }

    private KContainer createHomeTab(PaintHandler paintHandler) {
        KVerticalDivider divider = new KVerticalDivider(0);
        divider.setMargin(0, 10, 0, 10);

        KContainer homeTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);

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

        homeTab.add(commandContainer);
        homeTab.add(divider);
        homeTab.add(copyPasteContainer);

        return homeTab;
    }

    private KContainer createFigureTab(PaintHandler paintHandler) {
        KVerticalDivider divider = new KVerticalDivider(0);
        divider.setMargin(0, 10, 0, 10);

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
        figureTab.add(divider);
        figureTab.add(groupContainer);

        return figureTab;
    }

    private KContainer createColorTab(PaintHandler paintHandler) {
        KVerticalDivider divider = new KVerticalDivider(0);
        divider.setMargin(0, 10, 0, 10);

        KContainer colorTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);

        colorTab.add(createLineColorContainer(paintHandler));
        colorTab.add(divider);
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
}
