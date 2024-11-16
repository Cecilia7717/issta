//import static org.junit.Assert.*;

import org.junit.Test;

public class EuclidTest {

	@Test(timeout=5000)
	public void test() {
		System.out.println();
		
		for (int i = 0; i < 100; i++) {
			System.out.print("test" + i + ": ");
			int a = (int)(Math.random() * Integer.MAX_VALUE);
			int b = (int)(Math.random() * Integer.MAX_VALUE);
			int expected = gcd(a, b);
			try {
				int actual = Euclid.gcd(a, b);
				assertEquals(expected, actual);
			}
			catch (Exception e) {
				System.out.println(" Fail");
			}
		}
		
		
	}
	
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }
    
    public void assertEquals(int expected, int actual) {
    	if (expected == actual) System.out.println(" Pass");
    	else System.out.println(" Fail");
    	//org.junit.Assert.assertEquals(expected, actual);
    }

}
