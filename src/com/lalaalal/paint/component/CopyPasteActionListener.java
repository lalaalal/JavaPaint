package com.lalaalal.paint.component;

import com.lalaalal.kswing.KActionListener;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.command.Command;
import com.lalaalal.paint.command.CutFiguresCommand;
import com.lalaalal.paint.command.DeleteFigureCommand;
import com.lalaalal.paint.command.PasteFiguresCommand;

import java.awt.event.ActionEvent;

public class CopyPasteActionListener implements KActionListener {
    public enum CopyPasteType {
        Delete, Copy, Cut, Paste
    }

    private final CopyPasteType type;
    private final PaintHandler paintHandler;

    public CopyPasteActionListener(CopyPasteType copyPasteType, PaintHandler paintHandler) {
        this.type = copyPasteType;
        this.paintHandler = paintHandler;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Command command = null;
        if (type == CopyPasteType.Delete)
            command = new DeleteFigureCommand(paintHandler);
        else if (type == CopyPasteType.Copy)
            paintHandler.getFigureHandler().copySelectedFigures();
        else if (type == CopyPasteType.Cut)
            command = new CutFiguresCommand(paintHandler);
        else
            command = new PasteFiguresCommand(paintHandler);
        paintHandler.executeCommand(command);
    }
}
