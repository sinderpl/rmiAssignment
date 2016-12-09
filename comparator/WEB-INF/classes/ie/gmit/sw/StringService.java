package ie.gmit.sw;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//import ie.gmit.sw.AlgorithmFactory;
//import ie.gmit.sw.algoType;
//import ie.gmit.sw.Resultator;
//import ie.gmit.sw.StringServiceInterface;
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
	private static AlgorithmFactory factory = AlgorithmFactory.getInstance();
	
	//Constructor
	public StringService() throws RemoteException{
	}
	
	
	//Implementation of the compare method, calls the correct algorithm and compares the two strings
	//returns and instance of the Resultator class to make it possible to check up on the status of the job
	public Resultator compare(String s, String t, String strAlgo) throws RemoteException{
		algoType algoInstance = factory.createAlgorithm(strAlgo);
		resultator = new Resultator();
		
		//The comparing is handled by a runnable thread here
		CompareThreadRunnable threadTask = new CompareThreadRunnable(s, t, strAlgo, algoInstance, resultator);
		Thread compareThread = new Thread(threadTask);
		compareThread.start();
			
		return resultator;
	}
}
