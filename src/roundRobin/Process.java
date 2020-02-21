package roundRobin;


public class Process {
	String pID;
	public int arrivalTime, serviceTime, burstTime, completionTime;

	public Process(String pID, int arrivalTime, int burstTime) {
		this.pID = pID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.serviceTime = 0;
	}

}