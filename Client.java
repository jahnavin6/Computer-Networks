/*This is the Socket Programming in Computer Networks.

Particular references has been provided above each and every method in the program,
on how it functions and works in the code. Here are few of the references for methods in the code.
- http://net-informations.com/java/net/multithreaded.htm

Complete references can be found in the readme file. */

//https://www.infoworld.com/article/2853780/socket-programming-for-scalable-systems.html
import java.io.*;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.StringTokenizer;
import java.net.*;
import java.util.*;
import java.io.InputStreamReader;
import java.lang.*;

//https://stackoverflow.com/questions/33087890/multithreading-with-client-server-program

public class Client 
{

	private Socket clientside(String s_1, int p_1) throws Exception 
	{
		Socket s_2;
		try 
		{
			//https://docs.oracle.com/javase/7/docs/api/java/net/InetSocketAddress.html
			InetAddress ia = InetAddress.getByName(s_1);
			//https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
			SocketAddress sa = new InetSocketAddress(ia, p_1);			
			s_2 = new Socket();
			
			int max_time = 50 * 2000; //maximum time allowed
			s_2.connect(sa, max_time);
			//returning the socket max_time
			return s_2;
			
		} catch (SocketTimeoutException ste) 
		{
			//reference : https://stackoverflow.com/questions/3163399/difference-between-system-out-println-and-system-err-println#:~:text=err.,err%20writes%20to%20a%20file.
			System.err.println("You've reached maximum time.."); //exception for timeout
			//https://www.geeksforgeeks.org/3-different-ways-print-exception-messages-java/
			ste.getMessage();
			throw ste;
		}
	}


	public Client() 
	{
		String stitle = "localhost"; //the title
		int port = 8080; //default port number

		
		try 
		{
			//socket creation : https://stackoverflow.com/questions/33087890/multithreading-with-client-server-program
			Socket ss4 = clientside(stitle, port); // opening the socket and respective IP address, host number and connection values are printed
			//https://www.geeksforgeeks.org/java-program-find-ip-address-computer/
			System.out.println("IP Address: " + InetAddress.getLocalHost() + "\n");
			 //Peername netPeerGroup = Peername();
			//https://crunchify.com/how-to-get-server-ip-address-and-hostname-in-java/#:~:text=In%20Java%2C%20you%20can%20use,of%20the%20current%20Server%20name.  
			System.out.println("HostName: " + InetAddress.getLocalHost().getHostName() + "\n");
			System.out.println("On port " + ss4.getPort() + "\n");
			System.out.println("Port to which the request was forwarded " + ss4.getLocalPort() + "\n");
			//https://www.tutorialspoint.com/java/java_networking.htm 
			
			//System.out.println("Connected to client " + ss4.getInetAddress() + "\n");
			//System.out.println("Of the host " + ss4.getLocalAddress() + "\n");
			//System.out.println("Peer Name" + socket.getPeerName());
			//System.out.println("Peer Name" + netPeerGroup.getPeerName());
			
			SocketAddress sappend = ss4.getRemoteSocketAddress();

			System.out.println("Connected to Client, with the Address: " + sappend.toString() + "\n");
			long inittimeofsys = System.currentTimeMillis();
			
			
			String filedetails = "index.html"; //giving the file name or path with the extension

			String finalop = retrievedetails(ss4, "GET " + filedetails + " pe: text/html\n\n"); //retrieve the file contents	
			System.out.println(finalop);
			
			long mostrecenttime = System.currentTimeMillis(); //declaring variables for the RoundTripTime
			long rt_time = mostrecenttime - inittimeofsys; //declaring variables for the RoundTripTime

			System.out.println("The RoundTripTime is :"); //displays the roundtrip time
			System.out.println(mostrecenttime + "-" + inittimeofsys + "=" + rt_time);
			ss4.close(); //closing the socket connection
			
		} catch (Exception exc) 
		{ 
			exc.getMessage();
		}
	}

	//http://net-informations.com/java/net/multithreaded.htm
	private String retrievedetails(Socket ss1, String sending) throws Exception 
	{
		try 
		{
			//https://www.baeldung.com/java-outputstream
			BufferedWriter bfwr = new BufferedWriter(new OutputStreamWriter(ss1.getOutputStream()));
			
			bfwr.write(sending); //writing to output
			
			//https://stackoverflow.com/questions/2340106/what-is-the-purpose-of-flush-in-java-streams
			bfwr.flush(); //output stream will be flushed
			
			//https://stackoverflow.com/questions/36859610/connecting-client-to-server-with-bufferedreader-and-dataoutputstream-using-java
			BufferedReader bfrr = new BufferedReader(new InputStreamReader(ss1.getInputStream()));
			
			String inp; 

			StringBuilder sb = new StringBuilder(); //creates a mutable string of characters
		
			for(;(inp = bfrr.readLine()) != null;)
				sb.append(inp + "\n");
			
			bfrr.close();
			
			return sb.toString();
		} 
		catch (IOException ioe) 
		{
			//https://www.tutorialspoint.com/what-are-the-differences-between-printstacktrace-method-and-getmessage-method-in-java
			ioe.getMessage();
			throw ioe;
		}
	}

	public static void main(String[] args) 
	{ 
		new Client();
		
		int port = 8080; //default port
	}
}