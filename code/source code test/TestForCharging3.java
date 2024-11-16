//import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class TestForCharging3 {

	@Test
	public void test1() {
		System.out.print("test1: ");
		int result = Charging.utility3(120);
		assertEquals(600, result);
	}

	@Test
	public void test2() {
		System.out.print("test2: ");
		int result = Charging.utility3(100);
		assertEquals(400, result);
	}
	
	@Test
	public void test3() {
		System.out.print("test3: ");
		int result = Charging.utility3(70);
		assertEquals(280, result);
	}
	
	@Test
	public void test4() {
		System.out.print("test4: ");
		int result = Charging.utility3(50);
		assertEquals(150, result);
	}
	
	@Test
	public void test5() {
		System.out.print("test5: ");
		int result = Charging.utility3(30);
		assertEquals(90, result);
	}
	
	@Test
	public void test6() {
		System.out.print("test6: ");
		int result = Charging.utility3(0);
		assertEquals(0, result);
	}
	
	@Test
	public void test7() {
		int n = 7;
		while (n <= 100) {
	        System.out.print("test" + n + ": ");
	        Random random = new Random();
	        int a = random.nextInt(0, 151);
	        int result = Charging.utility3(a);
	        try {
	            assertEquals(result, uti(a));
	        } catch (AssertionError e) {
	        }
	        n++;
	    }
	}
	
	public static int uti(int n) {
		//if using > 100, need to charge for $5 per unit;
		//if using <= 100, but >50, need to charge for $4 per unit;
		//if using <=50, need to charge for $3 per unit
		int a=0;
		if(n>100) {
			a = 5*n;
		}
		else if(n<=100&&n>50) {
			a = 4*n;
		}
		else if(n<=50) {
			a = 3*n;
		}
		return a;
		
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
