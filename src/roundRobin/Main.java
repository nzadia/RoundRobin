package roundRobin;

import roundRobin.Process;
import java.util.*;
import java.io.*;

public class Main {
	public static void main (String [] args) {
	ArrayList<Process> processes = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	System.out.print("Please enter filename: "); 
	String fileName = scan.nextLine();
	System.out.print("Please enter Time Quantum: ");
	int timeQuantum = scan.nextInt();
	
	try{ //file is going to be read and each parameter is going to be parsed through the file. 
		FileReader reader = new FileReader(fileName);
		Scanner read = new Scanner(reader);
		read.nextLine();
		while (read.hasNextLine())
		{
			String line = read.nextLine();
			String[] arr = line.split(",");
			processes.add(new Process((arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
		}
		Scheduler myScheduler = new Scheduler(processes, timeQuantum);
		myScheduler.roundRobin();
		read.close();
		scan.close();
	} 
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 

}
}
