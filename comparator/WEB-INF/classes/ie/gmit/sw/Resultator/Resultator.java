package ie.gmit.sw.Resultator;

import ie.gmit.sw.Resultator.ResultatorInterface;

import java.rmi.RemoteException;

/**
 * 
 * @author G00313177
 *
 *Resultator class implementing the resultator interface
 */
public class Resultator implements ResultatorInterface{
	//Variables
	//Result variable that is returned to the caller
	private String result;
	//Boolean to check whether the object has been processed
	private boolean isProcessed;
	
	//Constructor, initializes the object as not processed
	public Resultator(){
		isProcessed = false;
	}
	//Return the result of the object job
	public String getResult() throws RemoteException{
		return result;
	}
	
	//Set the result of the object job
	public void setResult(String result)throws RemoteException{
		this.result = result;
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
