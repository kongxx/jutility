package com.github.jutility.comparator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * A factory that be used to create BeanComparator instance. It use
 * comparator.properties file to get BeanComparator class implement.
 * 
 * @author fkong
 */
public class ComparatorFactory {

	private static ComparatorFactory factory = new ComparatorFactory();

	/**
	 * A property include the comparator class name
	 */
	private Properties props;

	private ComparatorFactory() {
		props = new Properties();
		try {
			props.load(ComparatorFactory.class
					.getResourceAsStream("comparator.properties"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static ComparatorFactory getInstance() {
		return factory;
	}

	/**
	 * Get an instance of BeanComparator with given Object and property of this
	 * Object.
	 * 
	 * @param clazz Class instance
	 * @param property the property of clazz
	 * @return an instance of BeanComparator
	 */
	public <T> BeanComparator getComparator(Class clazz, String property) {
		Field field = null;
		while(true) {
			try {
				field = clazz.getDeclaredField(property);
				break;
			} catch (NoSuchFieldException ex) {
				if (clazz.getName().equals("java.lang.Object")) {
					throw new RuntimeException(ex);
				}
				clazz = clazz.getSuperclass();
				continue;
			}
		}
		
		try {
			String fieldType = field.getType().getName();
			String className = props.getProperty(fieldType);
			if (className == null) {
				className = GenericComparator.class.getName();
			}
			Constructor constructor = Class.forName(className).getConstructor(
					String.class);
			return (BeanComparator) constructor
					.newInstance(new Object[] { property });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
