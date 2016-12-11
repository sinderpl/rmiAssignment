package ie.gmit.sw;
import java.rmi.Remote;
//import ie.gmit.sw.Resultator;
import java.rmi.RemoteException;

/**
 * 
 * @author G00313177
 *
 *String service interface to expose the Resultator compare method
 */
public interface StringServiceInterface extends Remote{
	//Compares two strings, takes in the algorithm to use, returns a Resultator object
	//in order to check whether the job has been completed yet
	public ResultatorInterface compare(String s, String t, String algo) throws RemoteException;
}
