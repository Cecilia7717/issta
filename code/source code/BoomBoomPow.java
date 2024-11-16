
public class BoomBoomPow {

	   /**
     * Power function.  Compute x^y.
     *
     * @param x   a double
     * @param y   a double
     * @return double
     */
    public static double pow(final double x, final double y) {

        printf("1");if (y == 0) {
            // y = -0 or y = +0
        	printf("2");return 1.0;
        } else {

        	printf("3");final long yBits        = Double.doubleToRawLongBits(y);
        	printf("4");final int  yRawExp      = (int) ((yBits & MASK_DOUBLE_EXPONENT) >> 52);
        	printf("5");final long yRawMantissa = yBits & MASK_DOUBLE_MANTISSA;
        	printf("6");final long xBits        = Double.doubleToRawLongBits(x);
        	printf("7");final int  xRawExp      = (int) ((xBits & MASK_DOUBLE_EXPONENT) >> 52);
        	printf("8");final long xRawMantissa = xBits & MASK_DOUBLE_MANTISSA;

        	printf("9");if (yRawExp > 1085) {
                // y is either a very large integral value that does not fit in a long or it is a special number

        		printf("10");if ((yRawExp == 2047 && yRawMantissa != 0) ||
                    (xRawExp == 2047 && xRawMantissa != 0)) {
                    // NaN
        			printf("11");return Double.NaN;
                } else if (printf("12") && xRawExp == 1023 && xRawMantissa == 0) {
                    // x = -1.0 or x = +1.0
                	printf("13");if (yRawExp == 2047) {
                        // y is infinite
                		printf("14");return Double.NaN;
                    } else {
                        // y is a large even integer
                    	printf("15");return 1.0;
                    }
                } else {
                    // the absolute value of x is either greater or smaller than 1.0

                    // if yRawExp == 2047 and mantissa is 0, y = -infinity or y = +infinity
                    // if 1085 < yRawExp < 2047, y is simply a large number, however, due to limited
                    // accuracy, at this magnitude it behaves just like infinity with regards to x
                	printf("16");if ((y > 0) ^ (xRawExp < 1023)) {
                        // either y = +infinity (or large engouh) and abs(x) > 1.0
                        // or     y = -infinity (or large engouh) and abs(x) < 1.0
                		printf("17");return Double.POSITIVE_INFINITY;
                    } else {
                        // either y = +infinity (or large engouh) and abs(x) < 1.0
                        // or     y = -infinity (or large engouh) and abs(x) > 1.0
                    	printf("18");return +0.0;
                    }
                }

            } else {
                // y is a regular non-zero number

            	printf("19");if (yRawExp >= 1023) {
                    // y may be an integral value, which should be handled specifically
            		printf("20");final long yFullMantissa = IMPLICIT_HIGH_BIT | yRawMantissa;
            		printf("21");if (yRawExp < 1075) {
                        // normal number with negative shift that may have a fractional part
            			printf("22");final long integralMask = (-1L) << (1075 - yRawExp);
            			printf("23");if ((yFullMantissa & integralMask) == yFullMantissa) {
                            // all fractional bits are 0, the number is really integral
            				printf("24");final long l = yFullMantissa >> (1075 - yRawExp);
            				printf("25");return FastMath.pow(x, (y < 0) ? -l : l);
                        }
                    } else {
                        // normal number with positive shift, always an integral value
                        // we know it fits in a primitive long because yRawExp > 1085 has been handled above
                    	printf("26");final long l =  yFullMantissa << (yRawExp - 1075);
                    	printf("27");return FastMath.pow(x, (y < 0) ? -l : l);
                    }
                }

                // y is a non-integral value

            	printf("28");if (x == 0) {
                    // x = -0 or x = +0
                    // the integer powers have already been handled above
            		printf("29");return y < 0 ? Double.POSITIVE_INFINITY : +0.0;
                } else if (printf("30") && xRawExp == 2047) {
                	printf("31");if (xRawMantissa == 0) {
                        // x = -infinity or x = +infinity
                		printf("32");return (y < 0) ? +0.0 : Double.POSITIVE_INFINITY;
                    } else {
                        // NaN
                    	printf("33");return Double.NaN;
                    }
                } else if (printf("34") && x < 0) {
                    // the integer powers have already been handled above
                	printf("35");return Double.NaN;
                } else {

                    // this is the general case, for regular fractional numbers x and y

                    // Split y into ya and yb such that y = ya+yb
                	printf("36");final double tmp = y * HEX_40000000;
                	printf("37");final double ya = (y + tmp) - tmp;
                	printf("38");final double yb = y - ya;

                    /* Compute ln(x) */
                	printf("39");final double lns[] = new double[2];
                	printf("40");final double lores = FastMath.log(x, lns);
                	printf("41");if (Double.isInfinite(lores)) { // don't allow this to be converted to NaN
                		printf("42");return lores;
                    }

                	printf("43");double lna = lns[0];
                	printf("44");double lnb = lns[1];

                    /* resplit lns */
                	printf("45");final double tmp1 = lna * HEX_40000000;
                	printf("46");final double tmp2 = (lna + tmp1) - tmp1;
                	printf("47");lnb += lna - tmp2;
                	printf("48");lna = tmp2;

                    // y*ln(x) = (aa+ab)
                	printf("49");final double aa = lna * ya;
                	printf("50");final double ab = lna * yb + lnb * ya + lnb * yb;

                	printf("51");lna = aa + ab;
                	printf("52");lnb = -(lna - aa - ab);

                	printf("53");double z = 1.0 / 120.0;
                	printf("54");z = z * lnb + (1.0 / 24.0);
                	printf("55");z = z * lnb + (1.0 / 6.0);
                	printf("56");z = z * lnb + 0.5;
                	printf("57");z = z * lnb + 1.0;
                	printf("58");z *= lnb;

                	printf("59");final double result = FastMath.exp(lna, z, null);
                    //result = result + result * z;
                	printf("60");return result;

                }
            }

        }

    }
    
    private static boolean printf(String string) {
		System.out.print(" " + string);
		return true;
		
	}

	/** Mask used to extract exponent from double bits. */
    private static final long MASK_DOUBLE_EXPONENT = 0x7ff0000000000000L;

    /** Mask used to extract mantissa from double bits. */
    private static final long MASK_DOUBLE_MANTISSA = 0x000fffffffffffffL;

    /** Mask used to add implicit high order bit for normalized double. */
    private static final long IMPLICIT_HIGH_BIT = 0x0010000000000000L;


    /**
     * 0x40000000 - used to split a double into two parts, both with the low order bits cleared.
     * Equivalent to 2^30.
     */
    private static final long HEX_40000000 = 0x40000000L; // 1073741824L

}
