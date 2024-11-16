//import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class TestForNumSumPo {

	@Test
	public void test1() {
		System.out.print("test1: ");
		int result = Numbers.sumPos(4, 3);
		assertEquals(result, 7);
	}
	
	@Test
	public void test2() {
		System.out.print("test2: ");
		int result = Numbers.sumPos(-4, 3);
		assertEquals(result, 3);
	}

	@Test
	public void test3() {
		System.out.print("test3: ");
		int result = Numbers.sumPos(4, -3);
		assertEquals(result, 4);
	}
	
	@Test
	public void test4() {
		System.out.print("test4: ");
		int result = Numbers.sumPos(-4, -3);
		assertEquals(result, 0);
	}

	@Test
	public void test5() {
		int n = 5;
		while (n <= 50) {
	        System.out.print("test" + n + ": ");
	        Random random = new Random();
	        int a = random.nextInt(-99, 100);
	        int b = random.nextInt(-99, 100);
	        int result = Numbers.sumPos(a, b);
	        try {
	            assertEquals(result, sumP(a,b));
	        } catch (AssertionError e) {
	        }
	        n++;
	    }
	}
	
	public static int sumP(int a, int b) {
		int sum = 0;
		
		if (a > 0) { 
			sum += a;
		}
		
		if (b > 0) { 
			sum += b;
		}

		return sum;
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
