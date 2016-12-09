package ie.gmit.sw;
/**
 * 
 * @author G00313177
 *
 *Basic class to store the client requests for the in Queue
 */
public class ClientRequest {
	//Local variables
	String stringOne;
	String stringTwo;
	String algo;
	String jobID;
	
	//Contructor
	public ClientRequest(String stringOne, String stringTwo, String algo, String jobID){
		this.stringOne = stringOne;
		this.stringTwo = stringTwo;
		this.algo = algo;
		this.jobID = jobID;
	}
	
	//Get/Set stringOne
	public String getStringOne() {
		return stringOne;
	}

	public void setStringOne(String stringOne) {
		this.stringOne = stringOne;
	}
	//Get/Set stringTwo
	public String getStringTwo() {
		return stringTwo;
	}

	public void setStringTwo(String stringTwo) {
		this.stringTwo = stringTwo;
	}
	//Get/Set algo
	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}
	//Get/Set jobID
	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
}
