//import static org.junit.Assert.*;

import org.junit.Test;

public class TestForCharging2 {

	@Test
	public void test1() {
		System.out.print("test1: ");
		int result = Charging.utility2(120);
		assertEquals(600, result);
	}

	@Test
	public void test2() {
		System.out.print("test2: ");
		int result = Charging.utility2(100);
		assertEquals(400, result);
	}
	
	@Test
	public void test3() {
		System.out.print("test3: ");
		int result = Charging.utility2(70);
		assertEquals(280, result);
	}
	
	@Test
	public void test4() {
		System.out.print("test4: ");
		int result = Charging.utility2(50);
		assertEquals(150, result);
	}
	
	@Test
	public void test5() {
		System.out.print("test5: ");
		int result = Charging.utility2(30);
		assertEquals(90, result);
	}
	
	public static void assertEquals(int a, int b) {
		if(a==b) {
			System.out.println("Pass");
		}
		else {
			System.out.println("Fail");
		}
		org.junit.Assert.assertEquals(a,b);
	}
}
