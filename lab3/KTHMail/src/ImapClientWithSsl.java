import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStream;
import java.util.Properties;



public class ImapClientWithSsl {
    private static final String CONFIG_FILE = "resources/config.properties";
    private static final String HOST = "webmail.kth.se";
    private static final int PORT = 993;

    private String username;
    private String password;
    private String response;
    private Socket sSLSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ImapClientWithSsl()  {
        try {
            createSocket();
            initializeIO();
            loadConfig();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void loadConfig() {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
            }
            Properties prop = new Properties();
            prop.load(input);

            username = prop.getProperty("username");
            password = prop.getProperty("password");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void createSocket(){
        try {
            sSLSocket = SSLSocketFactory.getDefault().createSocket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void initializeIO(){
        try {
            in = new BufferedReader(new InputStreamReader(sSLSocket.getInputStream()));
            out = new PrintWriter(sSLSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void logIn(String kthUserName, String kthPassword){
            out.println("A1 " + ImapMethod.LOGIN + " " + kthUserName + " " + kthPassword);
            printResponse("login");
    }

    public void getInbox(){
                out.println("S1 SELECT INBOX");
                printResponse("inbox");

                out.println("G1 " + ImapMethod.FETCH + " 1:* BODY.PEEK[HEADER.FIELDS (SUBJECT)]");
                printResponse("fetch");

                out.println("G2 " + ImapMethod.FETCH + " 2 body[text]");
                printResponse("fetch message");
    }
    public void logOut(){
            out.println("L1 " +ImapMethod.LOGOUT);
            printResponse("logout");

            closeConnection();
    }

    public void printResponse(String from){
        System.out.println("Response from " + from + ": ");
        try {
            while((response = in.readLine()) != null && !response.contains("completed")){
                if(!response.contains("BODY[HEADER.FIELDS (SUBJECT)]") && !response.equals(")") && !response.equals(""))
                    System.out.println(response);

                if(response.contains("Error")){
                    break;
                }
            }
            if(response.contains("completed")){
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            if(sSLSocket != null){
                System.out.println("Closing socket");
                sSLSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void closeOut(){
        if(out != null){
            System.out.println("Closing PrintWriter");
            out.close();
        }
    }
    private void closeIn(){
        try {
            if(in != null){
                System.out.println("Closing BufferedReader");
                in.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        ImapClientWithSsl server = new ImapClientWithSsl();
        server.logIn(server.username, server.password);
        server.getInbox();
        server.logOut();
    }

}
