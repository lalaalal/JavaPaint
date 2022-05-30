package com.lalaalal.paint;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class Subject {
    private final ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(@NotNull Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
