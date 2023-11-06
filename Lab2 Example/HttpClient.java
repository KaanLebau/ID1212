package examples;
import java.io.*;
import java.net.*;

public class HttpClient{
    
    public static void main(String[] args) throws Exception{
	String host = "www.apache.org";
        //host = "www.blankwebsite.com";
        //host="www.lu.se";
        host="localhost";
	int port = 1234;
	String file = "index.html";
        file = "";
	Socket s =
	    new Socket(host,port);
	
	PrintStream utdata =
	    new PrintStream(s.getOutputStream());
	utdata.println("GET /" + file + " HTTP/1.1");
        utdata.println("Host: " + host);
	utdata.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0");
        utdata.println();
	//s.shutdownOutput();
	
	BufferedReader indata =
	    new BufferedReader(new InputStreamReader(s.getInputStream()));
	String str;
	while( (str = indata.readLine()) != null){
	    System.out.println(str);
	}
	s.close();
    }
}