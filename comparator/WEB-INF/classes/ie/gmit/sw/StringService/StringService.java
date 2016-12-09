import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ie.gmit.sw.Algorithms;
import ie.gmit.sw.Resultator;
import ie.gmit.sw.StringService.StringServiceInterface;
/**
 * 
 * @author G00313177
 *
 *StringService class to implement the StringService interface
 */
public class StringService extends UnicastRemoteObject implements StringServiceInterface{
	
	//Variables 
	private static final long serialVersionUID = 1L;
	Resultator resultator ;
	AlgorithmFactory factory = AlgorithmFactory.getInstance();
	
	//Constructor
	public StringService(){
		//algoComp = new AlgorithmComparer();
	}
	
	
	//Implementation of the compare method, calls the correct algorithm and compares the two strings
	//returns and instance of the Resultator class to make it possible to check up on the status of the job
	public Resultator compare(String s, String t, String strAlgo) throws RemoteException{
		algoType algoInstance = factory.getAlgorithm(algo);
		resultator = new Resultator();
		
		//The comparing is handled by a runnable thread here
		CompareThreadRunnable threadTask = new CompareThreadRunnable(s, t, strAlgo, algo, resultator);
		Thread compareThread = new Thread(threadTask);
		compareThread.start();
			
		return resultator;
	}
}
