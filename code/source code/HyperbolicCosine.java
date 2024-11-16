
public class HyperbolicCosine {
    /** StrictMath.log(Double.MAX_VALUE): {@value} */
    public static final double LOG_MAX_VALUE = StrictMath.log(Double.MAX_VALUE);
    public static final long HEX_40000000 = 0x40000000L; // 1073741824L


    public static double cosh(double x) {
        print("1");if (x != x) {
        	print("1.1");
        	print("2");return x;
        }
        else {
        	print("1.2");
        }

        print("3");if (x > 20) {
        	print("3.1");
        	print("4");if (x >= LOG_MAX_VALUE) {
        		print("4.1");
                // Avoid overflow (MATH-905).
        		print("5");final double t = Math.exp(0.5 * x);
        		print("6");return (0.5 * t) * t;
            } else {
            	print("4.2");
            	print("7");return 0.5 * Math.exp(x);
            }
        } else {
        	print("3.2");
        	print("8");if (x < -20) {
        		print("8.1");
        		print("9");if (x <= -LOG_MAX_VALUE) {
        			print("9.1");
	                // Avoid overflow (MATH-905).
        			print("10");final double t = Math.exp(-0.5 * x);
        			print("11");return (0.5 * t) * t;
	            } else {
	            	print("9.2");
	            	print("12");return 0.5 * Math.exp(-x);
	            }
        	}
        	else {
        		print("8.2");
        	}
        }

        print("13");final double hiPrec[] = new double[2];
        print("14");if (x < 0.0) {
        	print("14.1");
            x = -x;
        }
        else {
        	print("14.2");
        }

        print("15");FastMath.exp(x, 0.0, hiPrec);

        print("16");double ya = hiPrec[0] + hiPrec[1];
        print("17");double yb = -(ya - hiPrec[0] - hiPrec[1]);

        print("18");double temp = ya * HEX_40000000;
        print("19");double yaa = ya + temp - temp;
        print("20");double yab = ya - yaa;

        // recip = 1/y
        print("21");double recip = 1.0/ya;
        print("22");temp = recip * HEX_40000000;
        print("23"); double recipa = recip + temp - temp;
        print("24");double recipb = recip - recipa;

        // Correct for rounding in division
        print("25");recipb += (1.0 - yaa*recipa - yaa*recipb - yab*recipa - yab*recipb) * recip;
        // Account for yb
        print("26");recipb += -yb * recip * recip;

        // y = y + 1/y
        print("27");temp = ya + recipa;
        print("28");yb += -(temp - ya - recipa);
        print("29");ya = temp;
        print("30");temp = ya + recipb;
        print("31");yb += -(temp - ya - recipb);
        print("32");ya = temp;

        print("33");double result = ya + yb;
        print("34");result *= 0.5;
        print("35");return result;
      }

    private static boolean print(String string) {
		System.out.print(" " + string);
		return true;
		
	}
}
