package ie.gmit.sw;
//import ie.gmit.sw.ClientRequest;
//import ie.gmit.sw.Resultator;
//import ie.gmit.sw.ResultatorInterface;
//import Resultator;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;

import javax.servlet.*;
import javax.servlet.http.*; 
/**
 * 
 * @author G00313177
 *
 *Handles the services, adapted from the online example
 */
public class ServiceHandler extends HttpServlet {
	//Local variables
	private String remoteHost = null;
	private static long jobNumber = 0;
	private static LinkedList<ClientRequest> inQueue = new LinkedList();
	private static Map<String, ResultatorInterface> outQueue = new ConcurrentHashMap<String, ResultatorInterface>();
        private boolean hasCompleted = false;
        private static ClientThreadRunnable clientJob = new ClientThreadRunnable(outQueue, inQueue);
	private static Thread clientWorker = new Thread(clientJob);
        //public int pass = 0;
        
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER");
                if(clientWorker.isAlive() != true){
			clientWorker.start();
		}
        }

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		out.print("<html><head><title>Distributed Systems Assignment </title>");		
		out.print("</head>");		
		out.print("<body>");
		if (taskNumber == null){
			ResultatorInterface temp = new Resultator();
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Assign the variables to a ClientRequest and add to inQueue
			inQueue.add(new ClientRequest(s, t, algorithm, taskNumber));
			outQueue.put(taskNumber, temp);
		}else{
			//Check out-queue for finished job
			ResultatorInterface resultator = (Resultator) outQueue.get(taskNumber);
                        out.print("<br><br><center>"+taskNumber+"<center>");
			if(resultator != null && resultator.isProcessed() && resultator.getResult() != null){
				out.print("<font color=\"#993333\"><b>");
				out.print("<center>The result of your request is: "+resultator.getResult()+"</center>");
				outQueue.remove(taskNumber);
				hasCompleted = true; 
			}
			else if(resultator != null && resultator.isProcessed() == false && resultator.getResult() != null){
				out.print("<font color=\"#993333\"><b>");
				out.print("Awaiting for the results to return");
                                //\ resultator.setProcessed();
			}
			else{
				out.print("<font color=\"#993333\"><b>");
				out.print("Please wait, your results are being computed");
                                /**
                                ResultatorInterface result = new Resultator();
                                result.setResult("completed locally");
                                result.setProcessed();
                                outQueue.put("T0", result);**/
			}
		}
		
		
		
		if(hasCompleted){
			out.print("<font color=\"#993333\"><b>");
			out.print("<br><br><center>THANK   YOU FOR USING THE SERVICE<center>");
                        hasCompleted = false;
		}
		else
		{
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
                /**   
		out.print("<br>This servlet should only be responsible for handling client request and returning responses. Everything else should be handled by different objects.");
		out.print("Note that any variables declared inside this doGet() method are thread safe. Anything defined at a class level is shared between HTTP requests.");				
		out.print("</b></font>");
                  
		out.print("<P> Next Steps:");	
		out.print("<OL>");
                
		out.print("<LI>Generate a big random number to use a a job number, or just increment a static long variable declared at a class level, e.g. jobNumber.");	
		out.print("<LI>Create some type of an object from the request variables and jobNumber.");	
		out.print("<LI>Add the message request object to a LinkedList or BlockingQueue (the IN-queue)");			
		out.print("<LI>Return the jobNumber to the client web browser with a wait interval using <meta http-equiv=\"refresh\" content=\"10\">. The content=\"10\" will wait for 10s.");	
		out.print("<LI>Have some process check the LinkedList or BlockingQueue for message requests.");	
		out.print("<LI>Poll a message request from the front of the queue and make an RMI call to the String Comparison Service.");			
		out.print("<LI>Get the <i>Resultator</i> (a stub that is returned IMMEDIATELY by the remote method) and add it to a Map (the OUT-queue) using the jobNumber as the key and the <i>Resultator</i> as a value.");	
		out.print("<LI>Return the result of the string comparison to the client next time a request for the jobNumber is received and the <i>Resultator</i> returns true for the method <i>isComplete().</i>");	
		out.print("</OL>");	
		**/
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
				
		//You can use this method to implement the functionality of an RMI client
		
		//
                }
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}