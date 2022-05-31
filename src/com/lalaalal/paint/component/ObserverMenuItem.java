package com.lalaalal.paint.component;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.Subject;

public class ObserverMenuItem extends KMenuItem {
    private final PaintHandler paintHandler;
    private final Subject subject;
    private Observer observer;

    public ObserverMenuItem(PaintHandler paintHandler, Subject subject, String text) {
        super(text);
        this.paintHandler = paintHandler;
        this.subject = subject;
    }

    public void setObserver(Observer observer) {
        subject.addObserver(observer);
    }
}
