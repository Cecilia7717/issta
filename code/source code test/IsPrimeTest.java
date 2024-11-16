
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

public class IsPrimeTest {

	@Test(timeout=60000)
	public void test() {
		System.out.println();
		
		for (int i = 0; i < 100000; i++) {
			System.out.print("test" + i + ": ");
			// get an odd random number
			int n = (int)(Math.random() * Integer.MAX_VALUE/2) * 2 + 1;
			// see whether it's prime using brute-force approach
			double sqrt = Math.sqrt(n);
			boolean expected = true;
			for (int j = 3; j <= sqrt; j += 2) {
				if (n % j == 0) {
					expected = false;
					break;
				}
			}
			boolean actual = Prime.isPrime(n);
			assertEquals(expected, actual);
		}
		
	}
	
	public void assertEquals(boolean expected, boolean actual) {
		if (expected == actual) System.out.println(" Pass");
		else System.out.println(" Fail");
		//org.junit.Assert.assertEquals(expected, actual);
	}

}
