package com.googlecode.jutility.compare;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.jutility.compare.comparator.BeanComparator;
import com.googlecode.jutility.compare.comparator.ComparatorFactory;

/**
 * A Sorter that provice sort method to sort a List&lt;Object&gt; collection by
 * an or series property of this Object instance.
 * <p>
 * By default, it support String, short, int, long, float, double, byte, char,
 * boolean and Date type.
 * 
 * @author fkong
 * 
 */
public class Sorter {
	// =========================================================================
	// private properties

	/**
	 * An instance of ComparatorFactory, which be used to get Comparator
	 * implements.
	 */
	private ComparatorFactory factory = ComparatorFactory.getInstance();

	/**
	 * Registered comparator collection, the key is the type and could be
	 * java.lang.String etc. The value is the instance of BeanComparator.
	 */
	private Map<String, BeanComparator> comparators;

	/**
	 * Registered comparator collection, the key is the type and could be
	 * java.lang.String etc. The value is the class of BeanComparator.
	 */
	private Map<String, Class> beanComparatorClasses;

	// =========================================================================
	// public methods

	/**
	 * Constructor.
	 */
	public Sorter() {
		comparators = new HashMap<String, BeanComparator>();
		beanComparatorClasses = new HashMap<String, Class>();
	}

	/**
	 * Sort the objects in list with the given propertis of the object.
	 * 
	 * @param <T>
	 * @param list
	 *            the same kind of Object collection
	 * @param properties
	 *            the property array of Object
	 * @param sortOrder
	 *            sort type, ASC or DESC
	 * @throws SortException
	 */
	public <T> void sort(List<T> list, String[] properties, SortOrder sortOrder)
			throws SortException {
		if (list == null || properties == null) {
			throw new IllegalArgumentException("Invalid argument!");
		}
		if (list.size() == 0 || properties.length == 0) {
			return;
		}

		// item object
		Class clazz = list.get(0).getClass();

		// sort with the first property
		sort(list, properties[0], sortOrder);

		// If the only sort by one property, return
		if (properties.length == 1) {
			return;
		}

		// skip the first
		for (int idxProp = 1; idxProp < properties.length; idxProp++) {
			String preProp = properties[idxProp - 1];
			String prop = properties[idxProp];

			Comparator comparator = getBeanComparator(clazz, preProp);

			List<T> tmpList = new ArrayList<T>();
			int idx = -1;
			for (int idxObj = 1; idxObj < list.size(); idxObj++) {
				if (comparator.compare(list.get(idxObj - 1), list.get(idxObj)) == 0) {
					if (idx == -1) {// record the first same item
						idx = idxObj - 1;
					}
					if (tmpList.isEmpty()) {
						tmpList.add(list.get(idxObj - 1));
					}
					tmpList.add(list.get(idxObj));
					if (idxObj == list.size() - 1) {
						sort(tmpList, prop, sortOrder);
						for (int i = 0; i < tmpList.size(); i++) {
							list.set(idx + i, tmpList.get(i));
						}
						idx = -1;
						tmpList.clear();
					}
				} else {
					if (!tmpList.isEmpty()) {
						sort(tmpList, prop, sortOrder);
						for (int i = 0; i < tmpList.size(); i++) {
							list.set(idx + i, tmpList.get(i));
						}
						idx = -1;
						tmpList.clear();
					}
				}
			}// end for idxObj
		}// end for idxProp
	}

	/**
	 * Sort the list collection, the list could be any same type Object.
	 * 
	 * @param list
	 *            the same type object collection.
	 * @param property
	 *            the property name of the object in list
	 * @param sortOrder
	 *            sort type, see SortOrder enum type
	 * @throws SortException
	 */
	public <T> void sort(List<T> list, String property, SortOrder sortOrder)
			throws SortException {
		if (list == null || property == null) {
			throw new IllegalArgumentException("Invalid argument!");
		}
		if (list.size() == 0) {
			return;
		}
		Class clazz = list.get(0).getClass();
		BeanComparator comparator = getBeanComparator(clazz, property);
		sort(list, comparator, sortOrder);
	}

	/**
	 * Register a BeanComparator for the given Class. For example, you can
	 * invoke
	 * <p>
	 * registerBeanComparator(java.lang.String.class, MyStringComparator);
	 * </p>
	 * to use MyStringComparator to sort the String type property.
	 * 
	 * @param propertyClass
	 *            the Class that need to be used to compare in an object
	 *            property
	 * @param beanComparatorClass
	 *            a Class of BeanComparator
	 */
	public void registerBeanComparator(Class propertyClass,
			Class beanComparatorClass) {
		beanComparatorClasses.put(propertyClass.getName(), beanComparatorClass);
	}

	/**
	 * Register a BeanComparator for the given Class. For example, you can
	 * invoke
	 * <p>
	 * MyStringComparator comparator = new MyStringComparator("name");
	 * registerBeanComparator(java.lang.String.class, comparator);
	 * </p>
	 * to use an instance of MyStringComparator to sort the String type
	 * property.
	 * 
	 * @param propertyClass
	 *            the Class that need to be compare
	 * @param comparator
	 *            an instance of BeanComparator
	 * @deprecated use registerBeanComparator(Class propertyClass, Class
	 *             beanComparatorClass) to replace
	 */
	public void registerBeanComparator(Class propertyClass,
			BeanComparator comparator) {
		comparators.put(propertyClass.getName(), comparator);
	}

	// =========================================================================
	// private methods

	private <T> void sort(List<T> list, BeanComparator beanComparator,
			SortOrder sortOrder) {
		Collections.sort(list, beanComparator);
		if (sortOrder == SortOrder.DESC) {
			Collections.reverse(list);
		}
	}

	/**
	 * Get an instance of BeanComparator. It will follow this sequence to get an
	 * instance of BeanComparator. <li>Get comparator from registered instance
	 * of BeanComparator.</li> <li>Get comparator from registered Class of
	 * BeanComparator.</li> <li>Get comparator from definition in
	 * comparator.properties.</li>
	 * 
	 * @param <T>
	 * @param clazz
	 *            the class of object
	 * @param property
	 *            the property name of this class
	 * @return an instance of BeanComparator
	 */
	private <T> BeanComparator getBeanComparator(Class clazz, String property) {
		String fieldType;
		try {
			Field field = clazz.getDeclaredField(property);
			fieldType = field.getType().getName();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (comparators.containsKey(fieldType)) {
			return comparators.get(fieldType);
		} else if (beanComparatorClasses.containsKey(fieldType)) {
			try {
				String className = beanComparatorClasses.get(fieldType)
						.getName();
				Constructor constructor = Class.forName(className)
						.getConstructor(String.class);
				return (BeanComparator) constructor
						.newInstance(new Object[] { property });
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			return factory.getComparator(clazz, property);
		}
	}
}
