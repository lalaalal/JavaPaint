package com.lalaalal.paint.component;

import com.lalaalal.kswing.*;

import java.awt.*;

public class TitleContainer extends KContainer {
    private final KContainer container = new KContainer(MATCH_PARENT, WRAP_CONTENT);

    public TitleContainer(String title) {
        setLayout(KLinearKLayout.Vertical);
        setPadding(0, 0, 0, 0);
        setWidth(WRAP_CONTENT);
        setHeight(WRAP_CONTENT);

        KLabel titleLabel = new KLabel(title);
        titleLabel.setFont(new Font("Sans", Font.PLAIN, 9));
        titleLabel.setTextColor(Color.GRAY);
        titleLabel.setPadding(0, 15, 2, 0);
        titleLabel.setBorder(false);

        container.setLayout(KLinearKLayout.Horizontal);
        container.setPadding(0, 0, 0, 0);

        super.add(titleLabel);
        super.add(container);
    }

    @Override
    public void add(KComponent component) {
        container.add(component);
    }
}
