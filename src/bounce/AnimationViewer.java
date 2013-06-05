package bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Simple GUI program to show an animation of shapes. Class AnimationViewer is a
 * special kind of GUI component (JPanel), and as such an instance of
 * AnimationViewer can be added to a JFrame object. A JFrame object is a window
 * that can be closed, minimised, and maximised. The state of a AnimationViewer
 * object comprises a list of Shapes and a Timer object. An AnimationViewer
 * instance subscribes to events that are published by a Timer. In response to
 * receiving an event from the Timer, the AnimationViewer iterates through a
 * list of Shapes requesting that each Shape paints and moves itself.
 * 
 * @author Ian Warren
 */
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds to generate ActionEvents.
	private final int DELAY = 20;

	// Collection of Shapes to animate.
	private List<Shape> shapes;

	private Timer timer = new Timer(DELAY, this);

	/**
	 * Creates an AnimationViewer instance with a list of Shape objects and
	 * starts the animation.
	 */
	public AnimationViewer() {
		shapes = new ArrayList<Shape>();

		NestingShape root = new NestingShape(100, 100, 5, -2, 400, 400, "hi");
		DynamicRectangleShape pre = new DynamicRectangleShape(Color.RED);
		DynamicRectangleShape post = new DynamicRectangleShape(20, 30, 5, 1, 40, 10,
				Color.BLUE);

		NestingShape intermediate = new NestingShape(0, 0, 1, 3, 300, 300);
		OvalShape preIntermediate = new OvalShape();
		OvalShape postIntermediate = new OvalShape(0, 0, 2, 7, 20, 20);

		NestingShape small = new NestingShape(0, 0, 2, 2, 200, 200);
		DynamicRectangleShape preSmall = new DynamicRectangleShape(Color.RED);
		DynamicRectangleShape postSmall = new DynamicRectangleShape(20, 20, -2, 2, 20,
				20, Color.BLUE);

		root.add(preIntermediate);
		root.add(intermediate);
		root.add(postIntermediate);
		
		intermediate.add(preSmall);
		intermediate.add(small);
		intermediate.add(postSmall);

		shapes.add(pre);
		shapes.add(root);
		shapes.add(post);

		// Start the animation.
		timer.start();
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object should
	 * be repainted. This can happen, for example, after an explicit repaint()
	 * call or after the window that contains this AnimationViewer object has
	 * been exposed after being hidden by another window.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);

		// Calculate bounds of animation screen area.
		int width = getSize().width;
		int height = getSize().height;

		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);

		// Progress the animation.
		for (Shape s : shapes) {
			s.paint(painter);
			s.move(width, height);
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to
		// repaint() will cause the AnimationViewer's paintComponent() to be
		// called.
		repaint();
	}

	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Animation viewer");
		frame.add(new AnimationViewer());

		// Set window properties.
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
