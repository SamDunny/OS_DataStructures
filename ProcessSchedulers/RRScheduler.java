/* by Sam Dunny
 * 
 */

/* Round Robin (RR): 
 * this technique has a time quantum 
 * that determines how long each process runs
 * Runs on the basis of a circular queue
 * so as processes come in, they are added to the end of the queue
 * keep a table of remaining times after each quantum
 * preemptive
 */

/* My solution differs from the provided text, as in some cases,
 * the test file shows a progression through the current process 
 * that exceeds the time_quantum, but my output will strictly
 * stay below the quantum (I start progression at 0)
 * eg. 0-9, whereas test sometimes has 0-9 or 0-10
 */

public class RRScheduler extends BasicScheduler{

	// addProcess() can remain the same as it adds to back of queue
	// dispatch() can remain the same as it pops from the front of the list
	
	// instance variables
	private int time_quantum;
	private int incr;
	
	// parameterized constructor
	public RRScheduler(int quant) {
		this.time_quantum = quant;
	}
	
	// accessors and mutators
	public int getTimeQuantum() {
		return time_quantum;
	}
	public void setIncr(int val)
	{
		this.incr = val;
	}
	public int getIncr()
	{
		return incr;
	}
	
    // nextIncr method, increases increment counter
	public void nextIncr()
	{
		incr++;
	}
	
	// overriding BasicScheduler.updateRunningProcess()
	public void updateRunningProcess()
	{
		// dispatch if process if over
		if(runningProcess == null)
		{
			dispatch();
			// reset increment
			setIncr(0);
			return;
		}
		
		// process and increment counter both progress
		runningProcess.nextLine();
		nextIncr();
		
		if(runningProcess.hasCompleted())
		{
			runningProcess.setCompletionTick(tickCount);
			waitingTimeSum += (runningProcess.getCompletionTick() - runningProcess.getArrivalTick());
			// reset increment
			setIncr(0);
			dispatch();
		}
		
		// check if increment exceeds time quantum
		if (getIncr() >= time_quantum) {
			// runningProcess gets added to back of queue
			addProcess(runningProcess);
			// reset increment
			setIncr(0);
			// dispatch pops off head
			dispatch();
		}
	}
}