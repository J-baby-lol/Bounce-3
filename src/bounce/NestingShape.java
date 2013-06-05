package bounce;

import java.util.ArrayList;
import java.util.List;

public class NestingShape extends Shape {

	public NestingShape() {
		super();
	}

	public NestingShape(String text) {
		super(text);
	}

	public NestingShape(int x, int y) {
		super(x, y);
	}

	public NestingShape(int x, int y, String text) {
		super(x, y, text);
	}

	public NestingShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	public NestingShape(int x, int y, int deltaX, int deltaY, String text) {
		super(x, y, deltaX, deltaY, text);
	}

	public NestingShape(int x, int y, int deltaX, int deltaY, int width,
			int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

	public NestingShape(int x, int y, int deltaX, int deltaY, int width,
			int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}

	//
	//
	// ===== interesting stuff below =====
	//
	//

	private List<Shape> shapeList = new ArrayList<Shape>();

	/**
	 * Moves the NestingShape itself as well as the Shapes nested inside it.
	 * 
	 * @see bounce.Shape#move(int, int)
	 */
	@Override
	public void move(int width, int height) {
		super.move(width, height);
		for (Shape shape : shapeList) {
			shape.move(fWidth, fHeight);
		}
	}

	/**
	 * Paints the NestingShape itself as well as the Shapes nested inside it.
	 * 
	 * @see bounce.Shape#paintShape(bounce.Painter)
	 */
	@Override
	protected void paintShape(Painter painter) {
		painter.drawRect(fX, fY, fWidth, fHeight);
		painter.translate(fX, fY);
		for (Shape shape : shapeList) {
			shape.paint(painter);
		}
		painter.translate(-fX, -fY);
	}

	//
	//
	// ===== interesting stuff finished =====
	//
	//

	void add(Shape shape) throws IllegalArgumentException {
		if (shape.fParent == null) {
			if (shape.fX + shape.fWidth > this.fWidth || shape.fY + shape.fHeight > this.fHeight) {
				throw new IllegalArgumentException();
			} else {
				shape.fParent = this;
				shapeList.add(shape);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	void remove(Shape shape) {
		if (shapeList.remove(shape)) {
			shape.fParent = null;
			return;
		}
	}
	
	/**
	 * @param shape
	 * @return -1 if no index found.
	 */
	public int indexOf(Shape shape){
		return shapeList.indexOf(shape);
	}

	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		return shapeList.get(index);
	}

	public int shapeCount() {
		return shapeList.size();
	}

	/**
	 * @return True iff this NestingShape contains a shape that equals the
	 *         parametre Shape.
	 */
	public boolean contains(Shape shape) {
		return shapeList.contains(shape);
	}
}
