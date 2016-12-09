package ie.gmit.sw;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
//import ie.gmit.sw.StringService;
//import ie.gmit.sw.StringServiceInterface;
/**
 * 
 * @author G00313177
 *
 *RMI server class called Servant,  creates the StringService service, creates the registry and bind's a name "stringService"
 *Based on the in class example
 */
public class Servant {
	//Runner method
	public static void main(String[] args) throws Exception {
			// Create the StringService variable
			StringServiceInterface service = new StringService();
			//Set up the registry on port 1099
			LocateRegistry.createRegistry(1099);
			//Bind the service to name "stringService" so that it can be called remotely
			Naming.rebind("stringService", service);
	}
	
}
