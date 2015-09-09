package com.github.jutility.comparator;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import com.github.jutility.comparator.DateComparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateComparatorTest {
	
	private DateComparator comparator;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testCompare() {
		comparator = new DateComparator("date");
		Calendar cal = Calendar.getInstance();
		
		MyBean mybean1 = new MyBean();
		MyBean mybean2 = new MyBean();
		
		mybean1.setDate(null);
		mybean2.setDate(null);
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setDate(null);
		mybean2.setDate(cal.getTime());
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setDate(cal.getTime());
		mybean2.setDate(null);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
		
		mybean1.setDate(cal.getTime());
		mybean2.setDate(cal.getTime());
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setDate(cal.getTime());
		sleep();
		mybean2.setDate(Calendar.getInstance().getTime());
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean2.setDate(cal.getTime());
		sleep();
		mybean1.setDate(Calendar.getInstance().getTime());
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	private void sleep() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public class MyBean {
		private Date date;

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		
	}
}
