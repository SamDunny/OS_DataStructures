/* by Sam Dunny
 * 
 */

public class BasicPCB implements Comparable <BasicPCB> {
	// instance variables
	private int id;
	private int programCounter;
	private int totalLines;
	private int priority;
	private double arrTick;
	private double compTick;
	
	// parameterized constructor
	public BasicPCB(int anID, int aLineAmt, double anArrTick)
	{
		this.setID(anID);
		this.setTotalLines(aLineAmt);
		this.setArrivalTick(anArrTick);
	}
	
	// accessors and Mutators
	public int getLinesLeft()
	{
		return totalLines - programCounter;
	}
	public int getID() {
		return id;
	}
	public void setID(int anID) {
		this.id = anID;
	}
	public int getProgramCounter() {
		return programCounter;
	}
	public void setProgramCounter(int aPC) {
		this.programCounter = aPC;
	}
	public int getTotalLines() {
		return totalLines;
	}
	public void setTotalLines(int aTL) {
		this.totalLines = aTL;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int aPriority) {
		this.priority = aPriority;
	}
	public double getArrivalTick() {
		return arrTick;
	}
	public void setArrivalTick(double anAT) {
		this.arrTick = anAT;
	}
	public double getCompletionTick() {
		return compTick;
	}
	public void setCompletionTick(double aCT) {
		this.compTick = aCT;
	}
	
	// hasCompleted method
	public boolean hasCompleted()
	{
		if (programCounter >= totalLines)
			return true;
		return false;
	}
	
	// nextLine method
	public void nextLine()
	{
		programCounter+=1;
	}
	
	// compareTo method
	public int compareTo(BasicPCB alt_pcb)
	{
		int ret;
		// return 1 if calling instance has lower priority
		if (this.priority < alt_pcb.getPriority())
			ret = 1;
		// return 0 is both instances have equal priority
		else if (this.priority == alt_pcb.getPriority())
			ret = 0;
		// return -1 otherwise
		else
			ret = -1;
	    return ret;
	}
	
	// toString method
	public String toString()
	{
		return "Process ID: "+id+" Program Counter: "+programCounter+" Total Lines: "+ totalLines;
	}	
}