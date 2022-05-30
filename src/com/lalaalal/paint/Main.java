package com.lalaalal.paint;

import com.lalaalal.kswing.*;

public class Main {
    public static void main(String[] args) {
        KAdapterFrame frameAdapter = new KAdapterFrame();
        KFrame frame = new PaintFrame(frameAdapter);
        frameAdapter.setKFrame(frame);
    }
}
