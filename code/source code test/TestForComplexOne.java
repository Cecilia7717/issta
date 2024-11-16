//import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class TestForComplexOne {

	@Test
	public void test1() {
		System.out.print("test1: ");
		int result = ComplexOne.add(10,20,-30);
		assertEquals(result, 0);
	}
	
	@Test
	public void test2() {
		System.out.print("test2: ");
		int result = ComplexOne.add(56,00,23);
		assertEquals(result, 79);
	}

	@Test
	public void test3() {
		System.out.print("test3: ");
		int result = ComplexOne.add(00,00,00);
		assertEquals(result, 0);
	}
	
	@Test
	public void test4() {
		System.out.print("test4: ");
		int result = ComplexOne.add(78,-11,43);
		assertEquals(result, 110);
	}

	@Test
	public void test5() {
		System.out.print("test5: ");
		int result = ComplexOne.add(43,-43,92);
		assertEquals(result, 92);
	}
	
	@Test
	public void test6() {
		int n = 6;
		while (n <= 20) {
	        System.out.print("test" + n + ": ");
	        Random random = new Random();
	        int a = random.nextInt(-99, 100);
	        int b = random.nextInt(-99, 100);
	        int c = random.nextInt(-99, 100);
	        int result = ComplexOne.add(a, b, c);
	        try {
	            assertEquals(result, (a + b + c));
	        } catch (AssertionError e) {
	        }
	        n++;
	    }
	}
	
	public static void assertEquals(int a, int b) {
		if (a == b) {
			System.out.println("Pass");
		}
		else {
			System.out.println("Fail");
		}
		org.junit.Assert.assertEquals(a,b);
	}


}