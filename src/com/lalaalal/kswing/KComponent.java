package com.lalaalal.kswing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class KComponent {
	public static class BoxModel {
		public int top;
		public int left;
		public int bottom;
		public int right;

		public BoxModel(int value) {
			top = left = bottom = right = value;
		}

		public BoxModel(int vertical, int horizontal) {
			top = bottom = vertical;
			left = right = horizontal;
		}

		public BoxModel(int top, int left, int bottom, int right) {
			this.top = top;
			this.left = left;
			this.bottom = bottom;
			this.right = right;
		}
	}

	public static final int WRAP_CONTENT = Integer.MIN_VALUE;
	public static final int MATCH_PARENT = Integer.MAX_VALUE;
	protected static final int DEFAULT_PADDING = 10;
	protected static final int DEFAULT_MARGIN = 0;

	protected BoxModel padding = new BoxModel(DEFAULT_PADDING);
	protected BoxModel margin = new BoxModel(DEFAULT_MARGIN);

	protected int x, y;
	protected int width;
	protected int height;
	protected int measuredWidth;
	protected int measuredHeight;

	protected KContainer parent = null;

	private boolean isVisible = true;
	protected boolean showBorder = true;
	private final ArrayList<KActionListener> listeners = new ArrayList<>();

	public KComponent(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getWidth() {
		if (width == WRAP_CONTENT || width == MATCH_PARENT)
			return measuredWidth;
		return width;
	}

	public int getContentWidth() {
		return getWidth() - padding.left - padding.right;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		if (height == WRAP_CONTENT || height == MATCH_PARENT)
			return measuredHeight;
		return height;
	}

	public int getContentHeight() {
		return getHeight() - padding.top - padding.bottom;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BoxModel getPadding() {
		return padding;
	}

	public void setPadding(int top, int left, int bottom, int right) {
		padding.top = top;
		padding.left = left;
		padding.bottom = bottom;
		padding.right = right;
	}

	public void setPadding(BoxModel padding) {
		this.padding = padding;
	}

	public BoxModel getMargin() {
		return margin;
	}

	public void setMargin(int top, int left, int bottom, int right) {
		margin.top = top;
		margin.left = left;
		margin.bottom = bottom;
		margin.right = right;
	}

	public void setMargin(BoxModel margin) {
		this.margin = margin;
	}

	public void setParent(KContainer container) {
		parent = container;
	}

	public KContainer getParent() {
		return parent;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean value) {
		isVisible = value;
	}

	public void setBorder(boolean value) {
		showBorder = value;
	}

	public void addActionListener(KActionListener listener) {
		this.listeners.add(listener);
	}

	public void processMouseEvent(MouseEvent event) {
		for (KActionListener listener : listeners) {
			listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Action"));
		}
	}

	public boolean contains(int x, int y) {
		return (this.x <= x && x <= this.x + getWidth())
				&& (this.y <= y && y <= this.y + getHeight());
	}

	private void measureSize(Graphics graphics) {
		if (width == MATCH_PARENT && parent != null)
			measuredWidth = parent.getContentWidth();
		if (height == MATCH_PARENT && parent != null)
			measuredHeight = parent.getContentHeight();

		measureContentSize(graphics);
	}

	protected abstract void measureContentSize(Graphics graphics);

	public void paint(Graphics graphics) {
		if (isVisible) {
			measureSize(graphics);
			paintContent(graphics);
		}
	}

	protected abstract void paintContent(Graphics graphics);

	public void repaint() {
		if (parent != null)
			parent.repaint();
	}
}
