package ie.gmit.sw;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * @author G00313177
 *
 *This was orignially a tester class but you can run it if you want to see the application run
 *I had some issues using the string jar with the service handler, most likely because of the threads not returning
 *an answer on time but this should work fine.
 */

public class Client {
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		
		LinkedList<ClientRequest> inQueue = new LinkedList<ClientRequest>();
		Map<String, ResultatorInterface> outQueue = new ConcurrentHashMap<String, ResultatorInterface>();
		ClientThreadRunnable clientJob = new ClientThreadRunnable(outQueue, inQueue);
		boolean isRunning = true;
		Resultator res = new Resultator();
		
		inQueue.add(new ClientRequest("asd", "sdsdsda","Damerau-Levenshtein Distance" , "T0"));
		//inQueue.add(new ClientRequest("str", "stra", "Damerau-Levenshtein Distance", "T0"));
		//outQueue.put("T0", res);
		
		
		Thread clientWorker = new Thread(clientJob);
		if(clientWorker.isAlive() != true){
			clientWorker.start();
		}
		
		//System.out.println("queue res after client thread start: " + qres.isProcessed());
		
		
		
		
		while(isRunning){
			try {
			    Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			ResultatorInterface rest = outQueue.get("T0");
			String output = rest.getResult();
			System.out.println("result is : " + output);
			
			if (rest.isProcessed() == true && rest.getResult() != null){
				System.out.println("It has been processed");
				isRunning = false;
			}else{
				System.out.println("It has not been processed");
			}
		}
		
		//Resultator resultator = ;''
		//resultator.setProcessed();
		
			
		
		/**
		StringServiceInterface ss = (StringServiceInterface) Naming.lookup("rmi://localhost:1099/stringService");
		Resultator resultator = ss.compare("testsd", "testba", "Damerau-Levenshtein Distance");
		resultator.setProcessed();
		System.out.println("result is : " + resultator.getResult());
		if (resultator.isProcessed()){
			System.out.println("It has been processed");
		}else{
			System.out.println("It has not been processed");
		}
		
		**/
		/**
		LinkedList<ClientRequest> inQueue = new LinkedList<ClientRequest>();
		Map<String, ResultatorInterface> outQueue = new ConcurrentHashMap<String, ResultatorInterface>();
		
		//StringServiceInterface ss = (StringServiceInterface) Naming.lookup("rmi://localhost:1099/stringService");
		//System.out.println("String Interface created and saved");
		
		ClientThreadRunnable clientJob = new ClientThreadRunnable(outQueue, inQueue);
		Thread clientWorker = new Thread(clientJob);
		if(clientWorker.isAlive() != true){
			clientWorker.start();
		}
		
		ResultatorInterface temp = new Resultator();
		String taskNumber = new String("T" + 0);
		//jobNumber++;
		//Assign the variables to a ClientRequest and add to inQueue
		inQueue.add(new ClientRequest("se", "sa", "Damerau-Levenshtein Distance", taskNumber));
		outQueue.put(taskNumber, temp);
		ResultatorInterface resultator = outQueue.get(taskNumber);
		System.out.println("check if result is there");
		System.out.println(resultator.getResult());
		
		//StringService ss = new StringService();
		//Resultator resultator = ss.compare("testa", "testb", "Damerau-Levenshtein Distance");
		System.out.println("Resultator initiated");
		System.out.println(resultator.getResult());**/
	}

}
