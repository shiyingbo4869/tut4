package nz.ac.massey.cs.pp.tutorial4.id19023254;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleListTests {
	
	protected SimpleList list = null;
	
	@Test
	public void testEmpty() {
		assertEquals(0,list.getSize());
	}

	@Test
	public void testAddOne() {
		list.add("one");
		assertEquals(1,list.getSize());
	}
	
	@Test
	public void testAddTwo() {
		list.add("one");
		list.add("two");
		assertEquals(2,list.getSize());
	}
	
	@Test
	public void testAddMany() {
		for (int i=0;i<100;i++) {
			list.add("element"+i);
		}
		assertEquals(100,list.getSize());
	}
	
	@Test
	public void testAddALot() {
		for (int i=0;i<10000;i++) {
			list.add("element"+i);
		}
		assertEquals(10000,list.getSize());
	}
	
	@Test
	public void testAddTwoRemoveOne1() throws ElementNotFoundException {
		list.add("one");
		list.add("two");
		list.remove("two");
		assertEquals(1,list.getSize());
	}
	
	@Test
	public void testAddTwoRemoveOne2() throws ElementNotFoundException {
		list.add("one");
		list.add("two");
		list.remove("one");
		assertEquals(1,list.getSize());
	}
	
	@Test(expected = ElementNotFoundException.class)
	public void testAddTwoRemoveNonExisting() throws ElementNotFoundException {
		list.add("one");
		list.add("two");
		list.remove("three");
	}
	
	// identity !
	@Test
	public void testAddDuplicates1() {
		list.add("one");
		list.add("two");
		list.add("one");
		assertEquals(3,list.getSize());
	}
	
	// identity !
	@Test
	public void testAddDuplicates2() {
		list.add("one");
		list.add("two");
		list.add("two");
		assertEquals(3,list.getSize());
	}
	
	// identity !
	@Test
	public void testAddDuplicates3() {
		list.add("one");
		list.add("two");
		list.add("on"+"e");
		assertEquals(3,list.getSize());
	}
	
	// equality !
	@Test
	public void testAddDuplicates4() {
		list.add("one");
		list.add("two");
		list.add("tw"+"o");
		assertEquals(3,list.getSize());
	}

	@Test
	public void testContains1() {
		list.add("one");
		list.add("two");
		assertTrue(list.contains("one"));
	}
	
	@Test
	public void testContains2() {
		list.add("one");
		list.add("two");
		assertTrue(list.contains("two"));
	}
	@Test
	public void testContains3() {
		list.add("one");
		list.add("two");
		assertFalse(list.contains("three"));
	}



}
