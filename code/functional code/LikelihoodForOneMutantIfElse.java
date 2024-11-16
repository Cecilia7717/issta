import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LikelihoodForOneMutantIfElse {
	private static int passed;
	private static int failed;
	private static String bugLineNumber;
	private static double max;
	private static int doubleNum;
	
	public LikelihoodForOneMutantIfElse(int passed, int failed, String bugLineNumber, double max, int doubleNum) {
		LikelihoodForOneMutantIfElse.passed = passed;
		LikelihoodForOneMutantIfElse.failed = failed;
		LikelihoodForOneMutantIfElse.bugLineNumber = bugLineNumber;
		LikelihoodForOneMutantIfElse.max = max;
		LikelihoodForOneMutantIfElse.doubleNum = doubleNum;
	}
	public int getPassed() {
		return passed;
	}
	
	public int getFailed() {
		return failed;
	}
	
	public int getDoubleNum() {
		return doubleNum;
	}
	
	public String getBugLineNumber() {
		return bugLineNumber;
	}
	
	public double getMax() {
		return max;
	}
	
	public void setMax(double max) {
		LikelihoodForOneMutantIfElse.max = max;
	}
	
	public void setDoubleNum(int doubleNum) {
		LikelihoodForOneMutantIfElse.doubleNum = doubleNum;
	}
	
	public void setBugLineNumber(String bugLineNumber) {
		LikelihoodForOneMutantIfElse.bugLineNumber = bugLineNumber;
	}
	
	public void setPassed(int passed) {
		LikelihoodForOneMutantIfElse.passed = passed;
	}
	
	public void setFailed(int failed) {
		LikelihoodForOneMutantIfElse.failed = failed;
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		String NameOfFile = args[0];
		String[][] array = read(NameOfFile);
		generate(array);
		print(array);
	}
	
	
	public static String[][] read(String filename) throws FileNotFoundException{
		failed = 0;
		passed = 0;
		boolean re = true;
		Scanner sc = new Scanner(new File (filename));
		String[][] array = new String[1200][4];
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.length()>0&&line.charAt(0)!='t') {
				
			}
			else {
				String lineNum = "0";
				for(int i = 0; i<array.length; i ++) {
					array[i][1] = null;
				}
				//remove the part "test1:  "
				if(line.length()>=4) {
					if(line.charAt(4) == ':') {
						line = line.substring(7,line.length());
					}
					else if(line.charAt(5) == ':') {
						line = line.substring(8,line.length());
					}
					else if(line.charAt(6) == ':') {
						line = line.substring(9,line.length());
					}
					else if(line.charAt(7) == ':') {
						line = line.substring(10,line.length());
					}
				}

				
				//define pass or fail
				if(line.length()>0) {
					if(line.charAt(line.length()-1)=='l') {
						//for fail
						failed = failed + 1;
						re = false;
					}
					else {
						//for pass 
						passed = passed + 1;
					}
				}
				
				
				//line number
				int i = 0;
				while (i < line.length() - 4) {
					if (line.charAt(i)== '*') {
						//find the ending point
						for (int j = i; j<line.length()-4;j++) {
							if(line.charAt(j)==' ') {
								bugLineNumber = line.substring(i+1,j);
								lineNum = bugLineNumber;
								break;
							}
						}
					}
					else {
						for (int j = i; j<line.length()-4;j++) {
							if(line.charAt(j)==' ') {
								lineNum = line.substring(i,j);
								i = i + lineNum.length();
								break;
							}
						}
					}
					
					//put the line number inside the array
					for(int i2 = 0; i2<array.length; i2++) {
						if (array[i2][0]!=null) {
							if(array[i2][0].equals(lineNum)) {
								if(array[i2][1] != null) {
									break;
								}
								else if(array[i2][1]==null){
									array[i2][0] = lineNum;
									array[i2][1] = "takenInThisLine";
									if(re == false) {
										array[i2][3] = array[i2][3]+'a';
									}
									if(re == true) {
										array[i2][2] = array[i2][2]+'a';
									}
									break;
								}
							}
						}
						else if(array[i2][0] == null) {
								array[i2][0] = lineNum;
								array[i2][1] = "takenInThisLine";
								if(re == false) {
									array[i2][3] = array[i2][3]+'a';
								}
								if(re == true) {
									array[i2][2] = array[i2][2]+'a';
								}
								break;
							}
					}
					i = i+1;
				}
			}
		}
		return array;
	}
	
	public static String[][] generate(String[][]array) {
		max = 0.0;
		for(int m = 0; m<array.length; m ++) {
			int passedInThisCase = 0;
			int failedInThisCase = 0;
			//if it has passed value
			if(array[m][2]!=null) {
				passedInThisCase = array[m][2].length() - 4;
			}
			if(array[m][3]!=null) {
				failedInThisCase = array[m][3].length() - 4;
			}
			
			array[m][2] = Integer.toString(passedInThisCase);
			array[m][3] = Integer.toString(failedInThisCase);
		}
		return array;
	}
	
	public static void print(String[][] array) {
		if(passed == 0) {
			System.out.println("no test passed");
		}
		else if(failed == 0) {
			System.out.println("no test failed");
		}
		else {
			double[][] result = new double [array.length][2];
			for(int m = 0; m<array.length; m ++) {
				int passedInThisCase = Integer.valueOf(array[m][2]);
				int failedInThisCase = Integer.valueOf(array[m][3]);
				double passedRate = 100*passedInThisCase/passed;
				double failedRate = 100*failedInThisCase/failed;
				double finalRate = (1 - passedRate/(passedRate+failedRate))*100;
				if(finalRate>max) {
					max = finalRate;
				}
				array[m][1] =finalRate+"";
				//relatively value and not ranked
				if(array[m][0]!=null&&!array[m][0].isEmpty()) {/////////error here
					if(array[m][1]!=null&&!array[m][1].isEmpty()) {//////////error here
						result[m][0] = Double. valueOf(array[m][0]);//error here 
						result[m][1] = Double. valueOf(array[m][1]);
					}
				}
				//System.out.println("Line "+array[m][0]+" has "+array[m][1]+"% of containing the bug");
			}
			abo(result);
		}
	}
	
	public static void abo (double[][]array){
		for(int i = 0; i<array.length; i++) {
			array[i][1] = array[i][1]*100/max;
		}
		rank(array);
	}
	
	public static void rank (double[][] array) {
		//sort the array
		for(int i = 0; i< array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				double temp;
				double tempForLineNum;
				if(array[j][1]<array[i][1]) {
					temp = array[i][1];
					tempForLineNum = array[i][0];
					
					array[i][1] = array[j][1];
					array[i][0] = array[j][0];
					
					array[j][1] = temp;
					array[j][0] = tempForLineNum;
				}
			}
		}
		boolean ifelse = false;
		for(int i = array.length-1; i>=0; i--) {
			if(array[i][0] != 0.0) {
				String tmpBugLine = bugLineNumber+".0";
				//10.0
				String tmp = (Double.toString(array[i][0]).substring(0,(Double.toString(array[i][0]).length()-2)));
				//System.out.print(tmp);
				if(tmp.equals(bugLineNumber)) {
					//10.0 10.1 10.2
					int length = (Double.toString(array[i][0]).length());
					//System.out.println(Double.toString(array[i][0]).charAt(length-1));
					if(Double.toString(array[i][0]).charAt(length-1)!='0') {
						ifelse = true;
					}
				}
			}
		}
		
		if(ifelse == false) {
			System.out.println("not in if-else condition");
			return;
		}
		System.out.println("Bug contains on line "+bugLineNumber);
		for(int i = array.length-1; i>=0; i--) {
			if(array[i][0] != 0.0) {
				String possibility = Double.toString(array[i][1]);
				
				if(possibility!="0.0"&&possibility!="NaN") {
				System.out.println("Line "+array[i][0]+" has "+(possibility)+"% possibility of containing the bug");
				}
			}
		}
	}
	
}
