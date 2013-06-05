package bounce;

import java.awt.Color;

public class DynamicRectangleShape extends RectangleShape {
	private Color currentColor = Color.BLACK;

	// consider applying "Builder" pattern instead of using such a lot of
	// similar constructors.

	public DynamicRectangleShape() {
		super();
	}

	public DynamicRectangleShape(Color color) {
		this();
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(String text) {
		super(text);
	}

	public DynamicRectangleShape(Color color, String text) {
		this(text);
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(int x, int y) {
		super(x, y);
	}

	public DynamicRectangleShape(int x, int y, Color color) {
		this();
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(int x, int y, String text) {
		super(x, y, text);
	}

	public DynamicRectangleShape(int x, int y, Color color, String text) {
		this(x, y, text);
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, Color color) {
		this(x, y, deltaX, deltaY);
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, String text) {
		super(x, y, deltaX, deltaY, text);
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, Color color,
			String text) {
		this(x, y, deltaX, deltaY, text);
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width,
			int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width,
			int height, Color color) {
		this(x, y, deltaX, deltaY, width, height);
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width,
			int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width,
			int height, Color color, String text) {
		this(x, y, deltaX, deltaY, width, height, text);
		this.currentColor = color;
		this.fLastBouncedOffVerticalEdge = true;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width,
			int height, String text, Color color) {
		this(x, y, deltaX, deltaY, width, height, color, text);
	}

	public void paintShape(Painter painter) {
		if (this.fLastBouncedOffVerticalEdge) {
			painter.fillRect(fX, fY, fWidth, fHeight, currentColor);
		} else {
			painter.drawRect(fX, fY, fWidth, fHeight);
		}
	}
}
