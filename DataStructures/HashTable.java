/* by Sam Dunny
 * 
 */

public class HashTable <K,T> {

	// internal class HashNode
	private class HashNode
	{
		// instance variables
		private K key;
		private T value;
		
		// parameterized constructor
		public HashNode(K aKey, T aVal)
		{
			key = aKey;
			value = aVal;
		}
	}

	// instance variables
	private Vector<DoublyLinkedList<HashNode>> hashTable;
	private int count;
	
	// default constructor
	public HashTable()
	{
		// initialize new vector hashTable
		hashTable = new Vector<DoublyLinkedList<HashNode>>();		
		count = 0;
	}
	
	// add method
	public void add(K aKey, T aValue)
	{
		// vector size starts off at 1 for getTableSize, getIndex
		hashTable.grow();
		// always add new linked list, no indexes in hashTable should be null
		hashTable.add(new DoublyLinkedList<HashNode>());
		
		// don't need to re-index if the size = 1
		if(hashTable.size() != 1)
			re_index();
		
		// create new hashNode with given parameters
		HashNode h = new HashNode(aKey, aValue);
		
		// determine the index of the vector
		int ind = getIndex(aKey);
			
		// add new element
		hashTable.get(ind).add(h);
		
		// increase count
	    count++;
	}
	
	/* re_index method: helper to add()
	 * used to "re-rack" hash table after every new element is added:
	 * due to this being a vector of dLLs, every element has to be re-assigned a new 
	 * hash every time the vector grows, so as to be able to find the correct element
	 * when the table gets bigger
	 */
	private void re_index()
	{
		// declare a new hashTable
		Vector<DoublyLinkedList<HashNode>> newTable = new Vector<DoublyLinkedList<HashNode>>();
		
		// initialize newTable to same size as original
		for (int i = 0; i < hashTable.size(); i++)
		{
			newTable.grow();
			newTable.add(new DoublyLinkedList<HashNode>());
		}
		
		// for each vector index
		for (int i = 0; i < hashTable.size(); i++)
		{
			// retrieve the linked list at that index
			DoublyLinkedList dLL = hashTable.get(i);
	
			// for each linked list at each vector slot
			dLL.resetCurrent();
			for (int j = 0; j < dLL.size(); j++)
			{
				// get the current HashNode
				HashNode h =  (HashTable<K, T>.HashNode) dLL.getCurrent();
				// recalculate its new index for the new table
				int ind = getIndex(h.key);
				
				// add value to the new table
				newTable.get(ind).add(h);
				
				// progress through list
				dLL.gotoNext();
			}
		}
		// set reference to the new table
		hashTable = newTable;
	}
	
	// get method
	public T get(K aKey)
	{
		// use key to find correct index in vector to start searching
		int ind = getIndex(aKey);
		
		/* hashTable is a vector, 
		 * get() (from Vector) will return linked list at the index */
		DoublyLinkedList dLL = hashTable.get(ind);
		
		// this theoretically shouldn't be possible, but just a check
		if (dLL == null)
			return null;
		
		/* since were are now traversing a linked list we can use:
		 * goToNext() (from DLL) to progress through dLL
		 * getCurrent() (from DLL) to inspect item
		 * size() (from DLL) to obtain length of list 
		 * resetCurrent() (from DLL) to set current to head */
		dLL.resetCurrent();
		for (int i = 0; i < dLL.size(); i++) {
			
			// cast from T to HashNode
			HashNode h =  (HashTable<K, T>.HashNode) dLL.getCurrent();

			// found item
			// Note: == did not work here, must use .equals()
			if ((h.key).equals(aKey))
			{
				// cast HashNode back to T and return
				return (T) h;
			}
			else
			{
				// progress through list
				dLL.gotoNext();
			}
		}
		// item was not found
		return null;
	}

	// remove method
	public T remove(K aKey)
	{
		// use key to find correct index in vector to start searching
		int ind = getIndex(aKey);
		
		/* hashTable is a vector, 
		 * get() (from Vector) will return linked list at the index */
		DoublyLinkedList dLL = hashTable.get(ind);
		
		// this theoretically shouldn't be possible, but just a check
		if (dLL == null)
			return null;
		
		/* since were are now traversing a linked list we can use:
		 * goToNext() (from DLL) to progress through dLL
		 * getCurrent() (from DLL) to inspect item
		 * size() (from DLL) to obtain length of list
		 * removeCurrent() (from DLL) to remove node 
		 * resetCurrent() (from DLL) to set current to head */
		dLL.resetCurrent();
		for (int i = 0; i < dLL.size(); i++) {
			
			// cast from T to HashNode
			HashNode h =  (HashTable<K, T>.HashNode) dLL.getCurrent();			
			// found item
			// Note: == did not work here, must use .equals()
			if ((h.key).equals(aKey))
			{
				// copy element before deleting, to return
				T found = (T) dLL.getCurrent();
				dLL.removeCurrent();
				count--;
				return found;
			}
			else
			{
				// progress through list
				dLL.gotoNext();
			}
		}
		// item was not found
		return null;
	}
	
	// getIndex method
	private int getIndex(K aKey)
	{
		// get Java-generated hash value from key
		int hashRes = aKey.hashCode();
		
		// ensure hash value is positive
		if (hashRes < 0)
			hashRes *= -1;
		
		// get index from hashed value
		int index = hashRes % getTableSize();
		
		// returns index
		return index;
	}
	
	// getTableSize method
	public int getTableSize()
	{
		/* hashTable is a vector, 
		 * size() (from Vector) will return size of the vector */
		return hashTable.size();
	}

	// count method
	public int count()
	{		
		/* count is incremented and decremented during add and remove
		 * we just return it here */
		return count;
	}

    // print method
	public void print()
	{
		/* hashTable is a vector, 
		 * count() (from Vector) will return number of non-null vector spaces 
		 * get(i) (from Vector) will return linked-list at index i in vector
		 * print() (from DLL) will print entire linked list */
		// Note: this will only print hashed values for each hashNode, not actual Key and Value
		for (int i = 0; i < hashTable.count(); i++)
		{
			DoublyLinkedList<HashNode> dll = hashTable.get(i);
			dll.print();
		}
	}
	
}