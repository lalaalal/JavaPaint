package com.lalaalal.paint.component;

import com.lalaalal.kswing.KButton;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.component.listener.CopyPasteActionListener;
import com.lalaalal.paint.figure.FigureHandler;

public class CopyPasteButton extends KButton implements Observer {
    private final CopyPasteActionListener.CopyPasteType type;
    private final FigureHandler figureHandler;

    public CopyPasteButton(CopyPasteActionListener.CopyPasteType type, PaintHandler paintHandler) {
        super(type.name());
        setBorder(false);

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
