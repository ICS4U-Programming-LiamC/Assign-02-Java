// import necessary modules
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
* Takes various condensed string and expands them based
* the format given to us. letters stay, numbers go away,
* but if theres a number the following char repeats that many times
* on top of however many times it was already going to appear.
*
* @author  Liam Csiffary

* @version 1.1
* @since   2022-03-22
*/

public class stringFun {

  // generates the array of inputs from a text file
  public static String[] ArrayGenerator(File file) throws Exception {

    // defining vars
    List<String> originalListString = new ArrayList<String>();
    System.out.println(file);
    Scanner scStudent = new Scanner(file);

    // pass the path to the file as a parameter
    while (scStudent.hasNextLine()) {
      originalListString.add(scStudent.nextLine());
    }

    // for testing purposes
    System.out.println(originalListString + "\n");

    // create array of length
    String[] array = new String[originalListString.size()];

    // fill the array
    for (int i = 0; i < array.length; i++) {
      array[i] = originalListString.get(i);
    }

    return array;
  }

  // also from https://www.w3schools.com/java/java_files_create.asp
  public static void Writer(String[] arr) {
    try {
      // creates file writing object
      FileWriter myWriter = new FileWriter("expandedStrings.txt");

      // my code
      // just writes to the file the same way you'd print it to the terminal
      // except with myWriter.write instead of System.out.print()
      for (int each = 0; each < arr.length; each++) {
        myWriter.write(arr[each] + "\n");
      }

      // closes the file
      myWriter.close();
      System.out.println("Successfully wrote to the file.");

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  // expands the strings based on the rules given to us then returns
  // the expanded string to main for further processing.
  public static String expander(String condensed) throws Exception {
    // define vars
    String expandedStr = "";
    String[] arrString = condensed.split("");
    int numOfLetter;

    // for each char in the string
    for (int i = 0; i < arrString.length; i++) {

      // if its a number
      try {
        numOfLetter = Integer.parseInt(arrString[i]);

        // add the char in the next index numOfLetter num of times
        if (i < arrString.length - 1) {
          for (int j = 0; j < numOfLetter; j++) {
            expandedStr += arrString[i + 1];
          }
        }

        // if it isn't a number simply add it to the string
      } catch (NumberFormatException e) {
        expandedStr += arrString[i];
      }
    }

    // return the expanded string
    return expandedStr;
  }

  public static void main(String[] args) throws Exception {
    // generates new file and creates the array using the ArrayGenerator
    File file = new File(System.getProperty("user.dir") + "/testCases.txt");

    // get the string as an array from the file
    String[] testCases = ArrayGenerator(file);
    // create an empty array of length testCases to pupulate with the final answers
    String[] expandedStrings = new String[testCases.length];

    // for each in the array of testCases
    for (int i = 0; i < testCases.length; i++) {
      // get the expanded string
      expandedStrings[i] = (expander(testCases[i]));

      // print the information in an easy to verify way
      System.out.println("The original string was: " + testCases[i]);
      System.out.println("The expanded string is: " + expandedStrings[i]);
      System.out.println("");
    }

    // creates and writes to a new file called "expandedStrings.txt"
    Writer(expandedStrings);
  }
}
