import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ReadFileDe {
	private static int passed;
	private static int failed;
	private static double bugLineNumber;
	private static double max;
	private static int doubleNum;
	
	public ReadFileDe(int passed, int failed, double bugLineNumber, double max, int doubleNum) {
		ReadFileDe.passed = passed;
		ReadFileDe.failed = failed;
		ReadFileDe.bugLineNumber = bugLineNumber;
		ReadFileDe.max = max;
		ReadFileDe.doubleNum = doubleNum;
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
	
	public double getBugLineNumber() {
		return bugLineNumber;
	}
	
	public double getMax() {
		return max;
	}
	
	public void setMax(double max) {
		ReadFileDe.max = max;
	}
	
	public void setDoubleNum(int doubleNum) {
		ReadFileDe.doubleNum = doubleNum;
	}
	
	public void setBugLineNumber(double bugLineNumber) {
		ReadFileDe.bugLineNumber = bugLineNumber;
	}
	
	public void setPassed(int passed) {
		ReadFileDe.passed = passed;
	}
	
	public void setFailed(int failed) {
		ReadFileDe.failed = failed;
	}
	
   public static void main(String args[]) throws IOException {
	   passed = 0;
	   failed = 0;
	   bugLineNumber = 0;
	   max = 0;
	   doubleNum = 0;
       if (args.length == 0) {
           System.out.println("No file name is passed!");
           return; // Terminate the program
       }
       String name = args[0];
       double[][] array = generate(name);
       read(array);
   }
   
   public static void resetVariables() {
	    passed = 0;
	    failed = 0;
	    bugLineNumber = 0;
	    max = 0;
	    doubleNum = 0;
	}
   
   //count how many lines does the code have
   //changed 7/12 from Scanner to BufferedReader
   public static int numOfLine(String fileName) throws IOException {
	   resetVariables();
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	        int numOfLine = 0;
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.length() > 0 && line.charAt(0) == 't') {
	            	int test = 0;
         		    while(line.charAt(test)!=':') {
         			   test++;
         		    }
         		    line = line.substring(test+1);
	                for (int ch = 0; ch < (line.length() - 4); ch++) {
	                    if (line.charAt(ch) != ' ') {
	                        if (line.charAt(ch + 1) != ' ') {
	                            int currentNum = (10 * (line.charAt(ch) - '0')) + (line.charAt(ch + 1) - '0');
	                            if (numOfLine < currentNum) {
	                                numOfLine = currentNum;
	                            }
	                            ch++;
	                        } else {
	                            int currentNum = line.charAt(ch) - '0';
	                            if (numOfLine < currentNum) {
	                                numOfLine = currentNum;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        return numOfLine;
	    }
	}
   
   public static double[][] generate(String fileName) throws IOException {
	    passed = 0;
	    failed = 0;
	    int numsOfLine = numOfLine(fileName);
	    double[][] arrays = new double[100000][3];
	    
	    //set(initialize) the arrays 
	    for (int m = 0; m < arrays.length; m++) {
	        for (int n = 0; n < arrays[m].length; n++) {
	            arrays[m][n] = 0;
	        }
	    }
	    
	    try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
	        String line;
	        while ((line = read.readLine()) != null) {
	            if (line.length() > 0 && line.charAt(0) == 't') {
	            	if(line.charAt(line.length()-1)=='s') {
                 	   passed++;
                    }
                    
                    else if(line.charAt(line.length()-1)=='l') {
                 	   failed++;
                    }
         		   int test = 0;
         		   while(line.charAt(test)!=':') {
         			   test++;
         		   }
         		   line = line.substring(test+1); // delete "test1: "...
                    double[] pastInThisTestCase = new double[100000];
             	   for(int i = 0; i<pastInThisTestCase.length; i++) {
             		   pastInThisTestCase[i] = 0;
             	   }
             	   
                    for (int n = 0; n < line.length()-6; n++) {
                 	   if(line.charAt(n)=='*') {
                 		   if(line.charAt(n+2)=='.') {
                 			   bugLineNumber = (line.charAt(n + 1)-'0') + (line.charAt(n + 3) - '0')*0.1;
                 			   n++;
                 		   }
                 		   else if(line.charAt(n+3)=='.') {
                 			   bugLineNumber = (line.charAt(n + 2) - '0') + (line.charAt(n + 1) - '0') * 10 + (line.charAt(n + 4) - '0')*0.1; // convert ASCII digits to integer
                                n++;
                                n++;
                 		   }
                 		   else if (line.charAt(n+2) != ' ') {
                                //bugLineNumber = (line.charAt(n + 2) - '0') + (line.charAt(n + 1) - '0') * 10; // convert ASCII digits to integer
                                String numberString = line.substring(n + 1, n + 3);
                                bugLineNumber = Double.parseDouble(numberString);
                            } 
                            else {
                         	   bugLineNumber = line.charAt(n + 1) - '0'; // convert ASCII digit to integer
                            }
                 	   }
                        if (n > 0 && line.charAt(n - 1) != ' ') {
                     	   boolean bo = true;
                     	   double num = 0;
                     	   if(line.charAt(n)=='.') {
                 			   num = (line.charAt(n - 1)-'0') + (line.charAt(n + 1) - '0')*0.1;
                 			   n++;
                 		   }
                 		   else if((n+1)<line.length()&&(line.charAt(n+1)=='.')) {
                 			   num = (line.charAt(n) - '0') + (line.charAt(n - 1) - '0') * 10 + (line.charAt(n + 2) - '0')*0.1; // convert ASCII digits to integer
                                n++;
                                n++;
                 			   
                 		   }
                 		   else if (line.charAt(n) != ' ') {
                     		    int start = n;
                     		    while (n < line.length() && (Character.isDigit(line.charAt(n)) || line.charAt(n) == '.')) {
                     		        n++;
                     		    }
                     		    String numberString = line.substring(start, n);
                     		    num = Double.parseDouble(numberString);
                     		}
                            else {
                         	   if(line.charAt(n-1)!='0') {
                         		   num = line.charAt(n - 1) - '0'; // convert ASCII digit to integer
                         	   }
                            }
                     	   
                            // if we're not reading the space and num is within the valid range
                            if (num != 0 && num <= arrays.length) {
                         	   for(int i = 0; i<pastInThisTestCase.length; i++) {
                         		   if (num == pastInThisTestCase[i]) {
                         			   bo = false;
                         			   break;
                         		   }
                         	   }
                         	   if(bo == true) {
                         		   //store the number of the line
                         		   for(int i = 0; i<pastInThisTestCase.length; i++) {
                             		   if (pastInThisTestCase[i]==0) {
                             			   pastInThisTestCase[i] = num;
                             			   break;
                             		   }
                             	   }
                         		   
                         		   // if we're not reading the space and num is within the valid range
                                    if (num != 0 && ((int)num + doubleNum) <= arrays.length) {
                                 	   String numString = Double.toString(num);
                                 	   if(numString.charAt(numString.length()-2)=='.') {
                                 		   doubleNum ++;
                                 	   }
                                 	   
                                        // if this is a pass test
                                        if (line.charAt(line.length() - 1) == 's') {
                                     	   if(((int)num - 1 + doubleNum)<arrays.length&&((int)num - 1 + doubleNum)>=0) {
                                     		   arrays[(int)num - 1 + doubleNum][2] = num;
                                     		   arrays[(int)num - 1 + doubleNum][0]++;
                                     	   }
                                        }
                                        // if this is a fail test
                                        if (line.charAt(line.length() - 1) == 'l') {
                                     	   if(((int)num - 1 + doubleNum)<arrays.length&&((int)num - 1 + doubleNum)>=0) {
                                     		   arrays[(int)num - 1 + doubleNum][2] = num;
                                     		   arrays[(int)num - 1 + doubleNum][1]++;
                                     	   }
                                        }
                                        
                                    }

                         	   }
                            }
                        }
                    }
	            }
	        }
	    }
	    return arrays;
	}

   public static void read(double[][] array) {
       max = 0;
       if (passed == 0) {
           System.out.print("passed == 0");
       } else if (failed == 0) {
           System.out.print("failed == 0");
       } else {
           int m = array.length;
           double[][] lineNotOrdered = new double[m][3];
           for (int n = 0; n < m; n++) {
               if (array[n][0] != 0 || array[n][1] != 0) {
                   double passedRate = (array[n][0]) * 100 / passed;
                   double failedRate = (array[n][1]) * 100 / failed;
                   double havingBugs = 1 - (passedRate / (passedRate + failedRate));
                   if (Double.isNaN(havingBugs)) {
                       havingBugs = 0d;
                   }
                   lineNotOrdered[n][0] = havingBugs;
                   lineNotOrdered[n][2] = array[n][2];
                   if ((havingBugs) > max) {
                       max = havingBugs;
                   }
               }
           }
           list(lineNotOrdered);
       }
   }

   public static void list(double[][] array) {
	    HashMap<Double, Double> lineProbabilities = new HashMap<>();

	    for (int x = array.length - 1; x >= 0; x--) {
	        double probability = array[x][0];
	        if (probability != 0) {
	            double realValue = probability * 100;
	            double relativeValue = (realValue / max);
	            lineProbabilities.put(array[x][2], relativeValue);
	        }
	    }

	    System.out.println("Bug is on line " + bugLineNumber);
	    System.out.println("Relative value: ");

	    // Sort the line probabilities in descending order
	    lineProbabilities.entrySet()
	            .stream()
	            .sorted(Map.Entry.<Double, Double>comparingByValue().reversed()
	                    .thenComparing(Map.Entry.<Double, Double>comparingByKey()))
	            .forEach(entry -> System.out.println(
	                    "Line " + entry.getKey() + ": the possibility of having a bug is " + entry.getValue() + "%")
	            );
	}

}
