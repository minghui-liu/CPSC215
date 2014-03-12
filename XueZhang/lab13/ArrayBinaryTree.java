/**
 * File: ArrrayBinaryTree.java
 *
 * An implementation of a binary tree using an array.  The root is stored at 
 * index 1, and the remaining nodes are stored as follows:  If a node is 
 * stored at index i, then its left child is stored at index 2i, and its right 
 * child is stored at index 2*i + 1.  Nothing is stored at index 0.
 *
 * @author Takunari Miyazaki
 * @author Yisheng Cai
 * @see ArrayIndexList
 * @see BinaryTree
 * @see BoundaryViolationException
 * @see EmptyTreeException
 * @see IndexList
 * @see InvalidPositionException
 * @see Iterable
 * @see Iterator
 * @see NonEmptyTreeException
 * @see Position
 * @verision 1.0.0 04/24/2013
 */

public class ArrayBinaryTree<E> implements BinaryTree<E>  {

  public static final int CAPACITY = 100; // the default capacity of an array
  protected Position<E> T[];              // an array of positions
  protected int capacity;                 // the capacity of an array
  protected int size;                     // the number of nodes
  protected int maxIndex;                 // the maximum index of a node

  /** Nested class for an array-based binary tree node. */
  protected static class BTPos<E> implements Position<E> {
    E element;      // element stored at this position
    int index;      // index of this position in the array
    public BTPos(E e, int i) { 
      element = e;
      index = i; 
    }
    public E element() { return element; }
    public int index() { return index; }
    public E setElement(E e) {
      E toReturn = element;
      element = e;
      return toReturn;
    }
    public String toString() {
      return("(" + element + ", " + index + ")");
    }
  }

  /** default constructor */
  public ArrayBinaryTree() { 
    this(CAPACITY);
  }

  /** parametric constructor */
  public ArrayBinaryTree(int cap) {
    capacity = cap; 
    T = (Position<E>[]) new Position[capacity];
    T[0] = null; // index 0 is deliberately empty
    size = 0;
    maxIndex = 0;
  }

  /** Returns the number of (internal and external) nodes. */
  public int size() { return size; } 

  /** Returns whether the tree is empty. */ 
  public boolean isEmpty() { return (size == 0); }

  /** Updates maxIndex. */
  protected void updateMaxIndex(int i) { if (i > maxIndex) maxIndex = i; } 

  /** Determines whether the given position is a valid node. */
  protected BTPos<E> checkPosition(Position<E> v) 
    throws InvalidPositionException {
    if (v == null || !(v instanceof BTPos))
      throw new InvalidPositionException("Position is invalid");
    return (BTPos<E>) v;
  }

  /** Returns whether v is an internal node. */
  public boolean isInternal(Position<E> v) throws InvalidPositionException {
    return hasLeft(v) || hasRight(v);
  }

  /** Returns whether v is an external node. */
  public boolean isExternal(Position<E> v) throws InvalidPositionException {
    return !isInternal(v);
  }

  /** Returns whether v is the root node. */
  public boolean isRoot(Position<E> v) throws InvalidPositionException { 
    BTPos<E> vv = checkPosition(v);
    return vv.index() == 1;
  }

  /** Returns whether v has a left child. */
  public boolean hasLeft(Position<E> v) throws InvalidPositionException { 
    BTPos<E> vv = checkPosition(v);
    if (2*vv.index() > maxIndex) return false;
    else return T[2*vv.index()] != null;
  }

  /** Returns whether v has a right child. 
	* @param position object v being checked
  * @return boolean statement true if v has a right child, false if it
  * does not
	*/
  public boolean hasRight(Position<E> v) throws InvalidPositionException { 
		BTPos<E> vv = checkPosition(v);
    if (2*vv.index()+1 > maxIndex) return false;
    else return T[2*vv.index()+1] != null;
  }

  /** Returns the root of the tree. */
  public Position<E> root() throws EmptyTreeException {
    if (isEmpty()) throw new EmptyTreeException("Tree is empty");
    return T[1];
  }

  /** Returns the left child of v. */
  public Position<E> left(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException { 
    if (!hasLeft(v)) throw new BoundaryViolationException("No left child");
    BTPos<E> vv = checkPosition(v);
    return T[2*vv.index()];
  }

  /** Returns the right child of v. 
  * @param position object v being checked
  * @return the right child of v
  */ 
  public Position<E> right(Position<E> v) 
    throws InvalidPositionException { 
		if (!hasRight(v)) throw new BoundaryViolationException("No right child");
    BTPos<E> vv = checkPosition(v);
    return T[2*vv.index()+1];
  }

  /** Returns the parent of v. */
  public Position<E> parent(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException { 
    if (isRoot(v)) throw new BoundaryViolationException("No parent");
    BTPos<E> vv = checkPosition(v);
    return T[vv.index()/2];
  }

  /** Returns an iterable collection of the children of v. */ 
  public Iterable<Position<E>> children(Position<E> v) 
    throws InvalidPositionException { 
    IndexList<Position<E>> children = new ArrayIndexList<Position<E>>();
    if (hasLeft(v))
      children.add(children.size(), left(v));
    if (hasRight(v))
      children.add(children.size(), right(v));
    return children;
  }

  /** Returns an iterable collection of all the nodes in the tree. */
  public Iterable<Position<E>> positions() {
    IndexList<Position<E>> P = new ArrayIndexList<Position<E>>();
    for (int i = 1; i <= maxIndex; i++)
      if (T[i] != null)
        P.add(P.size(), T[i]);
    return P;
  }

  /** Returns an iterator of the elements stored at all nodes in the tree. */
  public Iterator<E> iterator() { 
    IndexList<E> list = new ArrayIndexList<E>();
    for (int i = 1; i <= maxIndex; i++)
      if (T[i] != null)
        list.add(list.size(), T[i].element());
    return list.iterator();
  } 

  /** Replaces the element at v. */
  public E replace(Position<E> v, E o) throws InvalidPositionException {
    BTPos<E> vv = checkPosition(v);
    return vv.setElement(o);
  }

  /** Adds an element at the root. */
  public Position<E> addRoot(E e) {
    if (!isEmpty()) throw new NonEmptyTreeException("Tree is not empty");
    BTPos<E> p = new BTPos<E>(e, 1);
    T[1] = p;
    size++;
    updateMaxIndex(1);
    return p;
  }

  /** Expand the capacity of the array if needed. */
  protected void expandArray(int i) {
    if (i >= capacity) {
      while (i >= capacity)
        capacity *= 2;
      Position<E>[] U = (Position<E>[]) new Position[capacity];
      for (int j = 0; j <= maxIndex; j++)
	U[j] = T[j];
      T = U;
    }
  }

  /** Inserts a new node with an element e at the left child of v. */
  public Position<E> insertLeft(Position<E> v, E e) {
    if (hasLeft(v)) 
      throw new InvalidPositionException("Node already has a left child.");
    BTPos<E> vv = checkPosition(v);
    int i = 2*vv.index();
    expandArray(i);
    BTPos<E> p = new BTPos<E>(e, i);
    T[i] = p;
    size++;
    updateMaxIndex(i);
    return p;
  }

  /** Inserts a new node with an element e at the right child of v. 
	* @param position object v to be the parent, element e to be the 
	* right child of v.
	*	@return the node being inserted
	*/
  public Position<E> insertRight(Position<E> v, E e) {
    BTPos<E> vv = checkPosition(v);
    int i = 2*vv.index()+1;
    expandArray(i);
    BTPos<E> p = new BTPos<E>(e, i);
    T[i] = p;
    size++;
    updateMaxIndex(i);
    return p;
  }

  /** Returns a String represention of the tree. */
  public String toString() {
    if (isEmpty())
      return "[]";
    StringBuffer sb = new StringBuffer("[");
    for (int i = 0; i <= maxIndex; i++) {
      if (T[i] != null)
        sb.append(T[i].toString());
      else        
        sb.append("null");
      if (i < maxIndex)
        sb.append(", ");
    }
    return sb.toString() + "]";
  }

  /** Returns an iterable collection of the elements in preorder. 
  * @return iterable collection of all elements
  */
  public Iterable<E> elements() {
		IndexList<E> eList = new ArrayIndexList<E>();
		elements(T[1],eList);
		return eList;
  }

  /** Add nodes in T to an array index list recursively. 
	* @param node at root and index list which is an iterable collection	
	* @return iterable collection of all elements
	*/
  protected void elements(Position<E> v, IndexList<E> list) 
		throws InvalidPositionException {
		list.add(list.size(),v.element());
		if (hasLeft(v))
		  elements(left(v), list);
		if (hasRight(v))
		  elements(right(v), list);
  }
} 
