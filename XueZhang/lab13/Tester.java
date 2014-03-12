/**
 * File: Tester.java
 *
 * A program to test the classes ArrayBinaryTree and ExpressionTree.
 *
 * @author Takunari Miyazaki
 * @see ArrayBinaryTree
 * @see ExpressinTree
 * @see Position
 */

import java.util.*;

public class Tester {

  /** Inserts the prefix expression s into a tree T starting at the root. */
  public static void preorderBuild(ArrayBinaryTree<String> T, String s) {
    StringTokenizer st = new StringTokenizer(s);
    preorderBuild(T, T.addRoot(null), st);
  }

  /** Recursively inserts the prefix expression. */
  protected static void preorderBuild(ArrayBinaryTree<String> T,
    Position<String> v, StringTokenizer st) {
    if (st.hasMoreTokens()) {
      String s = st.nextToken();
      try {
        Integer.parseInt(s);
        T.replace(v, s);
      }
      catch (NumberFormatException e) {
        T.replace(v, s);
        preorderBuild(T, T.insertLeft(v, null), st);
        preorderBuild(T, T.insertRight(v, null), st);
      }
    }
  }

  /** The main() method. */
  public static void main(String args[]) {

    ArrayBinaryTree<String> bt1 = new ArrayBinaryTree<String>();
    System.out.println("New tree: " + bt1.toString());
    System.out.println("Inserting * as the root.");
    Position<String> p = bt1.addRoot("*");
    System.out.println("New tree: " + bt1.toString());
    System.out.println("Inserting 2 as the left child of *.");
    bt1.insertLeft(p, "2");
    System.out.println("New tree: " + bt1.toString());
    System.out.println("Inserting + as the right child of *.");
    p = bt1.insertRight(p, "+");
    System.out.println("New tree: " + bt1.toString());
    System.out.println("Inserting 6 as the left child of +.");
    bt1.insertLeft(p, "6");
    System.out.println("New tree: " + bt1.toString());
    System.out.println("Inserting 5 as the right child of +.");
    bt1.insertRight(p, "5");
    System.out.println("New tree: " + bt1.toString());
    System.out.println("Elements: " + bt1.elements().toString());
    System.out.println();

    ArrayBinaryTree<String> bt2 = new ArrayBinaryTree<String>(10);
    String exp = "- / * + 3 1 3 + - 9 5 2 + * 3 - 7 4 6";
    System.out.println("Inserting in preorder: " + exp);
    preorderBuild(bt2, exp);
    System.out.println("New tree: " + bt2.toString());
    System.out.println("Elements: " + bt2.elements().toString());
    System.out.println();

    // Add a code segment to test the class ExpressionTree using the 
    // expression "- / * + 3 1 3 + - 9 5 2 + * 3 - 7 4 6".
		ExpressionTree bt3 = new ExpressionTree();
		preorderBuild(bt3,"- / * + 3 1 3 + - 9 5 2 + * 3 - 7 4 6");
		System.out.println("ExpressionTree bt3: " + bt3.toString());
		System.out.println(bt3.evaluate());
		
		
    // Add a code segment to test the class ExpressionTree using the 
    // expression "+ - 496 * 28 6 8128".
		ExpressionTree bt4 = new ExpressionTree();
		preorderBuild(bt4,"+ - 496 * 28 6 8128");
		System.out.println("ExpressionTree bt4: " + bt4.toString());
		System.out.println(bt4.evaluate());
  }

}