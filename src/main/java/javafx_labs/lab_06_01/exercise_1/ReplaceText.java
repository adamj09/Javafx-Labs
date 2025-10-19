package javafx_labs.lab_06_01.exercise_1;

import java.io.*;
import java.util.*;

/**
 * This class was obtained from the textbook "Introduction to Java Programming
 * and Data Structures, Comprehensive Version, 12th Edition" by Liang.
 * 
 * Note: I'm leaving this code in my repository for convenience. Due to the repo
 * being a Java project dependent on JavaFX (which is managed by Maven), this
 * program cannot be run from the command line using javac. To run this, move
 * this file into its own directory (not a Java project), so that it has no
 * external dependencies. - Adam Johnston
 * 
 */

public class ReplaceText {
    public static void main(String[] args) throws Exception {
        // Check command line parameter usage
        if (args.length != 4) {
            System.out.println(
                    "Usage: java ReplaceText sourceFile targetFile oldStr newStr");
            System.exit(1);
        }

        // Check if source file exists
        File sourceFile = new File(args[0]);
        if (!sourceFile.exists()) {
            System.out.println("Source file " + args[0] + " does not exist");
            System.exit(2);
        }

        // Check if target file exists
        File targetFile = new File(args[1]);
        if (targetFile.exists()) {
            System.out.println("Target file " + args[1] + " already exists");
            System.exit(3);
        }

        try (
                // Create input and output files
                Scanner input = new Scanner(sourceFile);
                PrintWriter output = new PrintWriter(targetFile);) {
            while (input.hasNext()) {
                String s1 = input.nextLine();
                String s2 = s1.replaceAll(args[2], args[3]);
                output.println(s2);
            }
        }
    }
}
