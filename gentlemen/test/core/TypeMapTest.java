package core;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.TypeMap;

public class TypeMapTest {

	private static TypeMap<Object> map;

	private static class A {
	}

	private static class B {
	}

	private static class C {
	}

	private static A a = new A();
	private static B b1 = new B();
	private static B b2 = new B();
	private static C c = new C();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		map = new TypeMap<Object>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetItemCount() {
		assertTrue(map.getItemCount() == 0);
	}

	@Test
	public void testAdd() {
		// Add a
		map.add(a);

		assertTrue(map.getItemCount() == 1);

		// Add b1 4 times
		for (int i = 0; i < 4; i++) {
			map.add(b1);
		}

		assertTrue(map.getItemCount() == 2);

		// Add c
		map.add(c);

		assertTrue(map.getItemCount() == 3);

		// Add b2
		map.add(b2);

		assertTrue(map.getItemCount() == 4);
	}

	@Test
	public void testRemove() {
		assertTrue(map.getItemCount() == 4);

		map.remove(c);

		assertTrue(map.getItemCount() == 3);
	}

	@Test
	public void testFind() {

		List<A> listA = map.find(A.class);
		assertTrue(listA.size() == 1);
		assertTrue(listA.get(0) == a);

		List<B> listB = map.find(B.class);
		assertTrue(listB.size() == 2);
		assertTrue((listB.get(0) == b1 && listB.get(1) == b2)
				|| (listB.get(0) == b2 && listB.get(1) == b1));

		List<C> listC = map.find(C.class);
		assertTrue(listC.size() == 0);
	}

	@Test
	public void testGetItems() {
		assertTrue(map.getItems().size() == 3);

		Object[] objects = { a, b1, b2 };

		for (Object o : objects) {
			assertTrue(map.getItems().contains(o));
		}
	}

	@Test
	public void testClear() {
		assertTrue(map.getItemCount() == 3);

		map.clear();

		assertTrue(map.getItemCount() == 0);
	}
}
