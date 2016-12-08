import java.rmi.RemoteException;

import ie.gmit.sw.Resultator.Resultator;
import ie.gmit.sw.StringService.StringServiceInterface;
/**
 * 
 * @author G00313177
 *
 *StringService class to implement the StringService interface
 */
public class StringService implements StringServiceInterface{
	//Variables 
	Resultator resultator ;
	//Constructor
	public StringService(){}
	
	public Resultator compare(String s, String t, String algo) throws RemoteException{
		resultator = new Resultator();
	}
}
