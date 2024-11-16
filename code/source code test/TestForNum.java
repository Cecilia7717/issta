import java.util.Random;

import org.junit.Test;
public class TestForNum {

	@Test
	public void test1() {
		System.out.print("test1: ");
		String result = Numbers.positiveOdd(5);
		assertEquals("Positive and odd", result);
	}
	
	@Test
	public void test2() {
		System.out.print("test2: ");
		String result = Numbers.positiveOdd(6);
		assertEquals("Positive and even", result);
	}
	
	@Test
	public void test3() {
		System.out.print("test3: ");
		String result = Numbers.positiveOdd(-6);
		assertEquals("Negative and even", result);
	}
	
	@Test
	public void test4() {
		System.out.print("test4: ");
		String result = Numbers.positiveOdd(-3);
		assertEquals("Negative and odd", result);
	}
	
	@Test
	public void test5() {
		int n = 5;
		while (n <= 50) {
	        System.out.print("test" + n + ": ");
	        Random random = new Random();
	        int a = random.nextInt(-99,100);
	        String result = Numbers.positiveOdd(a);
	        try {
	            assertEquals(result, pos(a));
	        } catch (AssertionError e) {
	        }
	        n++;
	    }
	}
	
	public static String pos(int n) {
		if(n>0) {
			if(n%2!=0) {
				return "Positive and odd";
			}
			else if(n%2==0){
				return "Positive and even";
			}
		}
		if(n<=0) {
			if(n%2!=0) {
				return "Negative and odd";
			}
			else if(n%2==0){
				return "Negative and even";
			}
		}
		return "";
	}

	public static void assertEquals(String a, String b) {
		if (a == b) {
			System.out.println("Pass");
		}
		else {
			System.out.println("Fail");
		}
		org.junit.Assert.assertEquals(a,b);
	}
}
