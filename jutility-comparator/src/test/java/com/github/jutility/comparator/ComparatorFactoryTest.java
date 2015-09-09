/**
 * 
 */
package com.github.jutility.comparator;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import com.github.jutility.comparator.BeanComparator;
import com.github.jutility.comparator.ComparatorFactory;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author fkong
 *
 */
public class ComparatorFactoryTest {

	private ComparatorFactory factory;
	
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
		factory = ComparatorFactory.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ComparatorFactory#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(factory);
	}

	/**
	 * Test method for {@link ComparatorFactory#getComparator(java.lang.Class, java.lang.String)}.
	 */
	@Test
	public void testGetComparator() {
		BeanComparator beanComparator;
		beanComparator = factory.getComparator(A.class, "a1");
		assertNotNull(beanComparator);
		Assert.assertEquals("a1", "a1");
		beanComparator = factory.getComparator(A.class, "a2");
		assertNotNull(beanComparator);
		Assert.assertEquals("a2", "a2");
		beanComparator = factory.getComparator(A.class, "a3");
		assertNotNull(beanComparator);
		Assert.assertEquals("a3", "a3");
	}
	
	public class A {
		private String a1;
		private int a2;
		private Date a3;

		public String getA1() {
			return a1;
		}

		public void setA1(String a1) {
			this.a1 = a1;
		}

		public int getA2() {
			return a2;
		}

		public void setA2(int a2) {
			this.a2 = a2;
		}

		public Date getA3() {
			return a3;
		}

		public void setA3(Date a3) {
			this.a3 = a3;
		}
	}
}
