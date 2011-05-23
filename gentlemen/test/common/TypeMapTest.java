package common;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class TypeMapTest {

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

	private TypeMap<Object> map;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		map = new TypeMap<Object>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTypeMap() {
		assertTrue(map != null);
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
		assertTrue(map.getItemCount() == 0);

		map.add(a);
		map.add(c);

		assertTrue(map.getItemCount() == 2);

		map.remove(c);

		assertTrue(map.getItemCount() == 1);
	}

	@Test
	public void testFind() {

		assertTrue(map.getItemCount() == 0);

		map.add(a);
		map.add(b1);
		map.add(b2);

		Collection<A> collectionA = map.find(A.class);
		assertTrue(collectionA.size() == 1);
		assertTrue(collectionA.contains(a));

		Collection<B> collectionB = map.find(B.class);
		assertTrue(collectionB.size() == 2);
		assertTrue(collectionB.contains(b1));
		assertTrue(collectionB.contains(b2));

		Collection<C> collectionC = map.find(C.class);
		assertTrue(collectionC.size() == 0);
	}

	@Test
	public void testGetItems() {
		assertTrue(map.getItems().size() == 0);

		map.add(a);
		map.add(b1);
		map.add(b2);

		assertTrue(map.getItems().size() == 3);

		Object[] objects = { a, b1, b2 };

		for (Object o : objects) {
			assertTrue(map.getItems().contains(o));
		}
	}

	@Test
	public void testClear() {
		assertTrue(map.getItemCount() == 0);

		map.add(a);
		map.add(b1);
		map.add(b2);

		assertTrue(map.getItemCount() == 3);

		map.clear();

		assertTrue(map.getItemCount() == 0);
	}
}
