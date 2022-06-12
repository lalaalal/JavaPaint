package com.lalaalal.paint.component;

import com.lalaalal.kswing.KButton;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.figure.FigureHandler;

public class GroupButton extends KButton implements Observer {
    private final GroupActionListener.GroupType type;
    private final FigureHandler figureHandler;

    public GroupButton(GroupActionListener.GroupType type, PaintHandler paintHandler) {
        super(type.name());

        setBorder(false);

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
