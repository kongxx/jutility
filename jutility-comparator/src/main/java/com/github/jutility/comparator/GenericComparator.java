package com.github.jutility.comparator;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.WrapDynaBean;


/**
 * A generic comparator implement. The objects be compared must has compareTo
 * method.
 * 
 * @author fkong
 */
public class GenericComparator extends BeanComparator {
	public GenericComparator(String property) {
		super(property);
	}

	public int compare(Object o1, Object o2) {
		DynaBean dynabean1 = new WrapDynaBean(o1);
		Object property1 = dynabean1.get(property);
		
		DynaBean dynabean2 = new WrapDynaBean(o2);
		Object property2 = dynabean2.get(property);
		
		if (property1 == null && property2 == null) {
			return 0;
		}
		if(property1 == null && property2 != null) {
			return -1;
		}
		if(property1 != null && property2 == null) {
			return 1;
		}
		
		int ret = 0;
		try {
			Object obj = MethodUtils.invokeMethod(property1, "compareTo", property2);
			ret = (Integer)obj;
		} catch (Exception ex) {
			throw new CompareException(ex);
		}
		return ret;
	}
}
