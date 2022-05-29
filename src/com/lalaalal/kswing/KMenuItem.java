package com.lalaalal.kswing;

public class KMenuItem extends KAbstractButton {
    public static final BoxModel DEFAULT_MENU_ITEM_PADDING = new BoxModel(5, 30, 5, 10);

    public KMenuItem(String text) {
        super(text);
        setPadding(DEFAULT_MENU_ITEM_PADDING);
        setTextAlignment(Alignment.Left);
        setWidth(MATCH_PARENT);
        setBorder(false);
    }
}
