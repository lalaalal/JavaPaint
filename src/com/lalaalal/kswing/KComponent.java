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
	private int width;
	private int height;
	protected int measuredWidth;
	protected int measuredHeight;

	protected KContainer parent = null;

	private boolean isVisible = true;
	protected boolean showBorder = true;

	protected boolean isMeasured = false;
	private Color borderColor = Color.BLACK;
	private Color backgroundColor = Color.WHITE;

	private final ArrayList<KActionListener> actionListeners = new ArrayList<>();
	private final ArrayList<KMouseListener> mouseListeners = new ArrayList<>();

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

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public int getWidth() {
		if (width == WRAP_CONTENT || width == MATCH_PARENT)
			return measuredWidth;
		return width;
	}

	public int getWidthProperty() {
		return width;
	}

	public int getHeightProperty() {
		return height;
	}

	public int getContentWidth() {
		return getWidth() - padding.left - padding.right;
	}

	public void setWidth(int width) {
		this.width = width;

		isMeasured = false;
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

		isMeasured = false;
	}

	public BoxModel getPadding() {
		return padding;
	}

	public void setPadding(int top, int left, int bottom, int right) {
		padding.top = top;
		padding.left = left;
		padding.bottom = bottom;
		padding.right = right;

		isMeasured = false;
	}

	public void setPadding(BoxModel padding) {
		this.padding = padding;

		isMeasured = false;
	}

	public BoxModel getMargin() {
		return margin;
	}

	public void setMargin(int top, int left, int bottom, int right) {
		margin.top = top;
		margin.left = left;
		margin.bottom = bottom;
		margin.right = right;

		isMeasured = false;
	}

	public void setMargin(BoxModel margin) {
		this.margin = margin;

		isMeasured = false;
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

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		if (this.backgroundColor != backgroundColor) {
			this.backgroundColor = backgroundColor;
			repaint();
		}
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void addActionListener(KActionListener listener) {
		this.actionListeners.add(listener);
	}

	public void removeActionListener(KActionListener listener) {
		actionListeners.remove(listener);
	}

	public void addMouseListener(KMouseListener listener) {
		this.mouseListeners.add(listener);
	}

	public void removeMouseListener(KMouseListener listener) {
		mouseListeners.remove(listener);
	}

	protected void processMouseMotionEvent(MouseEvent event) {

	}

	protected void processMouseEvent(MouseEvent event) {
		if (!contains(event.getX(), event.getY()))
			return;

		if (event.getID() == MouseEvent.MOUSE_CLICKED) {
			for (KActionListener listener : actionListeners)
				listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Action"));
		}

		for (KMouseListener listener : mouseListeners) {
			if (event.getID() == MouseEvent.MOUSE_CLICKED)
				listener.mouseClicked(event);
			else if (event.getID() == MouseEvent.MOUSE_RELEASED)
				listener.mouseReleased(event);
			else if (event.getID() == MouseEvent.MOUSE_PRESSED)
				listener.mousePressed(event);
		}
	}

	public boolean contains(int x, int y) {
		return (this.x < x && x < this.x + getWidth())
				&& (this.y < y && y < this.y + getHeight());
	}

	protected void measureSize(Graphics graphics) {
		if (width == MATCH_PARENT && parent != null)
			measuredWidth = parent.getMatchParentWidth();
		if (height == MATCH_PARENT && parent != null)
			measuredHeight = parent.getMatchParentHeight();
	}

	public void paint(Graphics graphics) {
		if (isVisible) {
			if (!isMeasured) {
				measureSize(graphics);
				isMeasured = true;
			}

			paintContent(graphics);
		}
	}

	protected abstract void paintContent(Graphics graphics);

	public void repaint() {
		if (parent != null)
			parent.repaint();
	}
}
