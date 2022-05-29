package com.lalaalal.kswing;

import java.awt.*;

public class KLabel extends KComponent {
	public enum Alignment {
		Left, Center
	}

	private String text;
	private int textX;
	private int textY;

	private Color textColor = Color.BLACK;
	private Color borderColor = Color.BLACK;
	private Color backgroundColor = Color.WHITE;
	private Font font = new Font("Sans", Font.PLAIN, 12);

	private Alignment alignment = Alignment.Left;

	public KLabel(String text) {
		super(0, 0, WRAP_CONTENT, WRAP_CONTENT);
		this.text = text;
	}

	public KLabel(int width, int height, String text) {
		super(0, 0, width, height);
		this.text = text;
	}

	public KLabel(int x, int y, int width, int height, String text) {
		super(x, y, width, height);
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
		repaint();
	}

	public String getText() {
		return text;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		repaint();
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void setTextAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	@Override
	protected void measureContentSize(Graphics graphics) {
		graphics.setFont(font);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int textWidth = fontMetrics.stringWidth(text);
		int textHeight = fontMetrics.getHeight();
		int textAscent = fontMetrics.getAscent();

		textX = x + measureHorizontalInterval(textWidth);
		textY = y + measureVerticalInterval(textHeight) + textAscent;
	}

	protected int measureHorizontalInterval(int textWidth) {
		int horizontalInterval = padding.left;
		if (width == WRAP_CONTENT) {
			measuredWidth = textWidth + padding.left + padding.right;
		} else if (alignment == Alignment.Center) {
			horizontalInterval = (getWidth() - textWidth) / 2;
		}
		return horizontalInterval;
	}

	protected int measureVerticalInterval(int textHeight) {
		int verticalInterval = padding.top;
		if (height == WRAP_CONTENT) {
			measuredHeight = textHeight + padding.top + padding.bottom;
		} else {
			verticalInterval = (getHeight() - textHeight) / 2;
		}
		return verticalInterval;
	}

	@Override
	protected void paintContent(Graphics graphics) {
		graphics.setColor(backgroundColor);
		graphics.fillRect(x, y, getWidth(), getHeight());

		graphics.setColor(textColor);
		graphics.drawString(text, textX, textY);

		if (showBorder) {
			graphics.setColor(borderColor);
			graphics.drawRect(x, y, getWidth(), getHeight());
		}
	}
}
