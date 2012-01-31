package com.googlecode.jutility.io;

import java.io.Serializable;
import junit.framework.Assert;
import org.junit.*;

public class CloneUtilTest {
	
	public CloneUtilTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void test() {
		A a = new A();
		B b = new B();
		C c = new C();
		c.setName("ccc");
		b.setName("bbb");
		b.setC(c);
		a.setName("aaa");
		a.setB(b);
		
		A anotherA = (A)a.clone();
		Assert.assertNotNull(anotherA);
		Assert.assertEquals("aaa", anotherA.getName());
		Assert.assertEquals("bbb", anotherA.getB().getName());
		Assert.assertEquals("ccc", anotherA.getB().getC().getName());
		
		a.setName("aaaaa");
		a.getB().setName("bbbbb");
		a.getB().getC().setName("ccccc");
		Assert.assertEquals("aaa", anotherA.getName());
		Assert.assertEquals("bbb", anotherA.getB().getName());
		Assert.assertEquals("ccc", anotherA.getB().getC().getName());
		
		anotherA.setName("aaaa");
		anotherA.getB().setName("bbbb");
		anotherA.getB().getC().setName("cccc");
		Assert.assertEquals("aaaaa", a.getName());
		Assert.assertEquals("bbbbb", a.getB().getName());
		Assert.assertEquals("ccccc", a.getB().getC().getName());
		Assert.assertEquals("aaaa", anotherA.getName());
		Assert.assertEquals("bbbb", anotherA.getB().getName());
		Assert.assertEquals("cccc", anotherA.getB().getC().getName());
	}
}

class A implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private B b;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public B getB() {
		return b;
	}
	public void setB(B b) {
		this.b = b;
	}
	@Override
	public Object clone() {
		return CloneUtil.clone(this);
	}
}

class B implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private C c;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public C getC() {
		return c;
	}
	public void setC(C c) {
		this.c = c;
	}
	@Override
	public Object clone() {
		return CloneUtil.clone(this);
	}
}

class C implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public Object clone() {
		return CloneUtil.clone(this);
	}
}
