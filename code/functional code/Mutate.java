import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Mutate {
	
	public static List<String> comparisonOps = List.of("<", "<=", ">", ">=", "==", "!=");
	public static List<String> arithmeticOps = List.of("+", "-", "*", "/", "%");
	public static List<String> booleanOps = List.of("||", "&&");
	
 
	
	public static void main(String[] args) {
		
		try {
			String source = "src/Euclid.java"; // args[0]
			List<String> origLines = readOriginal(source);
			
			//System.out.println(origLines);
			
			List<Mutation> mutations = mutate(origLines);
			
			File root = new File("mutants/");
			/*
			for (File subdir : root.listFiles()) {
				for (File file : subdir.listFiles()) {
					if (file.delete() == false) {
						System.out.println("Error occurred deleting directory! " + file.getName());
						System.exit(0);
					}
					
				}
				if (subdir.delete() == false) {
					System.out.println("Error occurred deleting directory! " + subdir.getName());
					System.exit(0);
				}
			}
			if (root.delete() == false) {
				System.out.println("Error occurred deleting root directory!");
				System.exit(0);
			}
			
			if (root.mkdir() == false) {
				System.out.println("Error occurred creating root directory!");
				System.exit(0);
			}
			*/
			
			
			
			int counter = 0;
			for (Mutation m : mutations) {
				//System.out.println(m);
				File dir = new File("mutants/" + m.lineNumber + "_" + counter + "/");
				if (dir.mkdir() == false) {
					System.out.println("Error occurred creating directory!");
					System.exit(0);
				}
				PrintWriter out = new PrintWriter(new FileWriter("mutants/" + m.lineNumber + "_" + counter + "/Euclid.java"), true);
				for (int i = 0; i < origLines.size(); i++) {
					if (i + 1 == m.lineNumber) {
						String orig = origLines.get(i);
						String mutatedLine = orig.replace(m.origCode, m.mutatedCode);
						//System.out.println(orig + " -> " + mutatedLine);
						mutatedLine = mutatedLine.replace("print(\"", "print(\"*");
						out.println(mutatedLine);
					}
					else {
						out.println(origLines.get(i));
					}
				}
				out.println("// " + m);
				out.flush();
				out.close();
				counter++;
			}
			System.out.println("Done creating " + mutations.size() + " versions of code");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static List<Mutation> mutate(List<String> lines) {
		
		List<Mutation> mutations = new LinkedList<>();
		
		int lineNumber = 0;
		
		for (String line : lines) {
			lineNumber++;
			
			String[] statements = line.trim().split(";");

			// ignore any line that doesn't start with "print" followed by a semicolon 
			if (statements.length < 2) continue;			
			if (statements[0].trim().startsWith("print") == false && statements[0].trim().startsWith("while") == false) continue;
			
			String code = statements[1];
			//System.out.println(code);
			
			String[] tokens = code.split(" ");
			for (int i = 0; i < tokens.length; i++) {
				String tok = tokens[i];
				if (comparisonOps.contains(tok)) createMutations(mutations, comparisonOps, lineNumber, i, code);
				if (arithmeticOps.contains(tok)) createMutations(mutations, arithmeticOps, lineNumber, i, code);
				if (booleanOps.contains(tok)) createMutations(mutations, booleanOps, lineNumber, i, code);
			}
			
		}
		
		return mutations;
		
	}
	
	private static void createMutations(List<Mutation> mutations, List<String> operations, int lineNumber, int tokNumber, String code) {
		
		String[] tokens = code.split(" ");
		String base = "";
		for (int i = 0; i < tokNumber; i++) {
			base += tokens[i] + " ";
		}
		for (String op : operations) {
			if (op.equals(tokens[tokNumber])) continue;
			String copy = new String(base);
			copy += op + " ";
			for (int i = tokNumber + 1; i < tokens.length; i++) {
				copy += tokens[i] + " ";
			}
			Mutation m = new Mutation(lineNumber, tokNumber, code, copy);
			mutations.add(m);
		}
		
	}

	public static List<String> readOriginal(String name) throws Exception {
		
		List<String> lines = new LinkedList<>();
		
		File file = new File(name);
		
		Scanner in = new Scanner(file);
		
		while (in.hasNext()) {
			
			String line = in.nextLine();
			
			lines.add(line);
		}
		
		return lines;
		
	}

}
