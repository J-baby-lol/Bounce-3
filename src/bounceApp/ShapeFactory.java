package bounceApp;

import bounce.NestingShape;
import bounce.Shape;

/**
 * Interface that defines methods to be implemented by factory classes that
 * creates Shapes on request. Different ShapeFactory implementations will 
 * implement the interface differently. For example, DefaultShapeFactory 
 * instantiates Shape subclasses and randomly generates constructor argument
 * values. An alternative implementation might present the user with a GUI form
 * allowing the user to enter attribute values for a newly created shape.
 * 
 * @author Ian Warren
 *
 */
public interface ShapeFactory {
	/**
	 * Creates an instance of some subclass of Shape. 
	 * @param parent the intended NestingShape that the new Shape will be added
	 * to.
	 * @return an instance of some subclass of Shape, or null if the 
	 * instantiation process fails. Instantiation can fail, for example, if an
	 * attempt is made to instantiate a non-existing class, or if the class is
	 * not a subclass of Shape. 
	 */
	Shape makeShape(NestingShape parent);
	
	/**
	 * Sets the class that should be instantiated on a subsequent call to 
	 * makeShape().
	 * @param shapeClassName the name of the class that should be instantiated.
	 */
	void setShapeType(String shapeClassName);
	
	/**
	 * Returns a description of what caused the last failed makeShape() call to 
	 * fail. If there have been no past failed makeShape() calls, this method 
	 * returns null. 
	 */
	String instantiationError();
}
