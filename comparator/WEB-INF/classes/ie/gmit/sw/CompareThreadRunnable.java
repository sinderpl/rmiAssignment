package ie.gmit.sw;
//import ie.gmit.sw.ClientRequest;
//import ie.gmit.sw.algoType;
/**
 * 
 * @author G00313177
 *
 *
 *Separate thread class as per the instructions, running as an asynchronous service
 */
public class CompareThreadRunnable implements Runnable{
	//Local vars
	private ClientRequest currentTask;
	algoType algo;
	ResultatorInterface resultator;
	String strOne;
	String strTwo;
	String strAlgo;
	
	//Constructor
	public CompareThreadRunnable(String strOne,String strTwo, String strAlgo, algoType algo, ResultatorInterface resultator){
		this.currentTask = currentTask;
		this.strOne = strOne;
		this.strTwo = strTwo;
		this.strAlgo = strAlgo;
		this.algo = algo;
		this.resultator = resultator;
	}
	
	//Run method, describes what the class should do once called
	public void run() {
		int distance;
		//Get the distance between the two
		distance = algo.distance(strOne, strTwo);
		try {
			//Try to change the resultator variable result
			resultator.setResult( strAlgo + " is: " + distance);
			//Pretened for the task to take longer than it did
			//Thread.sleep(1000);//change back to 100000
			//Thread.sleep(14000);
			//Set the resultator as finished
			//Thread.sleep(14000);
			resultator.setProcessed();
			System.out.println("compare completeed : " + resultator.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
