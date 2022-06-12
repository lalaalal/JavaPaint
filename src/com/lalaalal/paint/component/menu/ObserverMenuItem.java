package com.lalaalal.paint.component.menu;

import com.lalaalal.kswing.KMenuItem;
import com.lalaalal.paint.Observer;
import com.lalaalal.paint.PaintHandler;
import com.lalaalal.paint.Subject;

public class ObserverMenuItem extends KMenuItem {
    private final Subject subject;

    public ObserverMenuItem(Subject subject, String text) {
        super(text);
        this.subject = subject;
    }

    public void setObserver(Observer observer) {
        subject.addObserver(observer);
    }
}
