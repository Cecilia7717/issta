
import org.junit.Test;

public class LogTest {
	
	private static int count = 0;
	private double x;
	private double[] hiPrec;

	@Test
	public void test() {
		System.out.println();
		/*
		for (int i = 0; i < 100; i++) {
			System.out.print("test" + count++ + ": ");
			int x = (int)(Math.random() * 500);
			int y = (int)(Math.random() * 200);
			double expected = Math.pow(x, y);
			double actual = BoomBoomPow.pow(x, y);
			assertEquals(expected, actual, 0.0000001);
		}
		 */		
		
		
		// x = 0
		x = 0;
		hiPrec = null;
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		
		// x negative
		x = -4;
		hiPrec = null;
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		
		x = -4;
		hiPrec = new double[2];
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		
		// x not a number
		x = 5 / 0.0;
		hiPrec = null;
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		
		x = 5 / 0.0;
		hiPrec = new double[2];
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		
		// x = pos infinity
		x = Double.POSITIVE_INFINITY;
		hiPrec = null;
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);

		x = Double.POSITIVE_INFINITY;
		hiPrec = new double[2];
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);

		// x = 1.0
		x = 1.0;
		hiPrec = null;
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		
		// regular number, hiPrec = null
		x = 10.0;
		hiPrec = null;
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		
		// regular number, hiPrec != null
		x = 10.0;
		hiPrec = new double[2];
		System.out.print("test" + count++ + ": ");
		assertEquals(FastMath.log(x, hiPrec), Log.log(x, hiPrec), 0.0);
		


	}
	

	public void assertEquals(double expected, double actual, double eps) {
		try {
			org.junit.Assert.assertEquals(expected, actual, eps);
			System.out.println(" Pass");
		}
		catch (Throwable e) {
			System.out.println(" Fail");
		}
	}
}
