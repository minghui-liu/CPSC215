/**
 * Linked List
 * This class implements a set of strings using a linked list to support five 
 * operations, namely, testing emptiness, membership, insertion, removal, and 
 * printing.
 * @author Yisheng Cai
 * @author Eric Bauerfeld
 */

public class StringSet {
    
  private Node head;
  private int size;                       // The actual size

  /**
   * Creates an empty list and initializes the set's size to 0 (i.e., it 
   * creates an empty set).
   */
  public StringSet() {
    head = null;
    size = 0;
  } 

  /**
   * 
   * Returns true if the set contains no element.
   * @return true when the set is empty, false when the set contains any elements.
   */
  public boolean isEmpty() {
    if (size==0){
	return true;
    }
    else{
	return false;
    }
  }

  /**
   * Returns, if it exists, a node containing a string s.
   * @param takes string s and finds the location of s
   * @return null if s doesn't exist, returns the location of s if it exists
   * 
   */
  private Node find(String s) {
    Node x=head;
    while (x != null){
	if (x.getElement().equals(s))
	    return x;
	x=x.getNext();
    }
    return null;
  }
  /**
   * Returns true if the set contains a string s.
   * @param takes string s and see if the list contains s
   * @return true if s exists in the list, returns false if s does not exist
   */
  public boolean contains(String s) {
    if (find(s) == null)
	return false;
    else
	return true;
    }


  /**
   * Inserts a string s into the list and increments size by 1.
   * @param takes string s and finds the location of s
   */
  public void insert(String s) {
    if (!contains(s)) {
	Node t = head;
	Node b = head;
	Node a = null;
	if (head == null){
		Node x = new Node(s, null);
		head = x;
	}
	else{
 	   	while((t.getElement().compareTo(s)) < 0){
			b = t;
			if (t.getNext() == null)
				break;
			else
				t = t.getNext();
		}
		if ((b==head) && (b.getElement().compareTo(s) > 0)){
			Node x = new Node(s, b);
			head = x;
		}
		else{
			a = b.getNext();
			Node x = new Node(s, a);
			b.setNext(x);
		}
	}
	size++;
      System.out.println("Inserting " + s + "...");
    }
    else
      System.out.println("Inserting " + s + "..., but " + s + 
                         " already exists.");
  }

  /**
   * Removes a string s from the list and decrements size by 1.
   * @param a string s and remove it from the list if s exists
   */
  public void remove(String s) {
    Node a;    
    Node b;
    Node r = find(s);
    if (r == null) 
      System.out.println("Removing " + s + "..., but " + s +
		         " does not exist.");
    else {
	Node x = head;
	Node y = null;
	if (head == r)
		head = head.getNext();
	else{
		while (x != r){
			y=x;
	   		x=x.getNext();
		}
		a = y.getNext().getNext();
		b = y;
		b.setNext(a);
	}

      System.out.println("Removing " + s + "...");
    }
  }

  /**
   * Prints all the strings in the list.
   */
  public void print() {
    Node n = head;
    System.out.print("{ ");
    while (n != null) {
      System.out.print(n.getElement());
      if (n.getNext() != null) 
	System.out.print(", ");
      n = n.getNext();
    }
    System.out.println(" }");
  }

}
