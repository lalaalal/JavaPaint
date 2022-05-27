package com.lalaalal.kswing;

import java.util.ArrayList;

public interface KLayoutManager {
    void adjustComponents(ArrayList<KComponent> components, KContainer container);

    int measureWidth(ArrayList<KComponent> components, KContainer container);
    int measureHeight(ArrayList<KComponent> components, KContainer container);
}
