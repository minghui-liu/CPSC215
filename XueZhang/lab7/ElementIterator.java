/**
 * File: ElementIterator
 *
 * This class implements an iterator for the ADT node list.
 * 
 * @author Max Le Merle
 * @author Yisheng Cai
 * @version 1.0.0 3/6/13
 */

public class ElementIterator<E> implements Iterator<E> {

  protected PositionList<E> list;  // the underlying list
  protected Position<E> cursor;    // the next position

  /** Creates an element iterator over the given list. 
	 * @parameter PositionList<E>, takes PositionList<E> l that will be iterated.	
	 */
  public ElementIterator(PositionList<E> L) {
    list = L;
    cursor = (list.isEmpty())? null : list.first();
  }

  /** Tests whether there are elements left in the iterator. 
	 * @return Boolean, true if there is a next value, false otherwise.
	 */
  public boolean hasNext() { return (cursor != null); }

  /** Returns the next element in the iterator. 
	 * @return E, returns the next element in the iterator.
	 */
  public E next() throws NoSuchElementException {
    if (cursor == null)
      throw new NoSuchElementException("No next element");
    E toReturn = cursor.element();
    cursor = (cursor == list.last())? null : list.next(cursor);
    return toReturn;
  }

}
