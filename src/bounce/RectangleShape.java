package bounce;


/**
 * Class to represent a simple rectangle.
 * 
 * @author Ian Warren
 */
public class RectangleShape extends Shape {

	/**
	 * Default constructor that creates a RectangleShape instance whose instance
	 * variables are set to default values.
	 */
	public RectangleShape() {
		super();
	}

	/**
	 * @author Xuzong Chen
	 */
	public RectangleShape(String text) {
		super(text);
	}

	/**
	 * @author Xuzong Chen
	 */
	public RectangleShape(int x, int y) {
		super(x, y);
	}

	/**
	 * @author Xuzong Chen
	 */
	public RectangleShape(int x, int y, String text) {
		super(x, y, text);
	}

	/**
	 * Creates a RectangleShape instance with specified values for instance
	 * variables.
	 */
	public RectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	/**
	 * @author Xuzong Chen
	 */
	public RectangleShape(int x, int y, int deltaX, int deltaY, String text) {
		super(x, y, deltaX, deltaY, text);
	}

	/**
	 * Creates a RectangleShape instance with specified values for instance
	 * variables.
	 */
	public RectangleShape(int x, int y, int deltaX, int deltaY, int width,
			int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

	/**
	 * @author Xuzong Chen
	 */
	public RectangleShape(int x, int y, int deltaX, int deltaY, int width,
			int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}

	/**
	 * Paints this RectangleShape object using the supplied Painter object.
	 */
	public void paintShape(Painter painter) {
		painter.drawRect(fX, fY, fWidth, fHeight);
	}

}
