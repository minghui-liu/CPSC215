/**
 * File: MyElementIterator
 *
 * This class implements an iterator for the ADT index list.
 * 
 * @author Yisheng Cai
 * @version 1.0.0 3/13/13
 */

public class MyElementIterator<E> implements Iterator<E> {

  protected IndexList<E> list;  // the underlying list
  protected int r=0;
	protected E cursor;

  /** Creates an element iterator over the given list. 
	 * @parameter IndexList<E>, takes IndexList<E> l that will be iterated.	
	 */
  public MyElementIterator(IndexList<E> L) {
    list = L;
    cursor = (list.isEmpty())? null : list.get(r);
  }

  /** Tests whether there are elements left in the iterator. 
	 * @return Boolean, true if there is a next value, false otherwise.
	 */
  public boolean hasNext() { return (cursor != null); }

  /** Returns the next element in the iterator. 
	 * @return E, returns the next element in the iterator.
	 */
  public E next() throws IndexOutOfBoundsException {
    if (cursor == null)
      throw new IndexOutOfBoundsException("No next element");
    E toReturn = list.get(r);
    r++;
    cursor = (cursor == list.get(list.size()-1))? null : list.get(r);
    return toReturn;
  }

}
