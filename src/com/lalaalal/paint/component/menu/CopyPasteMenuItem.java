package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.component.listener.CopyPasteActionListener;
import com.lalaalal.paint.figure.FigureHandler;

public class CopyPasteMenuItem extends KMenuItem implements Observer {
    private final CopyPasteActionListener.CopyPasteType type;
    private final FigureHandler figureHandler;

    public CopyPasteMenuItem(CopyPasteActionListener.CopyPasteType type, PaintHandler paintHandler) {
        super(type.name());

        this.type = type;
        this.figureHandler = paintHandler.getFigureHandler();

        addActionListener(new CopyPasteActionListener(type, paintHandler));

        paintHandler.getFigureHandler().addObserver(this);
        disable();
    }

    @Override
    public void update() {
        if (isAvailable())
            enable();
        else
            disable();
    }

    private boolean isAvailable() {
        if (type == CopyPasteActionListener.CopyPasteType.Paste)
            return figureHandler.hasCopiedFigures();
        return figureHandler.hasSelectedFigures();
    }
}
