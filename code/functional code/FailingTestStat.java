import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class FailingTestStat {
	public static void main(String args[]) throws IOException {
        if (args.length == 0) {
            System.out.println("There is no file name passed.");
            return;
        }
        else {
        	Scanner scan = new Scanner(new File(args[0]));
            int empty = 0;
            int contains = 0;
            int notcontains = 0;
            int total = 0;
            int totalLine = 0;
            String linebefore = "";
            String bugLine = "-1";
            int notcontainsline = 0;
            while(scan.hasNextLine()) {
            	String line = scan.nextLine();
            	if(line.contains("_")) {
            		linebefore = line;
            	}
            	if(line.contains("The bug")) {
            		bugLine = "-1";
            		//System.out.println(line.substring(19));
            		if(!line.contains("null")) {
                		bugLine = line.substring(19);
                		total++;
            		}
            	}
            	if (line.contains("No edges:")) {
            		if(line.length() == 12) {
            			if(!bugLine.equals("-1")) {
            				empty++;
                			//System.out.println(linebefore);
                			System.out.println(bugLine);
                			System.out.println("");
            			}
            		}
            		else {
            			if(!bugLine.equals("-1")) {
            				if(line.contains(bugLine)) {
                				contains++;
                				totalLine++;
                				for(int i = 0; i < line.length(); i++) {
                					if(line.charAt(i) == ',') {
                						totalLine++;
                					}
                				}
                			}
            				else {
            					notcontains++;
            					notcontainsline++;
            					for(int i = 0; i < line.length(); i++) {
                					if(line.charAt(i) == ',') {
                						notcontainsline++;
                					}
                				}
                    			System.out.println(linebefore +" [not contains]");
                    			System.out.println(bugLine);
                    			System.out.println("");
            				}
            			}
            		}
            	}
            }
            System.out.println("There are total " + total +" valid test");
            System.out.println(empty + " have returned empty set");
            System.out.println(contains + " have returned set that contains the bugline");
            System.out.println(notcontains + " have returned set that not contains the bugline");
            System.out.println("");
            System.out.println("for the set that contains the bugline, \nthe average number of lines needed to read is " + totalLine/contains);
            System.out.println("the total number is " + totalLine);
            System.out.println("");
            System.out.println("for the set that not contains the bugline,");
            System.out.println("the total number is " + notcontainsline);
        }
	}
}