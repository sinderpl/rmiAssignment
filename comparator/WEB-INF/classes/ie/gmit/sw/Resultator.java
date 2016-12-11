package ie.gmit.sw;

import java.io.Serializable;
//import ie.gmit.sw.ResultatorInterface;
import java.rmi.RemoteException;

/**
 * 
 * @author G00313177
 *
 *Resultator class implementing the resultator interface
 */
public class Resultator implements ResultatorInterface, Serializable {
	//Variables
	//Result variable that is returned to the caller
	private String resultString;
	private static final long serialVersionUID = 1L;
	//Boolean to check whether the object has been processed
	private boolean isProcessed = false;
	
	//Constructor, initializes the object as not processed
	public Resultator()throws RemoteException{
	}
	//Return the result of the object job
	public String getResult() throws RemoteException{
		return resultString;
	}
	
	//Set the result of the object job
	public void setResult(String resulta)throws RemoteException{
		resultString = resulta;
	}
	//Checks if the job has been completed yet
	public boolean isProcessed() throws RemoteException{
		return isProcessed;
	}
	//Sets the processed tag to true/false
	public void setProcessed() throws RemoteException{
		isProcessed = true;
	}
}
