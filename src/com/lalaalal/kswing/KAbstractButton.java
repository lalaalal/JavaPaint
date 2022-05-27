package com.lalaalal.kswing;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
}
