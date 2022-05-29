package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class KAbstractButton extends KLabel {
	private Color normalColor = Color.WHITE;
	private Color hoverColor = Color.GRAY;

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
	public void setBackgroundColor(Color backgroundColor) {
		normalColor = backgroundColor;
		super.setBackgroundColor(normalColor);
	}

	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}

	@Override
	protected void processMouseMotionEvent(MouseEvent event) {
		super.processMouseMotionEvent(event);

		if (contains(event.getX(), event.getY()))
			super.setBackgroundColor(hoverColor);
		else
			super.setBackgroundColor(normalColor);
	}

	@Override
	protected void processMouseEvent(MouseEvent event) {
		super.processMouseEvent(event);

		if (super.contains(event.getX(), event.getY()))
			super.setBackgroundColor(hoverColor);
		else
			super.setBackgroundColor(normalColor);
	}
}
