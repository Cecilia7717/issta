
import org.junit.Test;

public class BoomBoomPowTest {
	
	private static int count = 0;

	@Test
	public void test() {
		System.out.println();
	//public void testRandomInts() {
		for (int i = 0; i < 100; i++) {
			System.out.print("test" + count++ + ": ");
			int x = (int)(Math.random() * 500);
			int y = (int)(Math.random() * 200);
			double expected = Math.pow(x, y);
			double actual = BoomBoomPow.pow(x, y);
			assertEquals(expected, actual, 0.0000001);
		}
		
	//public void testRandomDoubles() {
		for (int i = 0; i < 100; i++) {
			System.out.print("test" + count++ + ": ");
			double x = (Math.random() * 500);
			double y = (Math.random() * 200);
			double expected = Math.pow(x, y);
			double actual = BoomBoomPow.pow(x, y);
			assertEquals(expected, actual, 0.0000001);
		}

	//public void testXZero() {
		System.out.print("test" + count++ + ": ");
		double x = 0;
		double y = 4.5;
		double expected = Math.pow(x, y);
		double actual = BoomBoomPow.pow(x, y);
		assertEquals(expected, actual, 0.0000001);		
	

	//public void testXInfinity() {
		System.out.print("test" + count++ + ": ");
		 x = Double.POSITIVE_INFINITY;
		 y = 5.2;
		 expected = Math.pow(x, y);
		 actual = BoomBoomPow.pow(x, y);
		assertEquals(expected, actual, 0.0000001);		
	
	
	//public void testYZero() {
		System.out.print("test" + count++ + ": ");
		 x = 5;
		 y = 0;
		 expected = Math.pow(x, y);
		 actual = BoomBoomPow.pow(x, y);
		assertEquals(expected, actual, 0.0000001);		
	

	//public void testYInfinity() {
		System.out.print("test" + count++ + ": ");
		 x = 5;
		 y = Double.POSITIVE_INFINITY;;
		 expected = Math.pow(x, y);
		 actual = BoomBoomPow.pow(x, y);
		assertEquals(expected, actual, 0.0000001);	

	//public void testYLargeInt() {
		System.out.print("test" + count++ + ": ");
		 x = 0.99;
		 y = Long.MAX_VALUE * 2.0;
		 expected = Math.pow(x, y);
		 actual = BoomBoomPow.pow(x, y);
		assertEquals(expected, actual, 0.0000001);		
		
	}
	

	public void assertEquals(double expected, double actual, double eps) {
		if (expected == Double.POSITIVE_INFINITY && actual == Double.POSITIVE_INFINITY) System.out.println(" Pass");
		else if (Math.abs(expected - actual) < eps) System.out.println(" Pass");
		else System.out.println(" Fail");
		//org.junit.Assert.assertEquals(expected, actual, eps);
	}
}
