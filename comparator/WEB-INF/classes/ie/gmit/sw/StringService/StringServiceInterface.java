import ie.gmit.sw.Resultator.Resultator;
import java.rmi.RemoteException;

/**
 * 
 * @author G00313177
 *
 *String service interface to expose the Resultator compare method
 */
public interface StringServiceInterface {
	//Compares two strings, takes in the algorithm to use, returns a Resultator object
	//in order to check whether the job has been completed yet
	public Resultator compare(String s, String t, String algo) throws RemoteException;
}
