package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.component.listener.GroupActionListener;
import com.lalaalal.paint.figure.FigureHandler;

public class GroupMenuItem extends KMenuItem implements Observer {
    private final GroupActionListener.GroupType type;
    private final FigureHandler figureHandler;

    public GroupMenuItem(GroupActionListener.GroupType type, PaintHandler paintHandler) {
        super(type.name());
        this.type = type;
        this.figureHandler = paintHandler.getFigureHandler();
        this.figureHandler.addObserver(this);

        addActionListener(new GroupActionListener(type, paintHandler));
        disable();
    }

    @Override
    public void update() {
        if (isAvailable())
            enable();
        else
            disable();
    }

    public boolean isAvailable() {
        if (type == GroupActionListener.GroupType.Group)
            return figureHandler.hasSelectedFigures() && figureHandler.getSelectedFigures().size() != 1;
        return figureHandler.hasSelectedFigures() && figureHandler.hasSelectedGroup();
    }
}
