
public class HyperbolicSine {
    /** StrictMath.log(Double.MAX_VALUE): {@value} */
    public static final double LOG_MAX_VALUE = StrictMath.log(Double.MAX_VALUE);
    public static final long HEX_40000000 = 0x40000000L; // 1073741824L


    public static double sinh(double x) {
    	print("1");boolean negate = false;
    	print("2");if (x != x) {
    		print("2.1");
    		print("3");return x;
        }
    	else {
    		print("2.2");
    	}

        // sinh[z] = (exp(z) - exp(-z) / 2

        // for values of z larger than about 20,
        // exp(-z) can be ignored in comparison with exp(z)

    	print("4");if (x > 20) {
    		print("4.1");
    		print("5");if (x >= LOG_MAX_VALUE) {
    			print("5.1");
                // Avoid overflow (MATH-905).
    			print("6");final double t = Math.exp(0.5 * x);
    			print("7");return (0.5 * t) * t;
            } else {
            	print("5.2");
            	print("8");return 0.5 * Math.exp(x);
            }
        } else {
        	print("4.2");
        	print("9");if (x < -20) {
        		print("9.1");
        		print("10");if (x <= -LOG_MAX_VALUE) {
        			print("10.1");
	                // Avoid overflow (MATH-905).
        			print("11");final double t = Math.exp(-0.5 * x);
        			print("12");return (-0.5 * t) * t;
	            } else {
	            	print("10.2");
	            	print("13");return -0.5 * Math.exp(-x);
	            }
	        }
        	else {
        		print("9.2");
        	}
        }

    	print("14");if (x == 0) {
    		print("14.1");
    		print("15");return x;
        }
        else {
        	print("14.2");
        }

    	print("16");if (x < 0.0) {
    		print("16.1");
    		print("17");x = -x;
    		print("18");negate = true;
        }
        else {
        	print("16.2");
        }

    	print("19");double result;

    	print("20");if (x > 0.25) {
    		print("20.1");
    		print("21");double hiPrec[] = new double[2];
    		print("22");FastMath.exp(x, 0.0, hiPrec);

    		print("23");double ya = hiPrec[0] + hiPrec[1];
    		print("24");double yb = -(ya - hiPrec[0] - hiPrec[1]);

    		print("25");double temp = ya * HEX_40000000;
    		print("26");double yaa = ya + temp - temp;
    		print("27");double yab = ya - yaa;

            // recip = 1/y
    		print("28");double recip = 1.0/ya;
    		print("29");temp = recip * HEX_40000000;
    		print("30");double recipa = recip + temp - temp;
    		print("31");double recipb = recip - recipa;

            // Correct for rounding in division
    		print("32");recipb += (1.0 - yaa*recipa - yaa*recipb - yab*recipa - yab*recipb) * recip;
            // Account for yb
    		print("33");recipb += -yb * recip * recip;

    		print("34");recipa = -recipa;
    		print("35");recipb = -recipb;

            // y = y + 1/y
    		print("36");temp = ya + recipa;
    		print("37");yb += -(temp - ya - recipa);
    		print("38");ya = temp;
    		print("39");temp = ya + recipb;
    		print("40");yb += -(temp - ya - recipb);
    		print("41");ya = temp;

    		print("42");result = ya + yb;
    		print("43");result *= 0.5;
        }
        else {
        	print("20.2");
        	print("44");double hiPrec[] = new double[2];
        	print("45");FastMath.expm1(x, hiPrec);

        	print("46");double ya = hiPrec[0] + hiPrec[1];
        	print("47");double yb = -(ya - hiPrec[0] - hiPrec[1]);

            /* Compute expm1(-x) = -expm1(x) / (expm1(x) + 1) */
        	print("48");double denom = 1.0 + ya;
        	print("49");double denomr = 1.0 / denom;
        	print("50");double denomb = -(denom - 1.0 - ya) + yb;
        	print("51");double ratio = ya * denomr;
        	print("52");double temp = ratio * HEX_40000000;
        	print("53");double ra = ratio + temp - temp;
        	print("54");double rb = ratio - ra;

        	print("55");temp = denom * HEX_40000000;
        	print("56");double za = denom + temp - temp;
        	print("57");double zb = denom - za;

        	print("58");rb += (ya - za*ra - za*rb - zb*ra - zb*rb) * denomr;

            // Adjust for yb
        	print("59");rb += yb*denomr;                        // numerator
        	print("60");rb += -ya * denomb * denomr * denomr;   // denominator

            // y = y - 1/y
        	print("61");temp = ya + ra;
        	print("62");yb += -(temp - ya - ra);
        	print("63");ya = temp;
        	print("64");temp = ya + rb;
        	print("65");yb += -(temp - ya - rb);
        	print("66");ya = temp;

        	print("67");result = ya + yb;
        	print("68");result *= 0.5;
        }

    	print("69");if (negate) {
    		print("69.1");
    		print("70");result = -result;
        }
        else {
        	print("69.2");
        }

    	print("71");return result;
      }

    private static boolean print(String string) {
		System.out.print(" " + string);
		return true;
		
	}
}
