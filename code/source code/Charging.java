
public class Charging {
	public static int utility(int n) {
		//if using > 100, need to charge for $5 per unit;
		//if using <= 100, but >50, need to charge for $4 per unit;
		//if using <=50, need to charge for $3 per unit
		System.out.print("7 ");int a=0; 
		System.out.print("8 ");if(n>100) {
			System.out.print("9 ");a = 5*n;
		}
		else if(print("10 ") && (n<=100&&n>50)) {
			print("11 ");a = 4*n;
		}
		else if(print("12 ")&&(n<=50)) {
			print("13 ");a=2*n;//suppose to be 3n
		}
		System.out.print("14 ");return a;
	}
	public static int utility1(int n) {
		//if using > 100, need to charge for $5 per unit;
		//if using <= 100, but >50, need to charge for $4 per unit;
		//if using <=50, need to charge for $3 per unit
		System.out.print("7 ");int a=0; 
		System.out.print("8 ");if(n>=100) {
			System.out.print("9 ");a = 5*n;//suppose to be >100 instead of >=100
		}
		else if(print("10 ") && (n<=100&&n>50)) {
			print("11 ");a = 4*n;
		}
		else if(print("12 ")&&(n<=50)) {
			print("13 ");a=3*n;
		}
		System.out.print("14 ");return a;
	}
	public static int utility2(int n) {
		//if using > 100, need to charge for $5 per unit;
		//if using <= 100, but >50, need to charge for $4 per unit;
		//if using <=50, need to charge for $3 per unit
		System.out.print("7 ");int a=0;
		System.out.print("8 ");if(n>100) {
			System.out.print("9 ");a = 5*n;
		}
		else if(print("10 ") && (n<=100&&n>50)) {
			print("11 ");a = 3*n;//suppose to be 4*n
		}
		else if(print("12 ")&&(n<=50)) {
			print("13 ");a=3*n;
		}
		System.out.print("14 ");return a;
	}
	public static int utility3(int n) {
		//if using > 100, need to charge for $5 per unit;
		//if using <= 100, but >50, need to charge for $4 per unit;
		//if using <=50, need to charge for $3 per unit
		System.out.print("7 ");int a=0;
		System.out.print("8 ");if(n>100) {
			System.out.print("9 ");a = 5*n;
		}
		else if(print("10 ") && (n<=100&&n>50)) {
			print("11 ");a = 4*n;
		}
		else if(print("12 ")&&(n<=50)) {
			print("13 ");a=3*n;
		}
		System.out.print("14 ");return a*0;//should be a;
	}
	public static boolean print(String a) {
		System.out.print(a);
		return true;
	}
}
