import java.io.*;
import java.util.*;
public class KernelDataStructuresTester {
/*
	private Vector<Integer> vector;
	private DoublyLinkedList<Integer> dLL;
	private HashTable<String,String> hashTable;*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setout
		final String DIV = "\n-------------------------------------------------------\n";
		System.out.println(DIV+"CSCE311 Kernel Data Structure Tester"+DIV);
		try
		{
			KernelDataStructuresTester test = new KernelDataStructuresTester();
			System.out.println(DIV+"Vector Tests"+DIV);
			boolean v1Test = test.vectorTest1();
			boolean v2Test = test.vectorTest2();
			
			System.out.println(DIV+"Double Linked List Tests"+DIV);
			boolean d1Test = test.dLLTest1();
			boolean d2Test = test.dLLTest2();
			
			System.out.println(DIV+"Hash Table Tests"+DIV);
			boolean h1Test = test.hashTableTest1();
			boolean h2Test = test.hashTableTest2();
			
			System.out.println(DIV+"Vector Test Results"+DIV);
			System.out.println("Vector Test 1 Succeeded? "+v1Test);
			System.out.println("Vector Test 2 Succeeded? "+v2Test);
			
			System.out.println(DIV+"Doubly Linked List Test Results "+DIV);
			System.out.println("Doubly Linked List Test 1 Succeeded? "+d1Test);
			System.out.println("Doubly Linked List Test 2 Succeeded? "+d2Test);
			
			System.out.println(DIV+"Hash Table Test Results "+DIV);
			System.out.println("Hash Table Test 1 Succeeded? "+h1Test);
			System.out.println("Hash Table Test 2 Succeeded? "+h2Test);
		}
		catch(Exception e)
		{
			printError("A run time error has occurred during testing "+ e.toString());
			e.printStackTrace();
		}
	}
	
	public void testVector()
	{
		
	}
	/*
	 * Add, Get(i), Size() Test
	 */
	public boolean vectorTest1()
	{

		Vector<Integer> vector = new Vector<Integer>();
		final int MAX = 1000;
		printTest("Adding values to the Vector");
		for(int i=0;i<MAX;i++)
		{
			vector.add(i);
		}
		printSuccess("No runtime errors during add");
		printTest("Checking size");
		if(vector.count() != MAX)
		{
			printError("Size "+vector.count()+" is incorrect. It could be a problem with add(T) or size()");
			return false;
		}
		printSuccess("Sizes Match");
		printTest("Checking each value in the Vector using get(i)");
		for(int i=0;i<MAX;i++)
		{
			if(i != vector.get(i))
			{
				printError("Values do not match. Expected "+i+" but got "+vector.get(i) +". Could be an error in either method add(T) or get(i)");
				return false;
			}
		}
		printSuccess("Value match");
		return true;
	}
	public boolean vectorTest2()
	{
		Vector<Integer> vector = new Vector<Integer>();
		final int MAX = 1000;
		printTest("Adding values to the Vector");
		for(int i=0;i<MAX;i++)
		{
			vector.add(i);
		}
		printSuccess("No runtime errors during add");
		printTest("Removing all elements from the Vector and testing with size()");
		for(int i=0;i<MAX;i++)
		{
			vector.remove(i);
		}
		if(vector.count() != 0)
		{
			printError("Size is incorrect. It could be a problem with remove(i) or size()");
			return false;
		}
		printSuccess("Remove works and sizes match");
		return true;
	}
	public boolean dLLTest1()
	{
		DoublyLinkedList<Integer> dLL = new DoublyLinkedList<Integer>();
		final int MAX = 1000;
		printTest("Adding values to the Doubly Linked List");
		for(int i=0;i<MAX;i++)
		{
			dLL.add(i);
		}
		printSuccess("No runtime errors during add");
		printTest("Checking size");
		if(dLL.size() != MAX)
		{
			printError("Size is incorrect. It could be a problem with add(T) or size()");
			return false;
		}
		printSuccess("Sizes Match");
		printTest("Checking each value in the Doubly Linked List using getCurrent()");
		for(int i=0;i<MAX;i++)
		{
			if(i != dLL.getCurrent())
			{
				printError("Values do not match. Could be an error in either method add(T), getCurrent(), gotoNext()");
				return false;
			}
			dLL.gotoNext();			
		}
		printSuccess("Values match");
		return true;
	}
	public boolean dLLTest2()
	{
		DoublyLinkedList<Integer> dLL = new DoublyLinkedList<Integer>();
		final int MAX = 1000;
		printTest("Adding values to the Doubly Linked List");
		for(int i=0;i<MAX;i++)
		{
			dLL.add(i);
		}
		printSuccess("No runtime errors during add");

		printTest("Removing all even values");
		for(int i=0;i<MAX;i++)
		{
			if(dLL.getCurrent() % 2 == 0)
				dLL.removeCurrent();
			else
				dLL.gotoNext();
		}
		printSuccess("No runtime errors during removeCurrent()");
		
		printTest("Resetting current, and using getCurrent to check valid values");
		dLL.resetCurrent();
		for(int i=1;i<MAX;i+=2)//Only counts through odd values
		{
			if(i != dLL.getCurrent())
			{
				printError("Values do not match. Expected "+i+" and got "+dLL.getCurrent()+". Could be an error in either method resetCurrent(), removeCurrent(), getCurrent(), gotoNext()");
				return false;
			}
			dLL.gotoNext();
		}
		printSuccess("Values match");
		return true;
	}
	/*
	 * Testing Add
	 */
	public boolean hashTableTest1()
	{
		HashTable<String,Integer> hashTable = new HashTable<String,Integer>();
		final int MAX = 1000;
		final String NUMBER = "Number";
		printTest("Adding values to the Hash Table");
		for(int i=0;i<MAX;i++)
		{
			hashTable.add(NUMBER+i,i);
		}
		printSuccess("No runtime errors during add");
		printTest("Checking the count");
		if(hashTable.count() != MAX)
		{
			printError("Count "+hashTable.count()+" is incorrect. It could be a problem with add(K,T) or count()");
			return false;
		}
		printSuccess("Counts Match");
		printTest("Checking if all elements are stored");
		for(int i=0;i<MAX;i++)
		{
			if(hashTable.get(NUMBER+i) == null)
			{
				printError("A number expected to be found in the table was not in the table");
				return false;
			}
		}
		printSuccess("All values were stored correctly");
		return true;
	}
	public boolean hashTableTest2()
	{
		HashTable<String,Integer> hashTable = new HashTable<String,Integer>();
		final int MAX = 1000;
		final String NUMBER = "Number";
		printTest("Adding values to the Hash Table");
		for(int i=0;i<MAX;i++)
		{
			hashTable.add(NUMBER+i,i);
		}
		printSuccess("No runtime errors during add");
		printTest("Removing elements with a given key");
		for(int i=0;i<MAX;i++)
		{
			if(hashTable.remove(NUMBER+i) == null)
			{
				printError("A number expected to be found in the table was not in the table");
				return false;
			}
		}
		printSuccess("No run time errors during deletion");
		printTest("Checking count to verify all elements were removed");
		if(hashTable.count() != 0)
		{
			printError("Counts did not match. Could be an error in remove(K) or in count()");
			return false;
		}
		printSuccess("The counts matched, so we can assume all elements were removed");
		return true;
	}
	public static void println(String s)
	{
		System.out.println(s);
	}
	public static void printError(String s)
	{
		System.out.println("ERROR: "+s);
	}
	public static void printSuccess(String s)
	{
		System.out.println("SUCCESS: "+s);
	}
	public static void printTest(String s)
	{
		System.out.println("TEST: "+s);
	}
}