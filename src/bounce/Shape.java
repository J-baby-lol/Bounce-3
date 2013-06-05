package bounce;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements a
 * common movement algorithm. Shape subclasses must override method paint() to
 * handle shape-specific painting.
 * 
 * @author Ian Warren, Xuzong Chen
 * 
 */
public abstract class Shape {

	// === Below: Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;

	protected static final int DEFAULT_Y_POS = 0;

	protected static final int DEFAULT_DELTA_X = 5;

	protected static final int DEFAULT_DELTA_Y = 5;

	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;

	// === Below: Instance variables, accessible by subclasses.
	protected int fX;

	protected int fY;

	protected int fDeltaX;

	protected int fDeltaY;

	protected int fWidth;

	protected int fHeight;

	protected boolean fLastBouncedOffVerticalEdge = false;

	protected String fText = null;

	protected NestingShape fParent = null;

	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y,
				DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public Shape(String text) {
		this();
		this.fText = text;
	}

	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH,
				DEFAULT_HEIGHT);
	}

	public Shape(int x, int y, String text) {
		this(x, y);
		this.fText = text;
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public Shape(int x, int y, int deltaX, int deltaY, String text) {
		this(x, y, deltaX, deltaY);
		this.fText = text;
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		fX = x;
		fY = y;
		fDeltaX = deltaX;
		fDeltaY = deltaY;
		fWidth = width;
		fHeight = height;
	}

	public Shape(int x, int y, int deltaX, int deltaY, int width, int height,
			String text) {
		this(x, y, deltaX, deltaY, width, height);
		this.fText = text;
	}

	/**
	 * Moves this Shape object within the specified bounds. On hitting a
	 * boundary the Shape instance bounces off and back into the two-
	 * dimensional world.
	 * 
	 * @param width
	 *            width of two-dimensional world.
	 * @param height
	 *            height of two-dimensional world.
	 */
	public void move(int width, int height) {
		int nextX = fX + fDeltaX;
		int nextY = fY + fDeltaY;

		if (nextY <= 0) {
			nextY = 0;
			fDeltaY = -fDeltaY;
			fLastBouncedOffVerticalEdge = false;
		} else if (nextY + fHeight >= height) {
			nextY = height - fHeight;
			fDeltaY = -fDeltaY;
			fLastBouncedOffVerticalEdge = false;
		}

		if (nextX <= 0) {
			nextX = 0;
			fDeltaX = -fDeltaX;
			fLastBouncedOffVerticalEdge = true;
		} else if (nextX + fWidth >= width) {
			nextX = width - fWidth;
			fDeltaX = -fDeltaX;
			fLastBouncedOffVerticalEdge = true;
		}
		// Note:
		// =====
		// Since the above if-block for nextX comes subsequent to that for
		// nextY,
		// bounces off BOTH edges will be considered to be off the
		// x-related vertical edges.

		fX = nextX;
		fY = nextY;
	}

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 * <p>
	 * This is a hook in the Template Method {@link paintWithText}.
	 * 
	 * @param painter
	 *            the Painter object used for drawing.
	 */
	protected abstract void paintShape(Painter painter);

	// Note for the above paintShape method:
	// =====================================
	// Eclipse's "refactoring" rename function is applied when renaming this
	// method from "paint".

	/**
	 * Paints the text (if any) for this shape at the right location.
	 * <p>
	 * This is another hook in the Template Method paintWithText.
	 */
	protected void paintText(Painter painter) {
		painter.drawCentredText(fText, xCentre(), yCentre());
	}

	/**
	 * This is a Template Method. It is intended for users of the Shape class
	 * and subclasses to call.
	 */
	public void paint(Painter painter) {
		// boolean isNested = parent() != null;
		// if (isNested) {
		// fX += parent().x();
		// fY += parent().y();
		// }
		paintShape(painter); // hook: down-inheritance-hierarchy call.
		paintText(painter); // hook: self call.
		// if (isNested) {
		// fX -= parent().x();
		// fY -= parent().y();
		// }
	}

	/**
	 * @return this Shape object's x position.
	 */
	public int x() {
		return fX;
	}

	/**
	 * @return this Shape object's y position.
	 */
	public int y() {
		return fY;
	}

	/**
	 * @return this Shape object's speed and direction.
	 */
	public int deltaX() {
		return fDeltaX;
	}

	/**
	 * @return this Shape object's speed and direction.
	 */
	public int deltaY() {
		return fDeltaY;
	}

	/**
	 * @return this Shape's width.
	 */
	public int width() {
		return fWidth;
	}

	/**
	 * @return this Shape's height.
	 */
	public int height() {
		return fHeight;
	}

	/**
	 * @return the x-coordinate of the centre of the shape.
	 */
	public int xCentre() {
		return (int) (fX + fWidth / 2);
	}

	/**
	 * @return the y-coordinate of the centre of the shape.
	 */
	public int yCentre() {
		return (int) (fY + fHeight / 2);
	}

	/**
	 * @return the NestingShape under which this Shape is nested. null is
	 *         returned if this Shape is not nested under any NestingShape.
	 */
	public NestingShape parent() {
		return fParent;
	}

	/**
	 * @return a List<Shape> object. Ordered from the root Shape to the leaf
	 *         Shape in the nesting hierarchy of a NestingShape.
	 */
	public List<Shape> path() {
		List<Shape> list, parentList;
		list = new ArrayList<Shape>();
		list.add(this);
		if (parent() == null) {
			return list;
		} else {
			parentList = parent().path();
			if (parentList.addAll(list)) {
				return parentList;
			} else {
				// should never happen.
				throw new RuntimeException();
			}
		}
	}
	
	public String text(){
		return this.fText;
	}

	/**
	 * Returns a String whose value is the fully qualified name of this class of
	 * object. E.g., when called on a RectangleShape instance, this method will
	 * return "bounce.RectangleShape".
	 */
	public String toString() {
		return getClass().getName();
	}
}
