/* by Sam Dunny
 * 
 */

public class Vector <T> {

	// instance variables
	private T[] values;
	private int lastIndex;
	
	// default constructor
	public Vector()
	{
		values = null;
		lastIndex = 0;
	}
	
	// add method
	public void add(T data)
	{
		// first use: values = null
		if (values == null || lastIndex > (values.length - 1))
			grow();
		
		values[lastIndex] = data;
		lastIndex++;
	}
	
	// grow method
	public void grow() 
	{
		if (values == null)
		{
			T[] newArray = (T[])(new Object[1]);
			values = newArray;
		}
		else {
			T[] newArray = (T[])(new Object[values.length+1]);
			//Transfer all elements from values to the newArray (newArray[i] = values[i])
			for (int i = 0; i < values.length; i++) {
				newArray[i] = values[i];
			}
			// set instance reference
			values = newArray;
		}
	}
	
	// remove method
	public void remove(T data)
	{
		if (values == null)
		{
			System.out.println("Vector is null (remove)");
			return;
		}
		else
		{
			for (int i = 0; i < values.length; i++)
			{
				if (values[i] == data)
				{
					// new array - one element
					T[] newArray = (T[])(new Object[values.length-1]);
					// for all elements before sought element
					for (int j = 0; j < i; j++) 
					{
						newArray[j] = values[j];
					}
					// for all elements past sought element
					for (int j = i+1; j > values.length; j++)
					{
						newArray[j] = values[j];
					}
					// set instance reference
					values = newArray;
					// first null element is decremented
					lastIndex--;
					return;
				}
			}
		}
	}
	
	// print method
	public void print()
	{
		if (values == null)
		{
			System.out.println("Vector is null (print)");
			return;
		}
		for (int i = 0; i < values.length; i++)
		{
			System.out.println(values[i]);
		}
	}
	
	// size method
	public int size()
	{
		if (values == null)
		{
			System.out.println("Vector is null (size)");
			return -1;
		}
		return values.length;
	}
	
	// count method
	public int count()
	{
		int count = 0;
		if (values == null)
		{
			System.out.println("Vector is null (count)");
			return -1;
		}
		for (int i = 0; i < values.length; i++)
		{
			if (values[i] != null)
				count++;
		}
		return count;
	}

	// get method
	public T get(int index)
	{
	    return values[index];
	}
	
}