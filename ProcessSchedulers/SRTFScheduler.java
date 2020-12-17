/* by Sam Dunny
 * 
 */

/* Shortest Remaining Time First (SRTF): 
 * the shortest burst process will run first
 * in the case of ties, chooses by first arrival
 * allows for partial completion
 * keep a track of remaining times for each process
 * preemptive
 */

/* My solution differs from the provided text, as in some cases,
 * the test file will arbitrarily choose a process to run if two
 * processes have the same amount of time left, whereas my code
 * will choose the one that arrived first
 */

import java.util.LinkedList;
import java.util.Queue;

public class SRTFScheduler extends BasicScheduler{

	/* dispatch does not need to be overridden, as addProcess() 
	 * rearranges the queue automatically
	 */
	
	// overriding BasicScheduler.updateRunningProcess()
	public void updateRunningProcess()
	{
		// if running process is null, go get another process
		if(runningProcess == null)
		{
			dispatch();
			return;
		}
		
		// otherwise, find out if you need to swap processes
		boolean need_to_swap = false;		
		
		// assuming queue is not empty and you have something to swap with
		if (readyQ.peek() != null) {
			// get time remaining on this process
			int timeRem = runningProcess.getLinesLeft();
			// get minimum time from queue (should be head)
			int minTime = readyQ.peek().getLinesLeft();
			
			// validity check
			//System.out.println("MINTIME: (" + minTime + ") vs TIMEREM: ("+ timeRem + ")");
			
			if (minTime < timeRem)
			{
				// we need to swap processes
				need_to_swap = true;
				// add current process to queue again
				//System.out.println("SWAPPED, ADDED " + timeRem + " BACK ON QUEUE");
				addProcess(runningProcess);
			}
		}
		
		if (need_to_swap == false)
		{
			// proceed through current process
			runningProcess.nextLine();
		}
		else if (need_to_swap == true)
		{
			// fetch minimum time process
			dispatch();
			// proceed through new process
			runningProcess.nextLine();
		}
		else
		{
			System.out.println("BAD");
		}
				
		// if you've just reached the end of the process, go get another process
		if(runningProcess.hasCompleted())
		{
			runningProcess.setCompletionTick(tickCount);
			waitingTimeSum += (runningProcess.getCompletionTick() - runningProcess.getArrivalTick());
			dispatch();
		}
	}
	
	// overriding BasicScheduler.addProcess()
	/* instead of searching every time for next lowest totalLines,
	 * just rearrange queue to be in order every time you add new
	 * element. That way, dispatch pops head, which is automatically
	 * lowest. Not most efficient algorithm, but it works
	 */
	public void addProcess(BasicPCB p)
	{
		// increase process counter
		totalProcesses++;
		// add to queue
		readyQ.add(p);
		
		// transition from queue to array (for sorting and rearanging purposes)
		BasicPCB[] temp_arr = readyQ.toArray(new BasicPCB[readyQ.size()]);

		// sort array (bubble sort)
		int n = temp_arr.length;
	    BasicPCB pcb_temp; 
	    for(int i=0; i < n; i++)
	    {
	      	for(int j=1; j < (n-i); j++)
	       	{
	      		// getLinesLeft becuase this is preemptive
	       		if(temp_arr[j-1].getLinesLeft() > temp_arr[j].getLinesLeft())
	       		{
	       			//swap elements
	                pcb_temp = temp_arr[j-1];
	                temp_arr[j-1] = temp_arr[j];
	                temp_arr[j] = pcb_temp;
	            }      
	        }
	    }
	        
	    // create a new queue
	    Queue<BasicPCB> temp_q = new LinkedList<BasicPCB>();
	        
	    // turn back into queue
	    for (int i = 0; i < temp_arr.length; i++)
	    {
	      	// add sorted elements back into queue
	      	temp_q.add(temp_arr[i]);
	    }
	        
	    // reset reference
	    readyQ = temp_q;
	}	
}