package ie.gmit.sw;
/**
 * 
 * @author G00313177
 *
 *A runnable class enabling the client to query the outQueue to check whether the service has been completed.
 */
import java.rmi.Naming;
import java.util.Map;
import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.*;

public class ClientThreadRunnable implements Runnable {
	
	//Local variables
	Map<String, ResultatorInterface> outQueue;
	LinkedList<ClientRequest> inQueue;
	private ListIterator<ClientRequest> iterator;
	private boolean isRunning = true;
	private int pass ;
	
	//Constructor
	public ClientThreadRunnable(Map<String, ResultatorInterface> outQueue, LinkedList<ClientRequest> inQueue){
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		this.iterator = inQueue.listIterator(0);
		pass = 0;
	}
	
	//Base method for any runnable interface
	//@Override
	public void run() {
		
		try 
		{
			//initialise empty vars for future use
			ClientRequest currentJob = null;
			ResultatorInterface resultator = null;
			
			//Connect to the rmi string service
			StringServiceInterface service = (StringServiceInterface) Naming.lookup("rmi://localhost:1099/stringService");
			//run a loop on the service
			while(isRunning){
				if (currentJob == null){
				currentJob = getNextJob();
				}
				else{
					do{ 
						//compare the strings and return a result
					resultator = service.compare(currentJob.getStringOne(), currentJob.getStringTwo(), currentJob.getAlgo());
					if (resultator.getResult() != null){
						resultator.setProcessed();
					}
					addToOutQueue(currentJob.getJobID(), resultator);
						if(resultator.getResult() != null){
							System.out.println("results got");
							addToOutQueue(currentJob.getJobID(), resultator);
							pass ++;
							currentJob = null;
					}
					}while(resultator.isProcessed() == false);
				
				addToOutQueue(currentJob.getJobID(), resultator);
			}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addToOutQueue(String jobID, ResultatorInterface resultator){
		outQueue.put(jobID, resultator);
	}
	
	public ClientRequest getNextJob(){
		if (inQueue.size() != 0){
			return inQueue.get(pass);
		}
		else return null;
		//return this.inQueue.take();
	}


}
