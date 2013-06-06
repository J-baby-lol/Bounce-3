package bounce.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;

/**
 * 
 * Really this class is a concrete TreeModelAdapter for ShapeModel objects.
 * 
 * @author Xuzong Chen
 * 
 */
public class Task2 implements TreeModel {

	private ShapeModel adaptee;
	
	public Task2(ShapeModel model) {
		adaptee = model;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// do nothing.
	}

	/**
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 * @return null if parent is not a NestingShape.
	 */
	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof NestingShape) {
			NestingShape ns = (NestingShape) parent;
			try {
				return ns.shapeAt(index);
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof NestingShape) {
			NestingShape ns = (NestingShape) parent;
			return ns.shapeCount();
		} else if (parent instanceof Shape) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object,
	 *      java.lang.Object)
	 * @return -1 if no index found.
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (!(parent instanceof NestingShape && child instanceof Shape)) {
			return -1;
		} else {
			NestingShape ns = (NestingShape) parent;
			Shape s = (Shape) child;
			return ns.indexOf(s);
		}
	}

	@Override
	public Object getRoot() {
		return adaptee.root();
	}

	/**
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 * @return false if node is a NestingShape, true otherwise.
	 */
	@Override
	public boolean isLeaf(Object node) {
		return !(node instanceof NestingShape);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// do nothing.
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// "safely", nothing needs to be done.
	}

}
