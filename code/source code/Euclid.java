
public class Euclid {
	
	public static int gcd(int x, int y) {
		
		print("1");int a, b;
		print("2");if (x > y) {
			print("3");a = x ;   
		}
		else {
			print("4");a = y;
			 // a is greater number
		}
		print("5");if (x < y) {
			print("6");b = x ;   
		}
		else {
			print("7");b = y;
			 // b is smaller number
		}
		print("8");int r = b; 
		
		while (print("9")&& a % b != 0) {  
			print("10");r = a % b;  
			print("11");a = b;  
			print("12");b = r;  
		}  
		print("13");return r;  
		 
		
		
	}

    private static boolean print(String string) {
		System.out.print(" " + string);
		return true;
		
	}
}
