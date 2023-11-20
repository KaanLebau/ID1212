import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Properties;

public class SmtpClientWithSTARTTLS {
    private static final String CONFIG_FILE = "resources/config.properties";
    private static final String HOST = "smtp.kth.se";
    private static final int PORT = Integer.parseInt("587");
    private BufferedReader in;
    private PrintWriter out;
    private Socket sslSocket;
    String username;
    String password;
    private String response;
    private String from;
    private String to;
    private String message;

    public SmtpClientWithSTARTTLS() {
            createSocket();
            initializeIO();
            loadConfig();
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
    public void createSocket(){
        try {
            sslSocket = new Socket(HOST,PORT);
            initializeIO();
            startTLS();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void startTLS() throws IOException{
        out.println("EHLO " + HOST);
        printResponse("EHLO before STARTTLS");

        out.println("STARTTLS");
        printResponse("startTLS");

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        sslSocket = (SSLSocket) factory.createSocket(sslSocket, HOST, PORT, true);

        initializeIO();

        out.println("EHLO " + HOST);
        printResponse("EHLO after STARTTLS");
    }

    public void initializeIO(){
        try {
            in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            out = new PrintWriter(sslSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void logIn(String kthUserName, String kthPassword){
        try {
            out.println(SmtpMethod.LOGIN);
            out.println(Base64.getEncoder().encodeToString(kthUserName.getBytes()));
            out.println(Base64.getEncoder().encodeToString(kthPassword.getBytes()));
            printResponse("login");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            System.out.println("Login() is done!");
        }
    }

    public void sendMail(String from, String to, String message){
        try {
            setFrom(from);
            setTo(to);
            setMessage(message);
            printResponse("sendMail");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void setFrom(String from){
        out.println(SmtpMethod.FROM + ": <"+ from + ">");
    }
    private void setTo(String  to){
        out.println(SmtpMethod.RCPT + ": <" + to + ">");
    }
    private void setMessage(String message){
        out.println(SmtpMethod.DATA);
        out.println(message);
        out.println(SmtpMethod.QUIT);
    }
    private void printResponse(String from) throws IOException {
        System.out.println("Response from " + from + ": ");
        try {
            while((response = in.readLine()) != null && !response.isEmpty() && !response.contains("Ready to start")){
                System.out.println(response);

                if(response.contains("Error") || response.contains("CHUNKING")){
                    break;
                }
            }
            if(response.contains("Ready to start")){
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        response = null;
    }

    public static void main(String[] args) {
        String from = "From user1";
        String to = "To user2";
        String msg = "Hej hej!";

        SmtpClientWithSTARTTLS sendMail = new SmtpClientWithSTARTTLS();
        sendMail.logIn(sendMail.username, sendMail.password);
        //sendMail.sendMail(from, to ,msg);
    }
}
