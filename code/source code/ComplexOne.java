//6/19: two double digit int's addition
public class ComplexOne {
	public static int add(int a, int b, int c) {
		print("1 ");int result = 0;
		print("2 ");if(a==0&&b==0&&c==0) {
			print("3 ");result = 0;
		}
		else if(print("4 ")&&(a!=0||b!=0||c!=0)) {
			int firstLength=0;
			int secondLength=0;
			int thirdLength=0;

			String firstNum = "00";
			if(a!=0) {
				firstNum = Integer.toString(a);
				if(a<10&&a>-10) {
					if(a<0&&a>-10) {
						firstNum = "-0"+a;
					}
					else {
						firstNum = "0"+a;
					}
				}
				firstLength = firstNum.length();
			}
			else {
				firstLength = 2;
			}
			
			String secondNum = "00";
			if(b!=0) {
				secondNum = Integer.toString(b);
				if(b<10&&b>-10) {
					if(b<0&&b>-10) {
						secondNum = "-0"+b;
					}
					else {
						secondNum = "0"+b;
					}
				}
				secondLength = secondNum.length();
			}
			else {
				secondLength = 2;
			}
			
			String thirdNum = "00";
			if(c!=0) {
				thirdNum = Integer.toString(c);
				if(c<10&&c>-10) {
					if(c<0&&c>-10) {
						thirdNum = "-0"+a;
					}
					else {
						thirdNum = "0"+a;
					}
				}
				thirdLength = thirdNum.length();
			}
			else {
				thirdLength = 2;
			}
			int firstSec = Character.getNumericValue(firstNum.charAt(firstLength-1));
			int firstFi = Character.getNumericValue(firstNum.charAt(firstLength-2));
			int secondSec = Character.getNumericValue(secondNum.charAt(secondLength-1));
			int secondFi = Character.getNumericValue(secondNum.charAt(secondLength-2));
			int thirdSec = Character.getNumericValue(thirdNum.charAt(thirdLength-1));
			int thirdFi = Character.getNumericValue(thirdNum.charAt(thirdLength-2));
			
			print("5 ");if(a!=0) {
				print("6 ");if(a>0) {
					print("7 ");result = a;
				}
				else if(print("8 ")&&a<0) {
					print("9 ");result = result - firstSec - firstFi*10;
				}
			}
			
			print("10 ");if(b!=0) {
				print("11 ");if(b>0) {
					print("12 ");result = result+b;
				}
				else if(print("13 ")&&b<0) {
					print("14 ");result = result - secondSec*10 - secondFi;
					//bug here should be result = result - secondSec - secondFi*10;
				}
			}
			
			print("15 ");if(c!=0) {
				print("16 ");if(c>0) {
					print("17 ");result = result+c;
				}
				else if(print("18 ")&&c<0){
					print("19 ");result = result - thirdSec - thirdFi*10;
				}
			}
		}
		print("20 ");return result;
	}
	public static boolean print(String a) {
		System.out.print(a);
		return true;
	}
}
