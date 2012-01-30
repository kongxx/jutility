package com.googlecode.jutility.performance;

import java.util.HashMap;
import java.util.Map;

public class StatisticUtil {
	
	private static StatisticUtil statistic = new StatisticUtil();

	private static Map<String, StatisticElement> elements;

	private StatisticUtil() {
		elements = new HashMap<String, StatisticElement>();
	}

	public static StatisticUtil getInstance() {
		return statistic;
	}

	public void setStartPoint(String name) {
		if (elements.containsKey(name)) {
			StatisticElement element = elements.get(name);
			element.setCount();
			element.setStart();
		} else {
			StatisticElement element = new StatisticElement(name);
			element.setCount();
			element.setStart();
			elements.put(name, element);
		}
	}
	
	public void setStartPoint() {
		StackTraceElement[] stacks = (new Throwable()).getStackTrace();
		int length = stacks.length;
		if (length < 1) {
			return;
		}
		String clazzname = stacks[1].getClassName();
		String clazzmethod = stacks[1].getMethodName();
		String name = clazzname + "." + clazzmethod;
		setStartPoint(name);
	}

	public void setEndPoint(String name) {
		if (!elements.containsKey(name)) {
			return;
		} else {
			StatisticElement element = elements.get(name);
			element.setEnd();
			element.setTime();
		}
	}
	
	public void setEndPoint() {
		StackTraceElement[] stacks = (new Throwable()).getStackTrace();
		int length = stacks.length;
		if (length < 1) {
			return;
		}
		String clazzname = stacks[1].getClassName();
		String clazzmethod = stacks[1].getMethodName();
		String name = clazzname + "." + clazzmethod;
		setEndPoint(name);
	}

	public void clear() {
		elements.clear();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(String name : elements.keySet()) {
			StatisticElement element = elements.get(name);
			sb.append(element.toString());
		}
		return sb.toString();
	}
}