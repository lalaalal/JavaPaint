package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class KAbstractButton extends KLabel {
	private Color backgroundColor = Color.WHITE;

	public KAbstractButton(String text) {
		super(text);
	}

	public KAbstractButton(int width, int height, String text) {
		super(width, height, text);
	}

	public KAbstractButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		repaint();
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	protected void processMouseMotionEvent(MouseEvent event) {
		super.processMouseMotionEvent(event);

		if (contains(event.getX(), event.getY()))
			setBackgroundColor(Color.GRAY);
		else
			setBackgroundColor(Color.WHITE);
	}

	@Override
	protected void paintContent(Graphics graphics) {
		graphics.setColor(getBackgroundColor());
		graphics.fillRect(x, y, getWidth(), getHeight());

		super.paintContent(graphics);
	}
}
