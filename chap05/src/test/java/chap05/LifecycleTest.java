package chap05;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LifecycleTest {
	public LifecycleTest() {
		System.out.println("new LifecycleTest");
	}

	@BeforeAll
	static void testSetUp() {
		System.out.println("setUp all test");
	}

	@BeforeEach
	void setUp() {
		System.out.println("setUp");
	}

	@Test
	void a() {
		System.out.println("A");
	}

	@Test
	void b() {
		System.out.println("B");
	}

	@AfterEach
	void tearDown() {
		System.out.println("tearDown");
	}

	@AfterAll
	static void testTearDown() {
		System.out.println("after all test");
	}
}
