package bounce.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;

public class Task3 extends Task2 implements ShapeModelListener {

	private List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();

	public Task3(ShapeModel model) {
		super(model);
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		listeners.add(l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove(l);
	}

	@Override
	public void update(ShapeModelEvent event) {
		ShapeModelEvent.EventType eventType = event.eventType();
		Shape operand;
		NestingShape parent;
		parent = event.parent();
		int index;
		TreeModelEvent e;

		switch (eventType) {
		case ShapeAdded:
			operand = event.operand();
			index = event.index();
			e = new TreeModelEvent(this, parent.path().toArray(),
					new int[] { index }, new Object[] { operand });
			for (TreeModelListener l : listeners) {
				l.treeNodesInserted(e);
				// System.out.println(l.toString());
			}
			break;
		case ShapeRemoved:
			operand = event.operand();
			index = event.index();
			e = new TreeModelEvent(this, parent.path().toArray(),
					new int[] { index }, new Object[] { operand });
			for (TreeModelListener l : listeners) {
				l.treeNodesRemoved(e);
			}
			break;
		case ShapeMoved:
			// do nothing.
			break;
		}
	}

}
