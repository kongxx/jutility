package com.github.jutility.comparator;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;

/**
 * User java.lang.String type to compare the given two Objects. Assume the s1
 * and s2 is a property of object1 and object2. The compare result will be:
 * <li>If the s1 is null and s2 is null, the result is 0.</li>
 * <li>If the s1 is null, but s2 is not null, the result is -1.</li>
 * <li>If the s1 is not null, but s2 is null, the result is 1.</li>
 * <li>If s1 and s2 are not null, the result is s1.compareTo(s2)</li>
 * <p>
 * If the s1="a1", s2="a9", the result is -1; if the s1="a11", s2="a9", the
 * result is -1;
 * </p>
 * 
 * @author fkong
 */
public class StringComparator extends BeanComparator {

	public StringComparator(String propertyName) {
		super(propertyName);
	}

	public int compare(Object o1, Object o2) {
		DynaBean db1 = new WrapDynaBean(o1);
		String s1 = (String) db1.get(property);

		DynaBean db2 = new WrapDynaBean(o2);
		String s2 = (String) db2.get(property);

		if(s1 == null && s2 == null) {
			return 0;
		}
		if(s1 == null && s2 != null) {
			return -1;
		}
		if(s1 != null && s2 == null) {
			return 1;
		}

		if (s1.equals(s2)) {
			return 0;
		}
		return s1.compareTo(s2);
	}
}
