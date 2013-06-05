package bounce.views;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

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
		// don't do anything. Task2 does not listen, nor broadcast.
	}

	@Override
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getRoot() {
		return adaptee.root();  // TODO check
	}

	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}

}
