package bounce;



public class OvalShape extends Shape {

	public OvalShape(int x, int y, int deltaX, int deltaY, int width,
			int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
		// TODO Auto-generated constructor stub
	}

	public OvalShape(int x, int y, int deltaX, int deltaY, String text) {
		super(x, y, deltaX, deltaY, text);
		// TODO Auto-generated constructor stub
	}

	public OvalShape(int x, int y, String text) {
		super(x, y, text);
		// TODO Auto-generated constructor stub
	}

	public OvalShape(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintShape(Painter painter) {
		painter.drawOval(fX, fY, fWidth, fHeight);
	}

	public OvalShape() {
		super();
	}

	public OvalShape(int x, int y) {
		super(x, y);
	}

	public OvalShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

}
