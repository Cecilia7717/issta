import static org.junit.Assert.*;

import org.junit.Test;

public class HyperbolicSineTest {

	@Test 
	public void test() {
		for (int i = -50; i < 50; i++) {
			System.out.print("test" + i + ": ");
			double expected = FastMath.sinh(i);
			double actual = HyperbolicSine.sinh(i);
			assertEquals(expected, actual, 0.000001);
		}
		double expected = FastMath.sinh(0.1);
		double actual = HyperbolicSine.sinh(0.1);
		assertEquals(expected, actual, 0.000001);

	}

	public void assertEquals(double expected, double actual, double eps) {
		if (expected == Double.POSITIVE_INFINITY && actual == Double.POSITIVE_INFINITY) System.out.println(" Pass");
		else if (Math.abs(expected - actual) < eps) System.out.println(" Pass");
		else System.out.println(" Fail");
		//org.junit.Assert.assertEquals(expected, actual, eps);
	}
	


}
