/**
 * File: HeapPQ.java
 *
 * Realization of a priority queue by means of a heap.  The heap is directly 
 * built on the ADT array list.
 *
 * @author Takunari Miyazaki
 * @author Tanya Nongera
 * @author Yisheng Cai
 * @see ArrayIndexList
 * @see Comparator
 * @see DefaultComparator
 * @see Entry
 * @see IndexList
 */

public class HeapPQ<K,V> implements PriorityQueue<K,V> {

  protected IndexList<Entry<K,V>> heap;
  protected Comparator<K> comp;
  protected int size;

  /** Inner class for entries */
  protected static class MyEntry<K,V> implements Entry<K,V> {
    protected K k;
    protected V v;
    public MyEntry(K key, V value) {
      k = key;
      v = value;
    }
    public K getKey() { return k; }
    public V getValue() { return v; }
  }

  /** Creates an empty heap with the default comparator. */ 
  public HeapPQ() {
    heap = new ArrayIndexList<Entry<K,V>>();
    comp = new DefaultComparator<K>();
    heap.add(0, null);
    size = 0;
  }

  /** Creates an empty heap with the given comparator. */
  public HeapPQ(Comparator<K> c) {
    heap = new ArrayIndexList<Entry<K,V>>();
    comp = c;
    heap.add(0, null);
    size = 0;
  }

  /** Returns the size of the heap. 
	* @return integer size
  */
  public int size() {
    return size;
  }

  /** Returns whether the heap is empty. 
	* @return boolean if the heap is empty
  */
  public boolean isEmpty() {
    return size() == 0;
  }

  /** Returns but does not remove an entry with minimum key. 
	* @return minimum key of the heap
	*/
  public Entry<K,V> min() throws EmptyPriorityQueueException {
    if (isEmpty()) 
      throw new EmptyPriorityQueueException("Priority queue is empty.");
    return heap.get(1);
  }

  /** Inserts a key-value pair and return the entry created. 
	* @param key and value of the entry to be insert
	* @return the entry inserted
  */
  public Entry<K,V> insert(K k, V x) throws InvalidKeyException {
		checkKey(k);
		Entry<K,V> entry = new MyEntry<K,V>(k,x);
		heap.add(size+1,entry);
		int i = size+1;
		while ((i/2 >=1) && (comp.compare(key(heap.get(i/2)), key(heap.get(i)))>0)){
			swap(i,i/2);
			i=i/2;
		}
		size++;
		return entry;
  }

  /** Removes and returns an entry with minimum key. 
	* @return the entry removed
  */
  public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
	  if (isEmpty()) 
    	throw new EmptyPriorityQueueException("Priority queue is empty.");
		Entry<K,V> temp = min();
		if (size == 1){
			heap.remove(1);
			size--;
			return temp;
		}
		heap.set(1,heap.remove(size));
		int i=1;
		while ((i*2)<=(size-1)){
			int j = i*2;
			K w;
			if ((j+1<size) && (comp.compare(key(heap.get(j)), key(heap.get(j+1)))>0))
				j++;
			if (comp.compare(key(heap.get(i)),key(heap.get(j)))>0){
				swap(i,j);
				i=j;
			}
			else
				break;
		}
		size--;
		return temp;
  }

  /** Returns the key stored in an entry. 
	* @return key of the entry
  */
  protected K key(Entry<K,V> e) {
    return e.getKey();
  }

  /** Returns the value stored in an entry. 
  * @return key of the entry
  */
  protected V value(Entry<K,V> e) {
    return e.getValue();
  }

  /** Determines whether a given key is valid. 
  * @param key of the entry
	*/
  protected void checkKey(K key) throws InvalidKeyException {
    try {
      comp.compare(key, key);
    }
    catch(Exception e) {
      throw new InvalidKeyException("Invalid key");
    }
  }

  /** Swaps the entries of the two given indices. 
	* @param integer values of the entries' indeces
  */
  protected void swap(int i, int j) {
    Entry<K,V> temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

}