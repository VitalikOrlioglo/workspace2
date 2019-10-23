package app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KlasseTest {
	
	private Klasse klasse;

	@BeforeEach
	void setUp() throws Exception {
		klasse = new Klasse();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMaxIntInt() {
		int max = klasse.max(3, 5);
		assertEquals(5, max);
//		fail("Not yet implemented");
	}

	@Test
	void testMaxIntArray() {
		int max = klasse.max(3,55,24,42,-4,0,3,1);
		assertEquals(55, max);
	}

	@Test
	void testCreateList() {
//		assertTrue(true);
		try {
			List<String> list1 = klasse.createList("x");
			assertNull(list1);
			
			List<String> vlist = klasse.createList("v");
			assertTrue(vlist instanceof Vector);
			
			List<String> alist = klasse.createList("a");
			assertTrue(alist instanceof ArrayList);
		} catch (Exception e) {
			fail();
		}
		
	}

}
