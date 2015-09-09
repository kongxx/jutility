package com.github.jutility.comparator;

import static org.junit.Assert.assertTrue;

import com.github.jutility.comparator.GenericComparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericComparatorTest {

	private GenericComparator comparator;
	
	private MyBean mybean1;
	private MyBean mybean2;
	
	@Before
	public void setUp() throws Exception {
		mybean1 = new MyBean();
		mybean2 = new MyBean();
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testIntegerCompare() {
		comparator = new GenericComparator("myint");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMyint(1);
		mybean2.setMyint(2);
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMyint(2);
		mybean2.setMyint(1);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	@Test
	public void testLongCompare() {
		comparator = new GenericComparator("mylong");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMylong(100000000);
		mybean2.setMylong(200000000);
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMylong(200000000);
		mybean2.setMylong(100000000);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	@Test
	public void testShortCompare() {
		comparator = new GenericComparator("myshort");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMyshort((short) 1);
		mybean2.setMyshort((short) 127);
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMyshort((short) 127);
		mybean2.setMyshort((short) 1);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	@Test
	public void testByteCompare() {
		comparator = new GenericComparator("mybyte");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMybyte((byte) 1);
		mybean2.setMybyte((byte) 127);
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMybyte((byte) 127);
		mybean2.setMybyte((byte) 1);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	@Test
	public void testCharCompare() {
		comparator = new GenericComparator("mychar");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMychar('a');
		mybean2.setMychar('z');
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMychar('z');
		mybean2.setMychar('a');
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	@Test
	public void testFloatCompare() {
		comparator = new GenericComparator("myfloat");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMyfloat(1.01F);
		mybean2.setMyfloat(1.02F);
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMyfloat(1.02F);
		mybean2.setMyfloat(1.01F);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	@Test
	public void testDoubleCompare() {
		comparator = new GenericComparator("mydouble");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMydouble(1.01);
		mybean2.setMydouble(1.02);
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMydouble(1.02);
		mybean2.setMydouble(1.01);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	@Test
	public void testBooleanCompare() {
		comparator = new GenericComparator("myboolean");
		
		assertTrue(comparator.compare(mybean1, mybean2) == 0);
		
		mybean1.setMyboolean(false);
		mybean2.setMyboolean(true);
		assertTrue(comparator.compare(mybean1, mybean2) < 0);
		
		mybean1.setMyboolean(true);
		mybean2.setMyboolean(false);
		assertTrue(comparator.compare(mybean1, mybean2) > 0);
	}
	
	public class MyBean {
		int myint ;
		long mylong;
		short myshort;
		byte mybyte;
		char mychar;
		float myfloat;
		double mydouble;
		boolean myboolean;
		public int getMyint() {
			return myint;
		}
		public void setMyint(int myint) {
			this.myint = myint;
		}
		
		public long getMylong() {
			return mylong;
		}
		public void setMylong(long mylong) {
			this.mylong = mylong;
		}
		
		public short getMyshort() {
			return myshort;
		}
		public void setMyshort(short myshort) {
			this.myshort = myshort;
		}
		
		public byte getMybyte() {
			return mybyte;
		}
		public void setMybyte(byte mybyte) {
			this.mybyte = mybyte;
		}
		
		public char getMychar() {
			return mychar;
		}
		public void setMychar(char mychar) {
			this.mychar = mychar;
		}
		public float getMyfloat() {
			return myfloat;
		}
		public void setMyfloat(float myfloat) {
			this.myfloat = myfloat;
		}
		
		public double getMydouble() {
			return mydouble;
		}
		public void setMydouble(double mydouble) {
			this.mydouble = mydouble;
		}
		public boolean isMyboolean() {
			return myboolean;
		}
		public void setMyboolean(boolean myboolean) {
			this.myboolean = myboolean;
		}
		
	}
}
