import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ImapClientWithSsl {
    private final String HOST = "webmail.kth.se";
    private final int PORT = 993;
    private Socket sSLSocket;
    private BufferedReader in;
    private PrintWriter out;


    private void createSocket(){
        try {
            sSLSocket = SSLSocketFactory.getDefault().createSocket(HOST, PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void initializeIO(){
        try {
            in = new BufferedReader(new InputStreamReader(sSLSocket.getInputStream()));
            out = new PrintWriter(sSLSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void connectToServer()  {

        try {
            createSocket();
            initializeIO();
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void logIn(String kthUserName, String kthPassword){
        try {
            out.println("LOGIN " + kthUserName + " " + kthPassword);
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getInbox(){
        String response;

        while (true) {
            try {
                out.println("FETCH 1:* (BODY[HEADER.FIELDS (SUBJECT)])");
                if (!!(response = in.readLine()).equals(")")) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(response);
        }
    }
    public void logOut(){
        try {
            out.println("LOGOUT");
            closeConnection();
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void closeConnection(){
        closeIn();
        closeOut();
        closeSocket();
    }

    private void closeSocket() {
        try{
            if(sSLSocket != null & sSLSocket.isConnected())
                sSLSocket.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void closeOut(){
        if(out != null)
            out.close();
    }
    private void closeIn(){
        try {
            if(in != null)
                in.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {
        String username = "ozsan@kth.se";
        String password = "";
        ImapClientWithSsl server = new ImapClientWithSsl();
        server.connectToServer();
        server.logIn(username, password);
        server.getInbox();





    }
}
