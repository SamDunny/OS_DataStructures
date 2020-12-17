/* by Sam Dunny
 * 
 */

/* Shortest Job First (SJF): 
 * heavily dependent on arrival time
 * whichever process has the shortest burst time in the wait queue gets processed next
 * non-preemptive
 */

/* My solution differs from the provided text, as in some cases,
 * the test file will arbitrarily choose a process to run if two
 * processes have the same amount of time left, whereas my code
 * will choose the one that arrived first
 */

import java.util.LinkedList;
import java.util.Queue;

public class SJFScheduler extends BasicScheduler{
	
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
		
		// transition from queue to array (for sorting and rearranging purposes)
		BasicPCB[] temp_arr = readyQ.toArray(new BasicPCB[readyQ.size()]);

		// sort array (bubble sort)
		int n = temp_arr.length;
        BasicPCB pcb_temp; 
        for(int i=0; i < n; i++)
        {
        	for(int j=1; j < (n-i); j++)
        	{
        		// only have to worry about total lines because this is not preemptive
        		if(temp_arr[j-1].getTotalLines() > temp_arr[j].getTotalLines())
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