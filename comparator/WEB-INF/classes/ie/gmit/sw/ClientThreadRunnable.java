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
	Map<String, Resultator> outQueue;
	LinkedList<ClientRequest> inQueue;
	private ListIterator<ClientRequest> iterator;
	
	//Constructor
	public ClientThreadRunnable(Map<String, Resultator> outQueue, LinkedList<ClientRequest> inQueue){
		this.inQueue = inQueue;
		this.outQueue = outQueue;
		this.iterator = inQueue.listIterator(0);
	}
	
	//Base method for any runnable interface
	//@Override
	public void run() {
		try 
		{
			StringService service = (StringService) Naming.lookup("rmi://localhost:1099/stringService");
			while(true){
				ClientRequest currentJob = getNextJob(); 
				/**
				if (currentJob == null){
					return;
				}**/
				Resultator resultator = service.compare(currentJob.getStringOne(), currentJob.getStringTwo(), currentJob.getAlgo());
				addToOutQueue(currentJob.getJobID(), resultator);
					if(resultator.getResult() != null){
						addToOutQueue(currentJob.getJobID(), resultator);
					}
				while(resultator.isProcessed() == false);
				addToOutQueue(currentJob.getJobID(), resultator);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addToOutQueue(String jobID, Resultator resultator){
		outQueue.put(jobID, resultator);
	}
	
	public ClientRequest getNextJob(){
		if (iterator.hasNext()){
			return iterator.next();
		}
		else return null;
		//return this.inQueue.take();
	}


}
