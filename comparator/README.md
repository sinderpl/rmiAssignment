# rmiAssignment
Student : G00313177

I have created a asynchronous Java and Tomcat RMI application for finding the distance between two strings.

Algorithms:
There are 3 basic algorithms implemented. All of those implement the abstract interface "algoType" so that they can be created and returned by a singleton factory "Algorithm Factory.

Threads:
"ClientThreadRunnable" implements the client side of the RMI service by dispatching requests to the remote "stringService" interface. The "stringService" interface then creates a thread "CompareThreadRunnable" which then chooses the right algorithm and fires up the distance finding.

Objects:
I have created the "Resultator" object which stores the result of the distance search as well as being available for the client to query it on whether the search has been completed or not. 
The "ClientRequest" object has been created for the use in the inQueue linked lists so that I can have more control over what goes into each request.

Server:
There are the "ServiceHandler" and "Servlet" classes which control the front end and the stringService respectively.


Running the application:
1. Start the Tomcat application using the command : service tomcat8 start
2. Navigate to the directory in which the jar file is located and run teh following command : java -cp ./string-service.jar ie.gmit.sw.Servant
3.Open a web browser and got to: localhost:8080
4. Click on the "Manager webapp" hyperlink
5. Log in.
6. Under the "War file to deploy" heading choose the project comparator.war file and click deploy.
7. Click on the /Comparator hyperlink in the Applications menu near the top.
