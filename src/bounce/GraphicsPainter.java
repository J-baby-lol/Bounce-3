package bounce;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Ian Warren
 */
public class GraphicsPainter implements Painter {
	// Delegate object.

	private Graphics g;

	private int x = 0;

	private int y = 0;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this.g = g;
	}

	/**
	 * @see bounce.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		g.drawOval(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * @see bounce.Painter#fillRect(int, int, int, int, java.awt.Color)
	 */
	public void fillRect(int x, int y, int width, int height, Color color) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
	}

	/**
	 * @see bounce.Painter#drawCentredText(java.lang.String, int, int)
	 */
	public void drawCentredText(String text, int x, int y) {
		if (text != null) {
			FontMetrics fontMetrics = g.getFontMetrics();
			int width = fontMetrics.stringWidth(text);
			int x_mod = (int) (x - width / 2);
			int ascent = fontMetrics.getAscent();
			int descent = fontMetrics.getDescent();
			int y_mod = (int) (y + (ascent - descent) / 2);
			g.drawString(text, x_mod, y_mod);
		} else {
			// do nothing.
		}
	}

	@Override
	public void translate(int x, int y) {
		// this.x += x;
		// this.y += y;
		// g.translate(this.x, this.y);
		g.translate(x, y);
	}
}
