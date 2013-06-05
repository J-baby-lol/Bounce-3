package bounce;


public class GemShape extends Shape {

	public GemShape(int x, int y, int deltaX, int deltaY, int width,
			int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
		// TODO Auto-generated constructor stub
	}

	public GemShape(int x, int y, int deltaX, int deltaY, String text) {
		super(x, y, deltaX, deltaY, text);
		// TODO Auto-generated constructor stub
	}

	public GemShape(int x, int y, String text) {
		super(x, y, text);
		// TODO Auto-generated constructor stub
	}

	public GemShape(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Paints a gem shape, depending on the dimensions of the gem shape to be
	 * painted.
	 * 
	 * @see bounce.Shape#paintShape(bounce.Painter)
	 */
	@Override
	public void paintShape(Painter painter) {
		// x and y are coordinates of points.
		// Points are labeled 1~6 clockwise starting from the leftmost.
		int[] x = new int[6];
		int[] y = new int[6];
		boolean isSmall = this.fWidth < 40;

		x[0] = fX;
		x[1] = isSmall ? fX + (int) (fWidth / 2) : fX + 20;
		x[2] = isSmall ? fX + (int) (fWidth / 2) : fX + fWidth - 20;
		x[3] = fX + fWidth;
		x[4] = x[2];
		x[5] = x[1];

		y[0] = fY + (int) (fHeight / 2);
		y[1] = fY;
		y[2] = fY;
		y[3] = y[0];
		y[4] = fY + fHeight;
		y[5] = y[4];

		for (int i = 0; i < 6; i++) {
			painter.drawLine(x[i], y[i], x[(i + 1) % 6], y[(i + 1) % 6]);
		}

		// if (fWidth < 40) {
		// painter.drawLine(fX, , fX
		// + (int) (fWidth / 2), fY);
		// painter.drawLine(fX + (int) (fWidth / 2), fY, fX
		// + (int) (fWidth / 2), fY); // draws a dot
		// painter.drawLine(fX + (int) (fWidth / 2), fY, fX + fWidth, fY
		// + (int) (fHeight / 2));
		// painter.drawLine(fX + fWidth, fY + (int) (fHeight / 2), fX
		// + (int) (fWidth / 2), fY + fHeight);
		// painter.drawLine(fX + (int) (fWidth / 2), fY + fHeight, fX
		// + (int) (fWidth / 2), fY + fHeight); // draws a dot
		// painter.drawLine(fX + (int) (fWidth / 2), fY + fHeight, fX, fY
		// + (int) (fHeight / 2));
		// } else {
		// painter.drawLine(fX, fY + (int) (fHeight / 2), fX + 20, fY);
		// painter.drawLine(fX + 20, fY, fX + fWidth - 20, fY);
		// painter.drawLine(fX + fWidth - 20, fY, fX + fWidth, fY
		// + (int) (fHeight / 2));
		// painter.drawLine(fX + fWidth, fY + (int) (fHeight / 2), fX + fWidth
		// - 20, fY + fHeight);
		// painter.drawLine(fX + fWidth - 20, fY + fHeight, fX + 20, fY
		// + fHeight);
		// painter.drawLine(fX + 20, fY + fHeight, fX, fY
		// + (int) (fHeight / 2));
		// }
	}

	/**
	 * Default constructor that creates a GemShape instance whose instance
	 * variables are set to default values.
	 */
	public GemShape() {
		super();
	}

	/**
	 * Creates a GemShape instance with specified values for instance variables.
	 */
	public GemShape(int x, int y) {
		super(x, y);
	}

	/**
	 * Creates a GemShape instance with specified values for instance variables.
	 */
	public GemShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	/**
	 * Creates a GemShape instance with specified values for instance variables.
	 */
	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

}
