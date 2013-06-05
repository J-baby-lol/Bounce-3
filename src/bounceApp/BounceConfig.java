package bounceApp;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Singleton class that allows easy access to properties specified in the 
 * Bounce application's properties file, named bounce.properties. This file
 * should be stored in the user's home directory. On Windows this is:
 * C:/Documents and settings/<username>. 
 * 
 * @author Ian Warren
 *
 */
public class BounceConfig {
	// Location of properties file.
	private static final String FILE_LOCATION = "user.home";

	// Name of properties file.
	private static final String FILE_NAME = "bounce.properties";
	
	// Property default values.
	public static final int DEFAULT_ANIMATION_WIDTH = 500;
	public static final int DEFAULT_ANIMATION_HEIGHT = 500;
	
	public static String DEFAULT_SHAPE_FACTORY = "bounceApp.DefaultShapeFactory";
	
	public static final int MAX_ANIMATION_WIDTH = 1000;
	public static final int MAX_ANIMATION_HEIGHT = 1000;
	
	// Property names (keys).
	private static final String ANIMATION_WIDTH = "animation_width";
	private static final String ANIMATION_HEIGHT = "animation_height";
	private static final String SHAPES = "shape_classes";
	private static final String SHAPE_FACTORY = "shape_factory_class";
	
	// Property values.
	private String[] fShapeTypes;
	private Dimension fBounds;
	private String fShapeFactoryClassName;
	
	private static BounceConfig instance;
	
	/**
	 * Returns a reference to BounceConfig object.
	 */
	public static BounceConfig instance() {
		if(instance == null) {
			instance = new BounceConfig();
		}
		return instance;
	}
	
	/*
	 * Hidden constructor that populates the BounceConfig instance with 
	 * property values. 
	 */
	private BounceConfig() {
		String path = System.getProperty(FILE_LOCATION);
		File file = new File(path, FILE_NAME);
	
		// Create a new properties object.
		Properties props = new Properties();
	
		try {
			// Attempt to read property values from file.
			InputStream in = new FileInputStream(file);
			props.load(in);
			in.close();
		} catch(IOException e) {
			// Properties file not found, or an error has occurred reading the file.
			// No action necessary.
		} finally {
			// Read bounds property.
			int width = getBound(ANIMATION_WIDTH, DEFAULT_ANIMATION_WIDTH, props);
			int height = getBound(ANIMATION_HEIGHT, DEFAULT_ANIMATION_HEIGHT, props);
			
			/*
			 * Restore bounds to default values if invalid values have been 
			 * read from the properties file.
			 */
			if(width < DEFAULT_ANIMATION_WIDTH || width > MAX_ANIMATION_WIDTH) {
				width = DEFAULT_ANIMATION_WIDTH;
			} 
			if(height < DEFAULT_ANIMATION_HEIGHT || width > MAX_ANIMATION_HEIGHT) {
				height = DEFAULT_ANIMATION_HEIGHT;
			}
			fBounds = new Dimension(width, height);
			
			// Set shapes property.
			String shapeTypes = props.getProperty(SHAPES);
			if(shapeTypes == null) {
				// Create an empty array if no shape names are given.
				fShapeTypes = new String[0];
			} else {
				fShapeTypes = shapeTypes.split("\\s+");
			}
			
			// Read shape factory property.
			fShapeFactoryClassName = props.getProperty(SHAPE_FACTORY);
			if(fShapeFactoryClassName == null) {
				// Unspecified, so set to default value.
				fShapeFactoryClassName = DEFAULT_SHAPE_FACTORY;
			}
		}
	} 
	
	/**
	 * Returns the bounds of the world in which shapes move around. This method
	 * returns bounds in the range DEFAULT_ANIMATION_WIDTH/HEIGHT .. 
	 * MAX_ANIMATION_WIDTH/HEIGHT. In the properties file specifies bounds 
	 * outside of this range, they are ignored and DEFAULT_ANIMATION_WIDTH/
	 * HEIGHT are returned.
	 */
	public Dimension animationBounds() {
		return fBounds;
	}
	
	/**
	 * Returns an array of strings containing names of shape classes. If no 
	 * shape classes are named in the properties file, this method returns an 
	 * empty array.
	 */
	public String[] shapeTypes() {
		return fShapeTypes;
	}
	
	/**
	 * Returns the name of a ShapeFactory implementation class that should be
	 * used by the Bounce application creates Shapes when requested.
	 */
	public String shapeFactoryClassName() {
		return fShapeFactoryClassName;
	}
	
	/*
	 * Implementation method to read/validate bound properties.
	 */
	private int getBound(String propertyName, int defaultValue, Properties props) {
		int property = defaultValue;
		String propertyStr = props.getProperty(propertyName);
		if(propertyStr != null) {
			try {
				property = Integer.parseInt(propertyStr);
			} catch(NumberFormatException e) {
				// No action necessary - fall back on default setting for property.
			}
		}
		return property;
	}
}
