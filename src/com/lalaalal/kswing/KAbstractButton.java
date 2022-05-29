package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class KAbstractButton extends KLabel {

	public KAbstractButton(String text) {
		super(text);
	}

	public KAbstractButton(int width, int height, String text) {
		super(width, height, text);
	}

	public KAbstractButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

	@Override
	protected void processMouseMotionEvent(MouseEvent event) {
		super.processMouseMotionEvent(event);

		if (contains(event.getX(), event.getY()))
			setBackgroundColor(Color.GRAY);
		else
			setBackgroundColor(Color.WHITE);
	}
}
