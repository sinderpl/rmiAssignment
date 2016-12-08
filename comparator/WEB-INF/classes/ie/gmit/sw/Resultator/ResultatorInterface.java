package ie.gmit.sw.Resultator;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 
 * @author G00313177
 *
 *Resultator interface allowing for the calling of the remote methods
 *
 */

public interface ResultatorInterface extends Remote{

	//Return the result of the object job
	public String getResult() throws RemoteException;
	//Set the result of the object job
	public void setResult(String result)throws RemoteException;
	//Checks if the job has been completed yet
	public boolean isProcessed() throws RemoteException;
	//Sets the processed tag to true/false
	public void setProcessed() throws RemoteException;
}
