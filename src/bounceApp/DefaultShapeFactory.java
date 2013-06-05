package bounceApp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import bounce.NestingShape;
import bounce.Shape;


/**
 * Simple implementation of the ShapeFactory interface. An instance of 
 * DefaultShapeFactory generates random values and supplies these when
 * instantiating a particular Shape subclass. This factory assumes that the
 * Shape subclass to instantiate defines a 6-argument constructor with the
 * ordered list of arguments being: x, y, deltaX, deltaY, width, and height.
 * 
 * @author Ian Warren
 */
public class DefaultShapeFactory  implements ShapeFactory {
	private String fNameOfClassToInstantiate;
	private String fErrorDescription;
	
	/**
	 * Creates a DefaultShapeFactory object.
	 */
	public DefaultShapeFactory() {
		fNameOfClassToInstantiate = null;
		fErrorDescription = null;
	}
	
	/**
	 * @see bounceApp.ShapeFactory.
	 */
	public Shape makeShape(NestingShape parent) {
		Shape newShape = null;
		
		try {
			// Load class to instantiate.
			Class<?> cls = Class.forName(fNameOfClassToInstantiate);
			
			// Attempt to find a 6-argument constructor for the loaded class.
			Constructor<?> cons = cls.getConstructor(
					java.lang.Integer.TYPE, java.lang.Integer.TYPE, 
					java.lang.Integer.TYPE, java.lang.Integer.TYPE,
					java.lang.Integer.TYPE, java.lang.Integer.TYPE);
			
			/*
			 * Generate initial values for newShape's instance variables.
			 * Respect boundaries of the new shape's intended parent.
			 */
			int x = 0;
			int y = 0;
			int deltaX = (int)(10.0 * Math.random()) + 1;
			int deltaY = (int)(10.0 * Math.random()) + 1;
			int width = (int)(parent.width() / 2 * Math.random()) + 1;
			int height = (int)(parent.height() / 2 * Math.random()) + 1;
			
			// Instantiate shape class, calling the 6-argument constructor.
			newShape = (Shape)cons.newInstance(x, y, deltaX, deltaY, width, height);
			
		} catch(ClassNotFoundException e) {
			// Thrown if named class does not exist.
			fErrorDescription = e.getMessage();
		} catch(NoSuchMethodException e) {
			// Thrown if a constructor with the specified arguments is not 
			// defined by the class.
			fErrorDescription = e.getMessage();
		} catch(SecurityException e) {
			// Thrown if a security manager is set and the running program is
			// not permitted to load classes at run-time.
			fErrorDescription = e.getMessage();
		} catch(InstantiationException e) {
			// Thrown if the loaded class cannot be instantiated, e.g. the
			// class might be abstract.
			fErrorDescription = e.getMessage();
		} catch(IllegalAccessException e) {
			// Thrown if the class' constructor is hidden, e.g. private.
			fErrorDescription = e.getMessage();
		} catch(IllegalArgumentException e) {
			// Thrown if the actual arguments are incompatible with the formal
			// arguments.
			fErrorDescription = e.getMessage();
		} catch(InvocationTargetException e) {
			// Thrown if the constructor itself throws an exception.
			fErrorDescription = e.getMessage();
		} catch(NullPointerException e) {
			// Thrown if fNameOfClassToInstantiate is null.
			fErrorDescription = e.getMessage();
		}
		return newShape;
	}
	
	/**
	 * @see bounceApp.ShapeFactory
	 */
	public void setShapeType(String shapeClassName) {
		fNameOfClassToInstantiate = shapeClassName;
	}
	
	/**
	 * @see bounceApp.ShapeFactory
	 */
	public String instantiationError() {
		return fErrorDescription;
	}
}
