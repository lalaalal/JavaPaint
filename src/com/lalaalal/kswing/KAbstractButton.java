package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class KAbstractButton extends KLabel {
	protected Color normalColor = Color.WHITE;
	protected Color hoverColor = Color.GRAY;

	protected Color enableTextColor = Color.BLACK;
	protected Color disableTextColor = Color.GRAY;

	private boolean isEnabled = true;

	public KAbstractButton(String text) {
		super(text);
	}

	public KAbstractButton(int width, int height, String text) {
		super(width, height, text);
	}

	public KAbstractButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void enable() {
		isEnabled = true;
		setTextColor(enableTextColor);
	}

	public void disable() {
		isEnabled = false;
		setTextColor(disableTextColor);
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

		if (contains(event.getX(), event.getY()) && isEnabled)
			super.setBackgroundColor(hoverColor);
		else
			super.setBackgroundColor(normalColor);
	}

	@Override
	protected void processMouseEvent(MouseEvent event) {
		if (isEnabled)
			super.processMouseEvent(event);

		if (super.contains(event.getX(), event.getY()) && isEnabled)
			super.setBackgroundColor(hoverColor);
		else
			super.setBackgroundColor(normalColor);
	}
}
