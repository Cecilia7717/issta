import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
public class FailingTest {
	public static void main(String[] args) throws FileNotFoundException {
		String bugLine = null;
		String line;
		File files = new File (args[0]);
		HashSet<String> fail = new HashSet<String>();
		HashSet<String> pass = new HashSet<String>();
		HashSet<String> failWithoutEdges = new HashSet<String>();
		try (Scanner scan = new Scanner(files)) {
			
			//
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				if(line.length() >= 1) {
					//System.out.println(line);
					if(line.charAt(0) == 't') {
						boolean result;
						if(line.charAt(line.length() - 1) == 's') {
							result = true;
						}
						else {
							result = false;
						}
						line = line.substring(0, line.length() - 6);
						//System.out.println(line);// "test9995: *1 1.1" 
						boolean startReading = false;
						for(int i = 0; i < line.length(); i++) {
							//System.out.println(" ");
							if(startReading == true) {
								if(bugLine == null) {
									if(line.charAt(i) == '*') {
										int start = i;
										i = i + 1;
										while(i < line.length()) {
											if(!Character.isWhitespace(line.charAt(i))) {
												i++;
											}
											else {
												break;
											}
										}
										bugLine = line.substring(start + 1, i);
										if(result == true) {
											pass.add(line.substring(start+1, i));
											
										}
										else {
											fail.add(line.substring(start+1, i));
											boolean here = true;
											for(int j = 0; j<line.substring(start+1, i).length(); j ++) {
												if(line.substring(start+1, i).charAt(j) == '.') {
													here = false;
												}
											}
											if (here == true)
												failWithoutEdges.add(line.substring(start+1, i));
										}
									}
								}
								//for finding the bug line number
								
	
								if(i < line.length()) {
									if(line.charAt(i) != '*' && line.charAt(i) != ' ') {
										int start = i;
										i = i + 1;
										while(i < line.length()) {
											if(!Character.isWhitespace(line.charAt(i))) {
												i++;
											}
											else {
												break;
											}
										}
										//System.out.println("("+line.substring(start, i)+")");
										if(result == true) {
											pass.add(line.substring(start, i));
											
										}
										else {
											fail.add(line.substring(start, i));
											boolean here = true;
											for(int j = 0; j<line.substring(start, i).length(); j ++) {
												if(line.substring(start, i).charAt(j) == '.') {
													here = false;
												}
											}
											if (here == true)
												failWithoutEdges.add(line.substring(start, i));
										}
									}
								}
							}
							else {
								if(Character.isWhitespace(line.charAt(i))) {
									startReading = true;
								}
							}
						}
					}
				}
			}
			
			for (String i : pass) {
	            fail.remove(i);
	            if(!i.contains(".")) {
		            failWithoutEdges.remove(i);
	            }
			}
		}
		
		System.out.println("The bug is on line " + bugLine);
		System.out.println(fail);
		System.out.println("which has "+ fail.size() + " lines");
		System.out.println("No edges: "+ failWithoutEdges);
		System.out.println("which has "+ failWithoutEdges.size() + " lines");
	}
}
