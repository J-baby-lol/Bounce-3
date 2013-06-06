package bounceApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import bounce.DynamicRectangleShape;
import bounce.GemShape;
import bounce.NestingShape;
import bounce.OvalShape;
import bounce.RectangleShape;
import bounce.Shape;
import bounce.ShapeModel;
import bounce.views.AnimationView;
import bounce.views.TableModelAdapter;
import bounce.views.Task3;

/**
 * Main program for Bounce application. A Bounce instance sets up a GUI
 * comprising three views of a ShapeModel: an animation view, a table view and a
 * tree view. In addition the GUI includes buttons and associated event handlers
 * to add new shapes to the animation and to remove existing shapes. A Bounce
 * object uses a Timer to progress the animation; this results in the ShapeModel
 * being sent a clock() message to which it responds by moving its constituent
 * Shape objects and then by notifying the three views (ShapeModelListeners).
 * The application uses a BounceConfig object to read properties from the
 * bounce.properties file, one of which is the name of a ShapeFactory
 * implementation class that is used to create Shapes on request.
 */
public class Bounce {
	private final int DELAY = 25;

	// Underlying model for the application.
	private ShapeModel fModel;

	// View instances.
	private JTree fTreeView;
	private AnimationView fAnimationView;
	private JTable fTabularView;

	/*
	 * Adapter objects (ShapeModelListeners) that transform ShapeModelEvents
	 * into Swing TreeModel and TableModel events.
	 */
	// private TreeModelAdapter fTreeModelAdapter;
	private Task3 fTreeModelAdapter;
	private TableModelAdapter fTableModelAdapter;

	// Swing components to handle user input.
	private JButton fNewShape;
	private JButton fDeleteShape;
	@SuppressWarnings("rawtypes")
	private JComboBox fShapeTypes;

	// ShapeFactory instance.
	private ShapeFactory fFactory;

	// Shape selected in the JTree view.
	private Shape fShapeSelected;

	/**
	 * Creates a Bounce object.
	 */
	public Bounce() {
		// Instantiate model and populate it with an initial set of shapes.
		BounceConfig config = BounceConfig.instance();
		fModel = new ShapeModel(config.animationBounds());
		populateModel();

		// Instantiate GUI objects and construct GUI.
		JFrame mainFrame = buildGUI();

		// Register views with models.
		fModel.addShapeModelListener(fAnimationView);
		fModel.addShapeModelListener(fTableModelAdapter);
		fModel.addShapeModelListener(fTreeModelAdapter);

		// Setup event handlers to process user input.
		setUpEventHandlers();

		// Show GUI and ensure the root shape within the JTree view is selected.
		fTreeView.setSelectionPath(new TreePath(fModel.root()));
		mainFrame.setVisible(true);
		
		// Get a ShapeFactory implementation and set the shape class to
		// instantiate.
		fFactory = loadShapeFactory();
		fFactory.setShapeType((String) (fShapeTypes.getSelectedItem()));

		// Start animation.
		Timer timer = new Timer(DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fModel.clock();
			}
		});
		timer.start();
	}

	public static void main(String[] args) {
		new Bounce();
	}

	/*
	 * Adds shapes to the model.
	 */
	private void populateModel() {
		NestingShape root = fModel.root();

		fModel.add(new RectangleShape(440, 0, 10, 10, 4, 2), root);
		fModel.add(new RectangleShape(0, 0, 5, 7), root);
		fModel.add(new GemShape(20, 20, 4, 4, 200, 20, "Bounce"), root);
		fModel.add(new RectangleShape(0, 0, 2, 2, 10, 10), root);
		fModel.add(new DynamicRectangleShape(0, 0, 2, 3, 180, 130,
				"I change color when I bounce", Color.BLUE), root);
		fModel.add(new OvalShape(50, 110, 2, 2), root);

		NestingShape child = new NestingShape(10, 10, 2, 2, 100, 100);
		fModel.add(new RectangleShape(10, 10, 10, 10, 4, 2), child);
		fModel.add(new DynamicRectangleShape(0, 0, 2, 3, 50, 80, Color.RED),
				child);
		fModel.add(new OvalShape(10, 10, 2, 2, 60, 60), child);
		fModel.add(child, root);
	}

	/*
	 * Registers event handlers with Swing components to process user inputs.
	 */
	private void setUpEventHandlers() {
		/*
		 * Event handling code to be executed whenever the users presses the
		 * "New" button. The shape factory is asked to instantiate a new shape
		 * and this is added to the model.
		 */
		fNewShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shape newShape = fFactory
						.makeShape((NestingShape) fShapeSelected);
				if (newShape != null) {
					fModel.add(newShape, (NestingShape) fShapeSelected);
				} else {
					System.err.println("Error: "
							+ fFactory.instantiationError());
				}
			}
		});

		/*
		 * Event handling code to be executed whenever the user presses the
		 * "Delete" button. The shape that is currently selected in the JTree
		 * view is removed from the model. During removal, the removed shape's
		 * former parent is selected in the JTree.
		 */
		fDeleteShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shape selection = fShapeSelected;
				NestingShape parent = selection.parent();

				fTreeView
						.setSelectionPath(new TreePath(parent.path().toArray()));
				fModel.remove(selection);

			}
		});

		/*
		 * Event handling code to be executed whenever the user selects the name
		 * of a shape type in the combo box. The event handler informs the
		 * factory so that it knows which class to instantiate when responding
		 * to a subsequent makeShape() request.
		 */
		fShapeTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) fShapeTypes.getSelectedItem();
				fFactory.setShapeType(selectedItem);
			}
		});

		/*
		 * Event handling code to be executed whenever the user selects a node
		 * within the JTree view. The event handler records which shape is
		 * selected and in addition enables/disables the "New" and "Delete"
		 * buttons appropriately. In addition, the TableModel representing the
		 * the shape selected in the JTree component is informed of the newly
		 * selected shape.
		 */
		fTreeView.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath selectionPath = fTreeView.getSelectionPath();
				fShapeSelected = (Shape) selectionPath.getLastPathComponent();

				/*
				 * Enable button fNewShape only if what is selected in the JTree
				 * is a NestingShape. Rationale: new shapes can only be added to
				 * NestingShape instances.
				 */
				fNewShape.setEnabled(fShapeSelected instanceof NestingShape);

				/*
				 * Enable button fDeleteShape only if what is selected in the
				 * JTree is not the root node. Rationale: any shape can be
				 * removed with the exception of the root.
				 */
				fDeleteShape.setEnabled(fShapeSelected != fModel.root());

				/*
				 * Tell the table model to represent the shape that is now
				 * selected in the JTree component.
				 */
				fTableModelAdapter.setAdaptee(fShapeSelected);
			}
		});
		
	}

	/*
	 * Instantiates a ShapeFactory that will be used to create Shape objects.
	 * This method attempts to load the class, if any, named in the
	 * application's properties file. If no class is named or if the named class
	 * cannot be loaded, class DefaultShapeFactory is instantiated.
	 */
	private ShapeFactory loadShapeFactory() {
		BounceConfig config = BounceConfig.instance();
		String factoryClassName = config.shapeFactoryClassName();
		ShapeFactory factory = null;

		try {
			Class<?> factoryClass = Class.forName(factoryClassName);
			factory = (ShapeFactory) factoryClass.newInstance();
		} catch (ClassNotFoundException e) {
			// Thrown if named factory class is not found.
		} catch (InstantiationException e) {
			// Thrown if the named class cannot be instantiated.
		} catch (IllegalAccessException e) {
			// Thrown if the named class' constructor is hidden.
		} finally {
			// If a ShapeFactory implementation has not been successfully
			// instantiated, instantiate the default factory.
			if (factory == null) {
				factory = new DefaultShapeFactory();
			}
		}
		return factory;
	}

	/*
	 * Creates and lays out GUI components. Note: there is nothing particularly
	 * interesting about this method - it simply builds up a composition of GUI
	 * components and makes use of borders, scroll bars and layout managers.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JFrame buildGUI() {
		// Create Swing model objects.
		fTreeModelAdapter = new Task3(fModel);
		fTableModelAdapter = new TableModelAdapter(fModel.root());

		// Create main Swing components.
		fTreeView = new JTree(fTreeModelAdapter);
		fTreeView.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		fTabularView = new JTable(fTableModelAdapter);
		fAnimationView = new AnimationView(BounceConfig.instance()
				.animationBounds());

		/*
		 * Create a panel to house the JTree component. The panel includes a
		 * titled border and scrollbars that will be activated when necessary.
		 */
		JPanel treePanel = new JPanel();
		treePanel.setBorder(BorderFactory
				.createTitledBorder("Shape composition hierarchy"));
		JScrollPane scrollPaneForTree = new JScrollPane(fTreeView);
		scrollPaneForTree.setPreferredSize(new Dimension(300, 504));
		treePanel.add(scrollPaneForTree);

		/*
		 * Create a panel to house the animation view. This panel includes a
		 * titled border and scroll bars if the animation area exceeds the
		 * allocated screen space.
		 */
		JPanel animationPanel = new JPanel();
		animationPanel.setBorder(BorderFactory
				.createTitledBorder("Shape animation"));
		JScrollPane scrollPaneForAnimation = new JScrollPane(fAnimationView);
		scrollPaneForAnimation.setPreferredSize(new Dimension(504, 504));
		animationPanel.add(scrollPaneForAnimation);
		fAnimationView.setPreferredSize(BounceConfig.instance()
				.animationBounds());

		/*
		 * Create a panel to house the tabular view. Again, decorate the tabular
		 * view with a border and enable automatic activation of scroll bars.
		 */
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(BorderFactory.createTitledBorder("Shape state"));
		JScrollPane scrollPaneForTable = new JScrollPane(fTabularView);
		scrollPaneForTable.setPreferredSize(new Dimension(810, 150));
		tablePanel.add(scrollPaneForTable);

		/*
		 * Create a control panel housing buttons for creating and destroying
		 * shapes, plus a combo box for selecting the type of shape to create.
		 */
		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		controlPanel.setBorder(BorderFactory
				.createTitledBorder("Control panel"));
		fNewShape = new JButton("New");
		fDeleteShape = new JButton("Delete");
		fShapeTypes = new JComboBox(BounceConfig.instance().shapeTypes());
		controlPanel.add(fNewShape);
		controlPanel.add(fDeleteShape);
		controlPanel.add(fShapeTypes);

		// Add components to main JFrame using further intermediate panels.
		JFrame frame = new JFrame("Bounce");
		JPanel top = new JPanel(new BorderLayout());
		top.add(animationPanel, BorderLayout.CENTER);
		top.add(treePanel, BorderLayout.WEST);
		top.add(tablePanel, BorderLayout.SOUTH);
		frame.add(top, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);

		// Have window centered on screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getPreferredSize();
		frame.setLocation(screenSize.width / 2 - (frameSize.width / 2),
				screenSize.height / 2 - (frameSize.height / 2));
		frame.pack();
		// frame.setResizable(false);
		frame.setResizable(true);

		// Cause application to terminate when window (frame) is closed.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		return frame;
	}
}
