/**
 * Braces Matching
 * This program reads a text file specified in a command-line argument 
 * into a string named inString.  It also has a code segment to echo 
 * print inString.  To execute, assuming an input file is named filename 
 * and exists in the same directory as CheckBraces.class, type
 *
 *   java CheckBraces filename
 *@author Yisheng Cai
 *@version 1.0 03/03/13
 */

import java.io.*;

public class CheckBraces {
  /* this main class uses a command-line to read text in a file and check the braces matching in the text.
   * @param args is the string of input. It reads the file named as the command-line argument
   */
  public static void main(String args[]) {
    String inString = null;
    if (args.length < 1) {
      System.out.println("Usage: java CheckBraces sourcefile");
      return;
    }
    Stack<Character> A;
    A = new DequeStack<Character>();
    int i=0;     //integer keeping track of indeces of character in the text
    int error=0; //integer indicating the type of error for braces matching
    char expect=' '; //character that tells what kind of brace is expected
    // Read the file named as the command-line argument
    try {
      File f = new File(args[0]);
      InputStreamReader inStream = 
        new InputStreamReader(new FileInputStream(f));
      int length = (int) f.length();
      char input[] = new char[length];
      inStream.read(input);
      inString = new String(input);
    } 
    /* Catch block
     * @param Takes a exception type as a parameter if file is not found
     */
    catch (FileNotFoundException e) {
      System.err.println("Error: File " + args[0] + " not found");
      e.printStackTrace();
    }
    catch (IOException e) {
      System.err.println("Error: I/O exception");
      e.printStackTrace();
    }

   //  This is a for loop that reads and checks every character in the string
   for (i=0; i<inString.length()-1; i++){
      if ((inString.charAt(i) == '(')||(inString.charAt(i) == '[')||(inString.charAt(i) == '{')){
	A.push(inString.charAt(i)); // Pushes brace into the stack
	if (A.top() == '(')
	  expect=')';
	else if (A.top() == '[')
	  expect=']';
	else if (A.top() == '{')
	  expect='}';
	}

      // Checks the stack when it finds a closing brace. Gives an error if the brace does not match.
      if (inString.charAt(i) == ')'){
	if (A.isEmpty()){
	  error=2;
	  break;
	}
	else if (A.top() != '('){
	  error=1;
	  A.pop();
       	  break;
	}
      	else 
	  A.pop();

	
      }
      else if (inString.charAt(i) == ']'){
	if (A.isEmpty()){
	  error=2;
	  break;
	}
	else if (A.top() != '['){
	  error=1;
	  A.pop();
       	  break;
	}
      	else 
	  A.pop();

      }
      else if (inString.charAt(i) == '}'){
	if (A.isEmpty()){
	  error=2;
	  break;
	}
	else if (A.top() != '{'){
	  error=1;
	  A.pop();
       	  break;
	}
      	else 
	  A.pop();
      }
    }
	
    

	
    // Echo print the file
    for (int k = 0; k < inString.length(); k++)
      System.out.print(inString.charAt(k));
    System.out.println();
    
    // Error messages for unmatched brace
    if (error == 1){
      System.out.println("Unmatched brace at character "+i+": Found "+inString.charAt(i)+" expecting "+ expect+".");
    }
    if (error ==2){
      System.out.println("Unmatched brace at character "+i+": No opening brace for "+inString.charAt(i)+".");
    }
  }
	

}
