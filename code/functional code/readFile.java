import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class readFile {
    public static void main(String args[]) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("No file name is passed!");
            return; // Terminate the program
        }
        String name = args[0];
        int[][] array = generate(name);
        read(array);
    }

    public static int[][] generate(String fileName) throws FileNotFoundException {
        // ...

        int[][] arrays = new int[18][2];

        Scanner scan = new Scanner(new File(fileName));

        // Read the line to create a new int[][] count time of pass and fail
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            line = line.substring(7); // delete "test1: "...

            for (int n = 0; n < line.length(); n++) {
                if (n > 0 && line.charAt(n - 1) != ' ') {
                    int num = 0;
                    if (line.charAt(n) != ' ') {
                        num = (line.charAt(n) - '0') + (line.charAt(n - 1) - '0') * 10; // convert ASCII digits to integer
                    } else {
                        num = line.charAt(n - 1) - '0'; // convert ASCII digit to integer
                    }

                    // if we're not reading the space and num is within the valid range
                    if (num != 0 && num <= arrays.length) {
                        // if this is a pass test
                        if (line.charAt(line.length() - 1) == 's') {
                            arrays[num - 1][0]++;
                        }

                        // if this is a fail test
                        if (line.charAt(line.length() - 1) == 'l') {
                            arrays[num - 1][1]++;
                        }
                    }
                }
            }
        }
        return arrays;
    }

    public static void read(int[][] array){
        for(int n = 0; n<array.length; n++) {
            System.out.print("line "+ (n+1) + ": ");
            System.out.print(array[n][0]+" pass and ");
            System.out.print(array[n][1]+" fail");
            System.out.println("");
        }
    }

}

