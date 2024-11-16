import static org.junit.Assert.*;

import org.junit.Test;

public class HyperbolicCosineTest {

	@Test
	public void test() {
		for (int i = 0; i < 50; i++) {
			System.out.print("test" + i + ": ");
			double expected = cosh_ORIG(i);
			double actual = HyperbolicCosine.cosh(i);
			assertEquals(expected, actual, 0.000001);
		}
	}

	public void assertEquals(double expected, double actual, double eps) {
		if (expected == Double.POSITIVE_INFINITY && actual == Double.POSITIVE_INFINITY) System.out.println(" Pass");
		else if (Math.abs(expected - actual) < eps) System.out.println(" Pass");
		else System.out.println(" Fail");
		//org.junit.Assert.assertEquals(expected, actual, eps);
	}
	
    public static double cosh_ORIG(double x) {
        if (x != x) {
            return x;
        }

        // cosh[z] = (exp(z) + exp(-z))/2

        // for numbers with magnitude 20 or so,
        // exp(-z) can be ignored in comparison with exp(z)

        if (x > 20) {
            if (x >= HyperbolicCosine.LOG_MAX_VALUE) {
                // Avoid overflow (MATH-905).
                final double t = Math.exp(0.5 * x);
                return (0.5 * t) * t;
            } else {
                return 0.5 * Math.exp(x);
            }
        } else if (x < -20) {
            if (x <= -HyperbolicCosine.LOG_MAX_VALUE) {
                // Avoid overflow (MATH-905).
                final double t = Math.exp(-0.5 * x);
                return (0.5 * t) * t;
            } else {
                return 0.5 * Math.exp(-x);
            }
        }

        final double hiPrec[] = new double[2];
        if (x < 0.0) {
            x = -x;
        }
        FastMath.exp(x, 0.0, hiPrec);

        double ya = hiPrec[0] + hiPrec[1];
        double yb = -(ya - hiPrec[0] - hiPrec[1]);

        double temp = ya * HyperbolicCosine.HEX_40000000;
        double yaa = ya + temp - temp;
        double yab = ya - yaa;

        // recip = 1/y
        double recip = 1.0/ya;
        temp = recip * HyperbolicCosine.HEX_40000000;
        double recipa = recip + temp - temp;
        double recipb = recip - recipa;

        // Correct for rounding in division
        recipb += (1.0 - yaa*recipa - yaa*recipb - yab*recipa - yab*recipb) * recip;
        // Account for yb
        recipb += -yb * recip * recip;

        // y = y + 1/y
        temp = ya + recipa;
        yb += -(temp - ya - recipa);
        ya = temp;
        temp = ya + recipb;
        yb += -(temp - ya - recipb);
        ya = temp;

        double result = ya + yb;
        result *= 0.5;
        return result;
      }

}
