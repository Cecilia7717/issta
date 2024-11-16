public class Numbers {
	public static boolean print(String a) {
		System.out.print(a);
		return true;
	}
	
	public static String positiveOdd(int n) {
		print("1 ");if(n>0) {
			print("2 ");if(n%2!=0) {
				print("3 ");return "Positive and odd";
			}
			else if(print("4 ")&&(n%2==0)){
				print("5 ");return "Positive and even";
			}
		}
		print("6 ");if(n<=0) {
			print("7 ");if(n%2!=0) {
				print("8 ");return "  "; //bug is here
			}
			else if(print("9 ")&&(n%2==0)){
				print("10 ");return "Negative and even";
			}
		}
		return "";
	}

    public static int sumPos(int x, int y) {
		
		print("1 ");int sum = 0;
		
		print("2 ");if (x > 0) { 
			print("3 ");sum += x;
		}
		
		print("4 ");if (y > 0) { 
			print("5 ");sum = y; // bug is here, should be sum += y
		}

		print("6 ");return sum;
	}
} 
