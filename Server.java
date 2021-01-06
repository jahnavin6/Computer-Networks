/*This is the Socket Programming in Computer Networks.

Particular references has been provided above each and every method in the program,
on how it functions and works in the code. Here are few of the references for methods in the code.

- https://stackoverflow.com/questions/33087890/multithreading-with-client-server-program
- https://www2.seas.gwu.edu/~cheng/6431/Projects/Project1WebServer/webserver.html 
- http://web.eecs.utk.edu/~jysun/files12Spring/Solutions_Programming_Assignment_1.html

Complete references can be found in the readme file. */

import java.io.*;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.StringTokenizer;
import java.net.*;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.lang.*;


//https://www2.seas.gwu.edu/~cheng/6431/Projects/Project1WebServer/webserver.html
//https://www.geeksforgeeks.org/introducing-threads-socket-programming-java/
//http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html 
//https://www.youtube.com/watch?v=ZIzoesrHHQo
//http://net-informations.com/java/net/multithreaded.htm
//https://www.javaworld.com/article/2853780/socket-programming-for-scalable-systems.html

final class communicateclient implements Runnable 
{
	
	Socket s1;

	public void run() 
	{
		try
		{
			print_param(); //calling function for the print statements
		}
			catch (Exception er)
			{
				System.out.println(er);
			}
	}

	private void print_param() throws Exception
	{
		try 
		{	
			//https://www.javatpoint.com/java-serversocket-getremotesocketaddress-method
			SocketAddress sa = s1.getRemoteSocketAddress();  //returns the socket address to which is gets connected
			
			//https://www.javatpoint.com/java-serversocket-getlocalport-method
			System.out.println("From port " + s1.getLocalPort() + "\n"); //It provides the port number

			//https://stackoverflow.com/questions/11143880/why-does-inetaddress-getlocalhost-gethostname-return-a-value-different-from
			System.out.println("Host Name of the server: " + InetAddress.getLocalHost().getHostName() + "\n"); //returns hostname
			
			//https://www.javatpoint.com/java-datagramsocket-getport-method
			System.out.println("Port to which the request was forwarded " + s1.getPort() + "\n"); //returns the port number

			//System.out.println("name" + s1.getByName() + "\n");
			 /*InetAddress  ipa = InetAddress.getLocalHost();
			 String  hn = ipa.getHostName();
			  System.out.println("Computer Name : " + hn);*/

			//https://stackoverflow.com/questions/5757900/gethostaddress-and-getinetaddress-in-java
			System.out.println("Client got Connected " + s1.getInetAddress() + "\n"); //returns the IP Address
			
			//https://www.codota.com/code/java/methods/java.net.InetAddress/getLocalHost
			System.out.println("the IP Address : " + InetAddress.getLocalHost() + "\n"); //returns the InetAddress of the localhost

			//https://www.codota.com/code/java/methods/java.net.Socket/getLocalAddress
			//System.out.println("Of Host " + s1.getLocalAddress() + "\n"); //returns local IP Address
			//https://users.cs.duke.edu/~chase/cps196/slides/sockets.pdf
			System.out.println("Server connection successful, the Socket Address is: " + sa.toString()); //returns the socket address
			//if the server connection is successful, then the server obtains a new socket with a different port
			concurrentprocess();
		} catch (Exception ex1) 
		{
			System.out.println(ex1);
		}

	}
	
	//https://www.codota.com/code/java/methods/java.io.InputStream/read
	private static void addressinfo(FileInputStream contentf, OutputStream oparameters) throws Exception 
	{
		byte[] flow_b = new byte[1024]; //creates a size of 1024 bytes
		boolean var = true;
		while(var)
		{
			int ct = contentf.read(flow_b);
			if(ct == -1)
			{
				break;
			}
			oparameters.write(flow_b, 0, ct);
			//sends the file to client overall
		}
	}	
	
	//https://www2.seas.gwu.edu/~cheng/6431/Projects/Project1WebServer/webserver.html
	//http://zetcode.com/java/readtext/
	
	private void concurrentprocess() throws Exception
	{
		
		//https://www.codota.com/code/java/methods/java.net.URLConnection/getOutputStream
		DataOutputStream oparameters = new DataOutputStream(s1.getOutputStream());  //OutputStream to write data

		//https://www.tutorialspoint.com/java/lang/process_getinputstream.htm
		InputStream iparameters = s1.getInputStream(); //The input stream of the process
		
		//https://www.javatpoint.com/java-bufferedreader-class
		BufferedReader br = new BufferedReader(new InputStreamReader(iparameters)); //create a buffered character input stream
		
		//https://stackoverflow.com/questions/8560395/how-to-use-readline-method-in-java
		String codecall = br.readLine(); //retrieves the request message which is the codecall
		
		System.out.println(codecall);
		
		//https://www.javatpoint.com/string-tokenizer-in-java
		StringTokenizer ts1 = new StringTokenizer(codecall); //retrieves the file name from the provided request line
		
		//https://www.geeksforgeeks.org/stringtokenizer-nexttoken-method-in-java-with-examples/
		String callfor = ts1.nextToken(); //return the next token one after another from this StringTokenizer.
		
		String detailsoff = ts1.nextToken();

		// Open the requested file
		//// https://www2.seas.gwu.edu/~cheng/6431/Projects/Project1WebServer/webserver.html
		FileInputStream contentf = null;
		
		boolean displaypresent = true;       
		
		// https://www2.seas.gwu.edu/~cheng/6431/Projects/Project1WebServer/webserver.html
        //http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html
		try 
		{
			contentf = new FileInputStream(detailsoff);
		} catch (FileNotFoundException fnfe) 
		
		{
			displaypresent = false;
		}

		//constructing the response message
		//http://www.ccs.neu.edu/home/rraj/Courses/U640/F04/Programs/WebServer.java

		String displayformat = "";
		
		String displaytype = "";
		
		String filecontents = "";

		//displays the msg "HTTP/1.1 200 OK" if it's present
		if (displaypresent)
		{
			//https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
			displayformat = "HTTP/1.1 200 OK" + "\n"; //it means that the request was successful
			displaytype = "Display Format" + formatextension(detailsoff) + "\n";
		} 

		//http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html


        //displays error message if it's not found
        else 
		{
			displayformat = "http//1.0 400 Bad Request" + "page cannot be displayed" + "\n"; //This means that the page cannot be found
			
			displaytype = "Content-type: " + "text/html" + "\n";    
			
			filecontents = "<!DOCTYPE HTML>" + "<html>" + "<h1>Error Page not found</h1>" + "</html>";
		}
	
		oparameters.writeBytes(displayformat);		
		oparameters.writeBytes(displaytype);		 
		
		//displays the content of the html file if it matches with its location and parameters
		if (displaypresent) 
		{
			System.out.println("File present in the specified path or location location");
			
			addressinfo(contentf, oparameters);       //the file presence is verified at that path as of display format
			oparameters.writeBytes(displayformat);
			oparameters.writeBytes(displaytype);
			contentf.close();
		} 

		else 
		{
			System.out.println("File not present in the specified path or location");
			oparameters.writeBytes(displayformat);
			oparameters.writeBytes(filecontents);					
			oparameters.writeBytes(displaytype);
		}

		//displays the type of file
		System.out.println("File path and its name: " + detailsoff);  
		
		String title = null;

		for(;(title = br.readLine()).length() != 0;)
			System.out.println(title);

		oparameters.close();   
		br.close();
		iparameters.close();     		
		s1.close();

	}

	public communicateclient(Socket s1) throws Exception 
	{
		this.s1 = s1;
	}

    //https://www2.seas.gwu.edu/~cheng/6431/Projects/Project1WebServer/webserver.html
    //this method provides the file extensions that will be compatible to display the contents
	private static String formatextension(String detailsoff)
	{	
		
		if (detailsoff.endsWith(".htm") || detailsoff.endsWith(".html") || detailsoff.endsWith(".txt"))
		{
			return "text/html";
		}

		else if (detailsoff.endsWith(".gif"))
		{
			return "image/gif";
		}
		
		else if (detailsoff.endsWith(".jpeg") || detailsoff.endsWith(".png") || detailsoff.endsWith(".jpg"))
		{
			return "image/jpeg";
		}
		
		return "other files format";
	}
}

//https://www2.seas.gwu.edu/~cheng/6431/Projects/Project1WebServer/webserver.html
//https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip
public final class Server 
{
	
	public static void main(String argv[]) throws Exception
	{
		
		int port = 8080; //default port number
		
		System.out.println("Begin Server .... ");
		
		ServerSocket w_st = new ServerSocket(port); //server connection

		boolean a = true;

		//starts communicating with the client by establishing the connection
		for(;a;)
		{
			Socket communicate = w_st.accept();
			communicateclient send_msg = new communicateclient(communicate);
			//for processing the request, new thread will be created.
			Thread t1 = new Thread(send_msg);
			t1.start(); //this initiates the thread
		}
	}
}
