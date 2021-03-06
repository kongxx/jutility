/**
 * 
 */
package com.github.jutility.comparator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author fkong
 * 
 */
public class TestSorter {
	private Sorter sorter = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sorter = new Sorter();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sorter = null;
	}

	/**
	 * Test method for {@link Sorter#Sorter()}.
	 */
	@Test
	public void testSorter() {
		assertNotNull(sorter);
	}

	/**
	 * Test method for
	 * {@link Sorter#sort(java.util.List, java.lang.String[], SortOrder)}
	 * .
	 */
	@Test
	public void testSortListOfTStringArraySortOrder() {
		List<MySchedule> list = new ArrayList<MySchedule>();
		list.add(new MySchedule("a1", "2007-01-01", "01:01:01", "2007-01-01", "01:01:01"));
		list.add(new MySchedule("a2", "2007-01-01", "01:01:02", "2007-01-02", "01:01:03"));
		list.add(new MySchedule("a3", "2007-01-01", "01:01:03", "2007-01-02", "01:01:02"));
		list.add(new MySchedule("a4", "2007-01-01", "01:01:04", "2007-01-02", "01:01:04"));
		list.add(new MySchedule("a5", "2007-01-01", "01:01:05", "2007-01-03", "01:01:01"));

		sorter.sort(list, new String[] { "startDate", "startTime" }, SortOrder.ASC);
		assertEquals(5, list.size());
		assertEquals("a1", list.get(0).getName());
		assertEquals("a2", list.get(1).getName());
		assertEquals("a3", list.get(2).getName());
		assertEquals("a4", list.get(3).getName());
		assertEquals("a5", list.get(4).getName());

		sorter.sort(list, new String[] { "startDate", "startTime" }, SortOrder.DESC);
		assertEquals(5, list.size());
		assertEquals("a5", list.get(0).getName());
		assertEquals("a4", list.get(1).getName());
		assertEquals("a3", list.get(2).getName());
		assertEquals("a2", list.get(3).getName());
		assertEquals("a1", list.get(4).getName());

		sorter.sort(list, new String[] { "endDate", "endTime" }, SortOrder.ASC);
		assertEquals(5, list.size());
		assertEquals("a1", list.get(0).getName());
		assertEquals("a3", list.get(1).getName());
		assertEquals("a2", list.get(2).getName());
		assertEquals("a4", list.get(3).getName());
		assertEquals("a5", list.get(4).getName());

		sorter.sort(list, new String[] { "endDate", "endTime" }, SortOrder.DESC);
		assertEquals(5, list.size());
		assertEquals("a5", list.get(0).getName());
		assertEquals("a4", list.get(1).getName());
		assertEquals("a2", list.get(2).getName());
		assertEquals("a3", list.get(3).getName());
		assertEquals("a1", list.get(4).getName());
	}

	/**
	 * Test method for
	 * {@link Sorter#sort(java.util.List, java.lang.String, SortOrder)}
	 * .
	 */
	@Test
	public void testSortListOfTStringSortOrder() {
		List<MySchedule> list = new ArrayList<MySchedule>();
		for (int i = 1; i <= 5; i++) {
			list.add(new MySchedule("a" + i, "2007-01-01", "01:01:01", "2007-01-01", "01:01:01"));
		}

		sorter.sort(list, "name", SortOrder.ASC);
		assertEquals(5, list.size());
		assertEquals("a1", list.get(0).getName());
		assertEquals("a5", list.get(4).getName());

		sorter.sort(list, "name", SortOrder.DESC);
		assertEquals(5, list.size());
		assertEquals("a5", list.get(0).getName());
		assertEquals("a1", list.get(4).getName());
	}
	
	/**
	 * Test method for
	 * {@link Sorter#sort(java.util.List, java.lang.String, SortOrder)}
	 * .
	 */
	@Test
	public void testInheritSortListOfTStringArraySortOrder() {
		List<C> list = new ArrayList<C>();
		for (int i = 1; i <= 5; i++) {
			C c = new C();
			c.setA("a" + i);
			c.setB("b" + i);
			c.setC("c" + i);
			list.add(c);
		}

		sorter.sort(list, new String[]{"c", "b", "a"}, SortOrder.ASC);
		assertEquals(5, list.size());
		for (int i =0; i < list.size(); i++) {
			assertEquals("a" + (i + 1), list.get(i).getA());
			assertEquals("b" + (i + 1), list.get(i).getB());
			assertEquals("c" + (i + 1), list.get(i).getC());
		}
		
		sorter.sort(list, new String[]{"c", "b", "a"}, SortOrder.DESC);
		for (int i =0; i < list.size(); i++) {
			assertEquals("a" + (list.size() - i), list.get(i).getA());
			assertEquals("b" + (list.size() - i), list.get(i).getB());
			assertEquals("c" + (list.size() - i), list.get(i).getC());
		}
	}
	
	/**
	 * Test method for
	 * {@link Sorter#registerBeanComparator(java.lang.Class, java.lang.Class)}
	 * .
	 */
	@Test
	public void testRegisterBeanComparatorClassClass() {
		List<MySchedule> list = new ArrayList<MySchedule>();

		MySchedule my1 = new MySchedule();
		my1.setName("base");
		my1.setMyType(MyType.BASE);
		list.add(my1);

		MySchedule my2 = new MySchedule();
		my2.setName("onceonly");
		my2.setMyType(MyType.BASE);
		list.add(my2);

		sorter.registerBeanComparator(MyType.class, MyTypeComparator.class);

		sorter.sort(list, "myType", SortOrder.ASC);
		assertEquals("base", list.get(0).getName());
		assertEquals("onceonly", list.get(1).getName());

		sorter.sort(list, "myType", SortOrder.DESC);
		assertEquals("onceonly", list.get(0).getName());
		assertEquals("base", list.get(1).getName());
	}

	public class MySchedule {
		private String name;
		private String startDate;
		private String startTime;
		private String endDate;
		private String endTime;
		private MyType myType;

		public MySchedule() {

		}

		public MySchedule(String name, String startDate, String startTime,
				String endDate, String endTime) {
			this.name = name;
			this.startDate = startDate;
			this.startTime = startTime;
			this.endDate = endDate;
			this.endTime = endTime;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public MyType getMyType() {
			return myType;
		}

		public void setMyType(MyType myType) {
			this.myType = myType;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum MyType {
		BASE, ONCE_ONLY,
	}

	public static class MyTypeComparator extends BeanComparator {
		public MyTypeComparator(String property) {
			super(property);
		}

		public int compare(Object o1, Object o2) {
			DynaBean db1 = new WrapDynaBean(o1);
			MyType type1 = (MyType) db1.get(property);

			DynaBean db2 = new WrapDynaBean(o2);
			MyType type2 = (MyType) db2.get(property);

			return type1.toString().compareTo(type2.toString());
		}
	}
	
	public class A {
		private String a;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}
	}
	public class B extends A {
		private String b;

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}
	}
	
	public class C extends B {
		private String c;

		public String getC() {
			return c;
		}

		public void setC(String c) {
			this.c = c;
		}
	}
}
