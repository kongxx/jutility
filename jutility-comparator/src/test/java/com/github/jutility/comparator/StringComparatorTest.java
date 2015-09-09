package com.github.jutility.comparator;

import static org.junit.Assert.assertTrue;

import com.github.jutility.comparator.StringComparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringComparatorTest {
	
	private StringComparator comparator;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testCompare() {
		comparator = new StringComparator("name");
		
		MyBean mybean1 = new MyBean();
		MyBean mybean2 = new MyBean();
		
		mybean1.setName("a");
		mybean2.setName("a");
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setName("a");
		mybean2.setName("b");
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setName("b");
		mybean2.setName("a");
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
		
		mybean1.setName("a1");
		mybean2.setName("a9");
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setName("a11");
		mybean2.setName("a9");
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setName(null);
		mybean2.setName(null);
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setName(null);
		mybean2.setName("abc");
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
	}
	public class MyBean {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
