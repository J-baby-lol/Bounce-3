package bounce;

import java.awt.Color;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging the request in a buffer. The contents of a MockPainter object's log
 * can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Ian Warren, Xuzong Chen
 */
public class MockPainter implements Painter {

	/**
	 * Internal log.
	 */
	private StringBuffer log = new StringBuffer();

	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		log.append("(rectangle " + x + "," + y + "," + width + "," + height
				+ ")");
	}

	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
	}

	/**
	 * Logs the fillRect call.
	 */
	@Override
	public void fillRect(int x, int y, int width, int height, Color color) {
		log.append("(filled-rectangle " + x + "," + y + "," + width + ","
				+ height + "," + color + ")");
	}

	@Override
	public void drawCentredText(String text, int x, int y) {
//		log.append("(text \"" + text + "\"  which should be centred at " + x
//				+ "," + y + ")");
	}

	@Override
	public void translate(int x, int y) {
//		log.append("(translate (" + x + "," + y + "))");
	}
}