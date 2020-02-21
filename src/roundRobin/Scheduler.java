package roundRobin;

import java.util.*;

public class Scheduler {
	Process cpu = null;
	public int processTime=0;
	public int timeQuantum, contextSwitch, counter;
	ArrayList<Process> processList, readyQueue, endProcesses;	
	double TotalTurnaround = 0.0, totalWaitingtime = 0.0, TotalUtilization = 0.0;
	double waitingTime, turnaroundTime, burstTime, cpuUtilization, throughput;
	
	public Scheduler(ArrayList<Process> csvFile, int timeQuantum) {
		this.timeQuantum = timeQuantum;
		this.processList = csvFile;
	}

	public void roundRobin() {
		contextSwitch = 0;
		readyQueue = new ArrayList<>();
		endProcesses = new ArrayList<>();

		while (!readyQueue.isEmpty() || !processList.isEmpty() || cpu != null) {
			
			for (int i = 0; i < processList.size(); i++) {
				if (processList.get(i).arrivalTime == processTime) {
					readyQueue.add(processList.remove(i));
				}
			} 

			if (cpu == null) {
			 cpu = readyQueue.remove(0);
			}
			counter++;
			cpu.serviceTime++;
			
			if (cpu.burstTime == cpu.serviceTime) { 
				cpu.completionTime = processTime; 
				endProcesses.add(cpu);
				cpu = null;
				contextSwitch++;
				counter = 0;
				
			} 
			else if (counter == timeQuantum) { 
				readyQueue.add(cpu);
				cpu = null;
				contextSwitch++;
				counter = 0;
			}
			
			processTime++;
			
		}

		for (int i = 0; i < endProcesses.size(); i++) {
			TotalTurnaround = (TotalTurnaround+ (endProcesses.get(i).completionTime - endProcesses.get(i).arrivalTime));
			totalWaitingtime = (totalWaitingtime +(endProcesses.get(i).completionTime - endProcesses.get(i).arrivalTime) - endProcesses.get(i).burstTime);
			TotalUtilization = (TotalUtilization + (endProcesses.get(i).burstTime));
		} 
		 turnaroundTime = TotalTurnaround / endProcesses.size();
		 waitingTime = totalWaitingtime / endProcesses.size();
		 cpuUtilization = (TotalUtilization - (contextSwitch * .01)) / processTime;
		 throughput = (double) endProcesses.size() / processTime;

		System.out.print("CPU Utilization: " + cpuUtilization +'\n'+  "Throughput: " + throughput+'\n'+ "Average Waiting Time: " + waitingTime +'\n'+  "Average Turnaround Time: " + turnaroundTime);

	}

}

