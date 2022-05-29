package com.lalaalal.kswing;

import java.awt.*;
import java.util.HashMap;

public class KToolBar extends KContainer {
    private final KContainer tabContainer = new KContainer(0, 0, MATCH_PARENT, WRAP_CONTENT);
    private final KContainer toolContainer = new KContainer(0, 0, MATCH_PARENT, WRAP_CONTENT);
    private final HashMap<KButton, KContainer> tools = new HashMap<>();

    private final BoxModel TAB_PADDING = new BoxModel(7, 20);

    public KToolBar() {
        super(0, 0, MATCH_PARENT, WRAP_CONTENT);
        init();
    }

    public KToolBar(int x, int y) {
        super(x, y, MATCH_PARENT, WRAP_CONTENT);
        init();
    }

    private void init() {
        setPadding(0, 0, 0, 0);
        setBorder(true);
        tabContainer.setLayout(KLinearKLayout.Horizontal);
        tabContainer.setBorder(true);
        tabContainer.setPadding(0, 20, 0, 0);
        toolContainer.setLayout(KLinearKLayout.Horizontal);
        toolContainer.setBorder(true);
        toolContainer.setPadding(0, 0, 0, 0);

        setLayout(KLinearKLayout.Vertical);
        add(tabContainer);
        add(toolContainer);
    }

    public void addTool(KContainer container, String name) {
        KButton tab = new KButton(name);
        tab.setPadding(TAB_PADDING);
        tab.setBorder(false);
        tab.addActionListener(event -> setTool(tab));

        container.setWidth(KComponent.MATCH_PARENT);
        container.setHeight(KComponent.WRAP_CONTENT);
        container.setLayout(KLinearKLayout.Horizontal);
        tabContainer.add(tab);
        tools.put(tab, container);
    }

    private void setTool(KButton tab) {
        toolContainer.clear();
        toolContainer.add(tools.get(tab));

        for (KButton button : tools.keySet()) {
            if (tab == button) {
                tab.setBackgroundColor(Color.BLACK);
                tab.setTextColor(Color.WHITE);
            } else {
                button.setBackgroundColor(Color.WHITE);
                button.setTextColor(Color.BLACK);
            }

        }

        repaint();
    }

    public void setTool(String name) {
        for (KButton tab : tools.keySet()) {
            if (tab.getText().equals(name))
                setTool(tab);
        }
    }

    @Override
    protected void paintContent(Graphics graphics) {
        super.paintContent(graphics);
    }
}
