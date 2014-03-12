/**
 * File: LinkedBinaryTree.java
 *
 * An implementation of the BinaryTree interface by means of a linked 
 * structure.
 *
 * @author Nate Maynard & Yisheng Cai
 * @author Roberto Tamassia
 * @author Michael Goodrich
 * @see BinaryTree
 * @see BoundaryViolationException
 * @see BTPosition
 * @see EmptyTreeException
 * @see InvalidPositionException
 * @see Iterable
 * @see Iterator
 * @see NodePositionList
 * @see NonEmptyTreeException
 * @see Position
 * @see PositionList
 */

public class LinkedBinaryTree<E> implements BinaryTree<E> {

  protected BTPosition<E> root;	// reference to the root
  protected int size;		// number of nodes

  /**  Creates an empty binary tree. */
  public LinkedBinaryTree() { 		    
    root = null;                // start with an empty tree
    size = 0;
  }

  /** Returns the number of nodes in the tree. 
  * @return the number of nodes in the tree
  */
  public int size() {
    return size; 
  }

  /** Returns whether the tree is empty. 
  * @return true if the tree is empty, false if not.
  */
  public boolean isEmpty() {
    return (size == 0);
  }

  /** Returns whether a node is internal. 
  * @param position nodes being accessed
  * @return boolean true if the node is internal, false if not
  */
  public boolean isInternal(Position<E> v) throws InvalidPositionException {
    checkPosition(v);           // auxiliary method
    return (hasLeft(v) || hasRight(v));
  }

  /** Returns whether a node is external. 
  * @param position nodes being accessed
  * @return boolean true if the node is external, false if not
  */
  public boolean isExternal(Position<E> v) throws InvalidPositionException {
    checkPosition(v);
    return (!hasLeft(v) && !hasRight(v));
  }

  /** Returns whether a node is the root. 
  * @param position nodes being accessed
  * @return boolean true if the node is a root, false if not
  */
  public boolean isRoot(Position<E> v) throws InvalidPositionException { 
    checkPosition(v);
    return (v == root()); 
  }

  /** Returns whether a node has a left child. 
  * @param position nodes being accessed
  * @return boolean true if the node has a left child, false if not
  */
  public boolean hasLeft(Position<E> v) throws InvalidPositionException { 
    BTPosition<E> vv = checkPosition(v);
    return (vv.getLeft() != null);
  }

  /** Returns whether a node has a right child. 
  * @param position nodes being accessed
  * @return boolean true if the node has a right child, false if not
  */
  public boolean hasRight(Position<E> v) throws InvalidPositionException { 
    BTPosition<E> vv = checkPosition(v);
    return (vv.getRight() != null);
  }

  /** Returns the root of the tree. 
  * @return position of the root of the tree
  */
  public Position<E> root() throws EmptyTreeException {
    if (root == null)
      throw new EmptyTreeException("The tree is empty");
    return root;
  } 

  /** Returns the left child of a node. 
  * @param the parental node
  * @return the position of the left child of this node
  */
  public Position<E> left(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException { 
    BTPosition<E> vv = checkPosition(v);
    Position<E> leftPos = vv.getLeft();
    if (leftPos == null)
      throw new BoundaryViolationException("No left child");
    return leftPos;
  }

  /** Returns the right child of a node. 
  * @param the parental node
  * @return the position of the right child of this node
  */
  public Position<E> right(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException {
    BTPosition<E> vv = checkPosition(v);
    Position<E> rightPos = vv.getRight();
    if (rightPos == null)
      throw new BoundaryViolationException("No right child");
    return rightPos;
  }  

  /** Returns the parent of a node. 
  * @param the child node
  * @return the parent of this child node 
  */
  public Position<E> parent(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException { 
    BTPosition<E> vv = checkPosition(v);
    Position<E> parentPos = vv.getParent();
    if (parentPos == null)
      throw new BoundaryViolationException("No parent");
    return parentPos; 
  }

  /** Returns an iterable collection of the children of a node. 
  * @param position of the node
  * @reutrn an iterable collection of the children of this node
  */
  public Iterable<Position<E>> children(Position<E> v) 
    throws InvalidPositionException { 
    PositionList<Position<E>> children = new NodePositionList<Position<E>>();
    if (hasLeft(v))
      children.addLast(left(v));
    if (hasRight(v))
      children.addLast(right(v));
    return children;
  }

  /** Returns an iterable collection of the tree nodes. 
  * @return an iterable collection of the tree nodes.
  */
  public Iterable<Position<E>> positions() {
    PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
    if(size != 0)
      preorderPositions(root(), positions);
    return positions;
  }

  /** Returns an iterator of the elements stored at the nodes 
  * @return an iterator of the elements stored at the nodes
  */
  public Iterator<E> iterator() {
    Iterable<Position<E>> positions = positions();
    Iterator<Position<E>> i = positions.iterator();
    PositionList<E> elements = new NodePositionList<E>();
    while (i.hasNext())
      elements.addLast((i.next()).element());
    return elements.iterator();
  }

  /** Replaces the element at a node. 
  * @param the position of node desired to replace and the element of the node
  * @return the node replaced
  */
  public E replace(Position<E> v, E o) 
    throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(v);
    E temp = v.element();
    vv.setElement(o);
    return temp;
  }

  // Additional accessor method
  /** Return the sibling of a node 
  * @param the position of a node
  * @return the position of the sibling of that node
  */
  public Position<E> sibling(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException {
      BTPosition<E> vv = checkPosition(v);
      BTPosition<E> parentPos = vv.getParent();
      if (parentPos != null) {
	BTPosition<E> sibPos;
	BTPosition<E> leftPos = parentPos.getLeft();
	if (leftPos == vv)
	  sibPos = parentPos.getRight();
	else
	  sibPos = parentPos.getLeft();
	if (sibPos != null)
	  return sibPos;
      }
      throw new BoundaryViolationException("No sibling");
  }

  // Additional update methods
  /** Adds a root node to an empty tree 
  * @param the element desired to put at root
  * @return the position ndoe of the root
  */
  public Position<E> addRoot(E e) throws NonEmptyTreeException {
    if(!isEmpty())
      throw new NonEmptyTreeException("Tree already has a root");
    size = 1;
    root = createNode(e,null,null,null);
    return root;
  }

  /** Inserts a left child at a given node. 
  * @param the position of the node and element stored in this node 
  * @return the new inserted node 
  */
  public Position<E> insertLeft(Position<E> v, E e)
    throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(v);
    Position<E> leftPos = vv.getLeft();
    if (leftPos != null)
      throw new InvalidPositionException("Node already has a left child");
    BTPosition<E> ww = createNode(e, vv, null, null);
    vv.setLeft(ww);
    size++;
    return ww;
  }

  /** Inserts a right child at a given node. 
  * @param the position of the node and element stored in this node 
  * @return the new inserted node 
  */
  public Position<E> insertRight(Position<E> v, E e)
    throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(v);
    Position<E> rightPos = vv.getRight();
    if (rightPos != null)
      throw new InvalidPositionException("Node already has a right child");
    BTPosition<E> ww = createNode(e, vv, null, null);
    vv.setRight(ww);
    size++;
    return ww;
  }

  /** Removes a node with zero or one child.
   *@param the position being removed
   *@return the removed element
   */
  public E remove(Position<E> v)
    throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(v);
    BTPosition<E> leftPos = vv.getLeft();
    BTPosition<E> rightPos = vv.getRight();
    if (leftPos != null && rightPos != null)
      throw new 
        InvalidPositionException("Cannot remove node with two children");
    BTPosition<E> ww; 	        // the only child of v, if any
    if (leftPos != null)
      ww = leftPos;
    else if (rightPos != null)
      ww = rightPos;
    else 		        // v is a leaf
      ww = null;
    if (vv == root) { 	        // v is the root
      if (ww != null)
	ww.setParent(null);
      root = ww;
    }
    else { 		        // v is not the root
      BTPosition<E> uu = vv.getParent();
      if (vv == uu.getLeft())
	uu.setLeft(ww);
      else
	uu.setRight(ww);
      if(ww != null)
	ww.setParent(uu);
    }
    size--;
    return v.element();
  }

  /** Attaches two trees to be subtrees of an external node.
   * @param v the position of the attachment
   * @param T1 the original tree
   * @param T2 the tree being attached
   */
  public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) 
    throws InvalidPositionException {
    BTPosition<E> vv = checkPosition(v);
    if (isInternal(v))
      throw new InvalidPositionException("Cannot attach from internal node");
    if (!T1.isEmpty()) {
      BTPosition<E> r1 = checkPosition(T1.root());
      vv.setLeft(r1);
      r1.setParent(vv);		// T1 should be invalidated
    }
    if (!T2.isEmpty()) {
      BTPosition<E> r2 = checkPosition(T2.root());
      vv.setRight(r2);
      r2.setParent(vv);		// T2 should be invalidated
    }
  }

  /** If v is a good node, cast to BTPosition, else throw exception. 
   * @param v the position being checked
   * @return the correctly checked position
   */
  protected BTPosition<E> checkPosition(Position<E> v) 
    throws InvalidPositionException {
    if (v == null || !(v instanceof BTPosition))
      throw new InvalidPositionException("The position is invalid");
    return (BTPosition<E>) v;
  }

  /** Creates a new binary tree node
   * @param element the element of the new tree node
   * @param parent the parent of the new tree node
   * @param left the left child of the new tree node
   * @param right the right child of the new tree node
   * @return the new tree node
   */
  protected BTPosition<E> createNode(E element, BTPosition<E> parent, 
    BTPosition<E> left, BTPosition<E> right) {
    return new BTNode<E>(element,parent,left,right);
  }

  /** Creates a list of the nodes in the subtree of a node in preorder.
   * @param v the position the preorder is to start from
   * @param pos the position list of the preordered positions
   */
  protected void preorderPositions(Position<E> v, 
    PositionList<Position<E>> pos) 
    throws InvalidPositionException {
    pos.addLast(v);
    if (hasLeft(v))
      preorderPositions(left(v), pos);
    if (hasRight(v))
      preorderPositions(right(v), pos);
  }

}