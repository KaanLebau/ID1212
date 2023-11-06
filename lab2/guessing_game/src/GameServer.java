import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author Kaan Özsan
 * @author Dan Ljungström
 */
public class GameServer extends ExceptionHandler{
    private final int PORT = 8088;
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;
    
    public GameServer(){
        try {
            this.serverSocket = new ServerSocket(PORT);
        }catch(IOException e){
            //TODO: handle exception
        }
    }
    public synchronized void initialize() {
        try{
    String folder = "/";
    System.out.println("Creating Serversocket");
	ServerSocket ss = new ServerSocket(1234);
	while(true){
	    System.out.println("Waiting for client...");
	    Socket s = ss.accept();
	    System.out.println("Client connected");
	    BufferedReader request =
		new BufferedReader(new InputStreamReader(s.getInputStream()));
	    String str = request.readLine();
	    System.out.println(str);
	    StringTokenizer tokens =
		new StringTokenizer(str," ?");
	    tokens.nextToken(); // The word GET
	    String requestedDocument = tokens.nextToken();
	    while( (str = request.readLine()) != null && str.length() > 0){
		System.out.println(str);
	    }
	    System.out.println("Request processed.");
	    s.shutdownInput();
	    
	    PrintStream response =
		new PrintStream(s.getOutputStream());
	    response.println("HTTP/1.1 200 OK");
	    response.println("Server: Trash 0.1 Beta");
            
            if((requestedDocument.length()==1))
		requestedDocument = folder+"index.html";
            else requestedDocument+=folder+requestedDocument;
                
	    if(requestedDocument.indexOf(".html") != -1)
		response.println("Content-Type: text/html");
	    if(requestedDocument.indexOf(".jpg") != -1)
		response.println("Content-Type: image/jpg");
	    
	    response.println("Set-Cookie: clientId=1; expires=Wednesday,31-Dec-23 21:00:00 GMT"); //Remove date to make it a session-cookie
          
	    response.println();
            if(!"\favicon.ico".equals(requestedDocument)){ // Ignore any additional request to retrieve the bookmark-icon.
                File f = new File(""+requestedDocument);
                FileInputStream infil = new FileInputStream(f);
                byte[] b = new byte[1024];
                while( infil.available() > 0){
                    response.write(b,0,infil.read(b));
                }
                //s.shutdownOutput();
                //s.close();
            }
        }
    }catch(IOException e){
        //TODO: handle exception
    }
}

    public static void main(String[] args) throws IOException {
        GameServer gs = new GameServer();

        System.out.println("Server is running ");
        gs.initialize();
    }
}