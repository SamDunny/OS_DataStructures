/* by Sam Dunny
 * 
 */

public class DoublyLinkedList <T> {
	
	// internal class ListNode
	private class ListNode
	{
		// instance variables
		private T data;
		private ListNode nextLink;
		private ListNode prevLink;	
		
		// default constructor
		public ListNode(T aData)
		{
			data=aData; //takes in one parameter 
			nextLink=null;
			prevLink=null;
		}
		
		// parameterized constructor
		public ListNode(T aData, ListNode nLink, ListNode pLink)
		{
			// don't have to create accessors and mutators because no one else is using this
			data = aData;
			nextLink = nLink;
			prevLink = pLink;
		}
	}

	// instance variables
	ListNode head;
	ListNode current;
	 
	// default constructor
	public DoublyLinkedList()
	{
		head = null;
		current = head;
	}
	
	// add method
	public void add(T data)
	{
		// list is empty
		if (head == null)
		{
			ListNode n = new ListNode(data);
			head = current = n;
			return;
		}
		else
		{
			ListNode temp = head;
			// traverse the list until you find an empty link coming up
			while(temp.nextLink != null)
			{
				temp = temp.nextLink;
			}
			// make new link
			ListNode n = new ListNode(data, null, temp);
			temp.nextLink = n;
		}
	}
	
	// getCurrent method
	public T getCurrent()
	{
		// initialize result
		T aData = null;
	 	if(current.data != null)
		{
			aData = current.data;
		}
		return aData;
	}
	
	// setCurrent method
	public void setCurrent(T data)
	{
		current.data = data;
	}
		
	// gotToNext method
	public void gotoNext()
	{
		if(current != null)
		{
			if(current.nextLink != null)
			{
				current = current.nextLink;
			}
		}
	}
	
	// resetCurrent method
	public void resetCurrent()
	{
		current = head;	
	}
	
	// hasMore method
	public boolean hasMore()
	{
		if (current != null)
			return true;
		return false;
	}
	
	// removeCurrent method
	public void removeCurrent()
	{
		// checks for existence WORKS
		if(current == null)
		{
			System.out.println("Cannot remove null elements");
			return;
		}
		
		// if the current is the head WORKS
		else if(current == head)
		{
			// if the only element is the head
			if (current.nextLink == null)
			{
				head = null;
				current = head;
				return;
			}
			else
			{
				current.nextLink.prevLink = null;
				current = current.nextLink;
				head = current;
				return;
			}
		}
		
		// if the current is the tail WORKS
		else if(current.nextLink == null)
		{
			// two element list WORKS
			if (current.prevLink == head)
			{
				current.prevLink.nextLink = null;
				current = current.prevLink;
				current.prevLink = null;
				return;
			}
			else 
			{
				// THIS REMOVES TWO TAILS, NOT ONE
				//current.prevLink = current.prevLink.prevLink;
				//current.prevLink.nextLink = null;
				//current = current.prevLink;
				
				current.prevLink.nextLink = null;
				current.prevLink = current.prevLink.prevLink;
				current = current.prevLink.nextLink;
				return;
			}
		}
		
		// if the current is in the middle of the body WORKS
		else
		{
			// points previous link 'forward' towards next link 'forward'
			current.prevLink.nextLink = current.nextLink;
			// points next link 'backward' towards previous link
			current.nextLink.prevLink = current.prevLink;
			// points current towards the next link
			current = current.nextLink;
			// lost all reference
			return;
		}
	}
	
	// print method
	public void print()
	{		
		// starts at the head
		ListNode copy = head;
		while(copy != null)
		{
			System.out.println(copy.data);
			// moves the link
			copy = copy.nextLink;
		}
	}
	
	// size method
	public int size()
	{
		// checks for existence
		if(head == null)
		{
			//System.out.println("Cannot count null elements");
			return -1;
		}
		// starts at the head
		ListNode copy = head;
		int incr = 0;
		while(copy != null)
		{
			incr++;
			// moves the link
			copy = copy.nextLink;
		}
		return incr;
	}
	
	//// other methods from from previous double linked list examples ////
	
	// gotToPrev method
	public void goToPrev()
	{
		if(current != null)
		{
			if(current.prevLink != null)
			{
				current = current.prevLink;
			}
		}
	}
	
	// insertNodeAfterCurrent method 
	public void insertNodeAfterCurrent(T data)
	{
		ListNode n = new ListNode(data, current.nextLink, current);
		if(current != null)
		{
			// sets new ListNode link equal to current link (effectively making it the current)
			n.nextLink = current.nextLink;
			// sets n to current link
			current.nextLink=n;
		}
	}
	
	// inList method
	public boolean inList(T data)
	{
		ListNode copy = head;
		while(copy != null)
		{
			if(copy.data == data)
			{
				return true;
			}
			// moves the link
			copy = copy.nextLink;
		}
		return false;
	}
	
}