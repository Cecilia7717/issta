import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DetermineRankingIfElse {
    public static void main(String args[]) throws IOException {
        if (args.length == 0) {
            System.out.println("There is no file name passed.");
            return;
        }
        String name = args[0];
        System.out.println("For those which is inside the if-else condition: ");
        System.out.println("with edges: ");
        print(generateWithEdge(name));
        System.out.println("without edges: ");
        print(generate(name));
    }

    public static String[] arrays = new String[10000];

    public static int[] generate(String fileName) throws IOException {
        Scanner scan = new Scanner(new File(fileName));
        int[] array = new int[4];
        String bugline = "";
        String lineNum = "";
        int rank = 0;
        String current = "100.0%";
        //int mutant = 1;
        while(scan.hasNextLine()) {
        	String line = scan.nextLine();
        	if(line.length()>0&&!line.isEmpty()) {
        		if (line.charAt(0)=='B') {
        				bugline = line.substring(20, line.length());
        				bugline = bugline +".0";
        				lineNum = "";
        				rank = 0;
        				current = "100.0%";
        		}
        		else if(line.charAt(0) == 'L'){
    				int finish = 1;
    				int finished = 1;
        			int space = 0;
        			for(int i = 0; i<line.length(); i++) {
        				if(space == 1) {
        					finish = i;
        				}
        				if(space == 3) {
        					finished = i;
        				}
        				if(line.charAt(i)==' ') {
        					space++;
        				}
        			}
        			lineNum = line.substring(5,finish);
        			String temp = (line.substring(finish+4,finished)).substring(1);
        			bugline = bugline.replaceAll("\\s", "");
        			lineNum = lineNum.replaceAll("\\s", "");
        			if(!bugline.equals(lineNum)){
        				if(temp.equals(current)) {
        				}
        				else {
        					current = temp;
        					rank++;
        				}
        			}
        			else {
        				if(rank>=4) {
        					rank = 3;
        				}
        				array[rank] ++;
        				bugline = "";
        			}
        		}
        	}
        }
        return array;
    }
    public static int[] generateWithEdge(String fileName) throws IOException {
        Scanner scan = new Scanner(new File(fileName));
        int[] array = new int[4];
        String bugline = "";
        String lineNum = "";
        int rank = 0;
        String current = "100.0%";
        //int mutant = 1;
        while(scan.hasNextLine()) {
        	String line = scan.nextLine();
        	if(line.length()>0&&!line.isEmpty()) {
        		if (line.charAt(0)=='B') {
        				bugline = line.substring(20, line.length());
        				lineNum = "";
        				rank = 0;
        				current = "100.0%";
        		}
        		else if(line.charAt(0) == 'L'){
    				int finish = 1;
    				int finished = 1;
        			int space = 0;
        			for(int i = 0; i<line.length(); i++) {
        				if(space == 1) {
        					finish = i;
        				}
        				if(space == 3) {
        					finished = i;
        				}
        				if(line.charAt(i)==' ') {
        					space++;
        				}
        			}
        			lineNum = line.substring(5,finish);
        			String temp = (line.substring(finish+4,finished)).substring(1);
        			String intLineNum = lineNum.substring(0, lineNum.length()-2);
        			bugline = bugline.replaceAll("\\s", "");
        			intLineNum = intLineNum.replaceAll("\\s", "");
        			if(!bugline.equals(intLineNum)){
        				if(temp.equals(current)) {
        				}
        				else {
        					current = temp;
        					rank++;
        				}
        			}
        			else {
        				if(rank>=4) {
        					rank = 3;
        				}
        				array[rank] ++;
        				bugline = "";
        			}
        		}
        	}
        }
        /*
        int fileNum = -1;
        double a = 0.0;
        int rank = 0;
        double forRank = 0.0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            int length = line.length();
            if (length == 0) {

            } else if (line.charAt(0) >= '0' && line.charAt(0) <= '9') {
                fileNum++;
                arrays[fileNum] = line;
            } else if (line.charAt(0) == 'R') {

            } else {
                if (line.charAt(0) == 'B') {

                    a = -1;

                    forRank = 100.0;
                    rank = 1;
                    String lineNumberString;
                    if(length == 19) {
                    	lineNumberString = line.substring(length - 4).trim();
                    }
                    else {
                    	lineNumberString = line.substring(length - 3).trim();
                    }

                    a = Double.parseDouble(lineNumberString);

                    array[fileNum][0] = a;
                }
                if (line.charAt(0) == 'L') {
                    double lines = 0;
                    if (line.charAt(8) != ':') {
                        lines = Character.getNumericValue(line.charAt(6)) + 10 * (Character.getNumericValue(line.charAt(5)))+0.1 * (Character.getNumericValue(line.charAt(8)));
                    } else {
                    	lines = Character.getNumericValue(line.charAt(5)) + 0.1 * (Character.getNumericValue(line.charAt(7)));
                    }
                    String sub;
                    if (lines > 9) {
                        sub = line.substring(45, length - 2);
                    } else {
                        sub = line.substring(44, length - 2);
                    }
                    double compare = Double.parseDouble(sub);
                    if (lines != a) {
                        if (compare < forRank) {
                            rank++;
                            forRank = compare;
                        }
                    } else {
                        if (compare < forRank) {
                            rank++;
                        }
                        array[fileNum][1] = rank;
                    }
                }
            }
        }
        */
        return array;
    }

    public static void print(int[] array) {
        int top1 = array[0];
        int top2 = array[1];
        int top3 = array[2];
        int more = array[3];
        /*
        for (int a = 0; a < array.length; a++) {
            if (array[a][1] != 0) {
                System.out.println(arrays[a]);
                System.out.print("Line " + array[a][0] + ": ");
                System.out.println("has top " + array[a][1] + " relative value");
                System.out.println(" ");
                if (array[a][1] == 1) {
                    top1++;
                } else if (array[a][1] == 2) {
                    top2++;
                } else if (array[a][1] == 3) {
                    top3++;
                } else {
                    more++;
                }
            }
        }
       */
        int total = top1 + top2 + top3 + more;
        System.out.println("There are " + (total) + " cases in total.");
        System.out.println(top1 + " are top 1. The rate is " + top1 * 100.0 / total + "%");
        System.out.println(top2 + " are top 2. The rate is " + top2 * 100.0 / total + "%");
        System.out.println(top3 + " are top 3. The rate is " + top3 * 100.0 / total + "%");
        System.out.println(more + " are ranked as or below top 4. The rate is " + more * 100.0 / total + "%");
    }
}