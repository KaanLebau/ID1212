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
        try {
            out.println("A1 " + ImapMethod.LOGIN + " " + kthUserName + " " + kthPassword);
            response = in.readLine();
            if(response != null){
                System.out.println("Server response: \n" + response);
            } else {
                System.out.println("Server response is NULL");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            System.out.println("Login() is done!");
        }
    }

    public void getInbox(){

        while (true) {
            try {
                out.println("S1 SELECT INBOX");
                out.println(ImapMethod.FETCH + " 1:* (BODY[HEADER.FIELDS (SUBJECT)])");
                if (!(response = in.readLine()).equals(")"))
                    break;
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        System.out.println("getInbox() function is done!");

    }
    public void logOut(){
        try {
            out.println(ImapMethod.LOGOUT);
            response = in.readLine();
            System.out.println(response);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            System.out.println("logOut() is done!");
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
