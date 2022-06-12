package com.lalaalal.paint.component;

import com.lalaalal.kswing.*;

public class PaintToolBar extends KTabbedPanel {
    public PaintToolBar() {
        KContainer homeTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);
        KButton undoButton = new KButton("Undo");
        undoButton.setBorder(false);
        KButton redoButton = new KButton("Redo");
        redoButton.setBorder(false);
        KVerticalDivider divider = new KVerticalDivider();
        divider.setWidth(0);
        divider.setMargin(0, 10, 0, 10);
        homeTab.add(undoButton);
        homeTab.add(redoButton);
        homeTab.add(divider);

        KContainer figureTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);
        KButton rectangleButton = new KButton("Rectangle");
        figureTab.add(rectangleButton);

        KContainer colorTab = new KContainer(MATCH_PARENT, WRAP_CONTENT);
        KButton redButton = new KButton("Red");
        colorTab.add(redButton);

        addTab(homeTab, "Home");
        addTab(figureTab, "Figure");
        addTab(colorTab, "Color");

        setTab("Home");
    }
}
