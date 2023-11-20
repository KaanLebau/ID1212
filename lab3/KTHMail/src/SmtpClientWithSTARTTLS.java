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
            out.println(SmtpMethod.LOGIN);
            printResponse("Username Prompt");
            out.println(Base64.getEncoder().encodeToString(kthUserName.getBytes()));
            printResponse("Password Prompt");
            out.println(Base64.getEncoder().encodeToString(kthPassword.getBytes()));
            printResponse("Authentication Status");
    }

    public void sendMail(String from, String to, String subject, String message){
            setFrom(from);
            printResponse("From Prompt");
            setTo(to);
            printResponse("To Prompt");
            setMessage(subject, message, from, to);
            printResponse("Message Prompt");
            out.println(SmtpMethod.QUIT);
            printResponse("Quit Prompt");
    }
    private void setFrom(String from){
        out.println(SmtpMethod.FROM + ": <"+ from + ">");
    }
    private void setTo(String  to){
        out.println(SmtpMethod.RCPT + ": <" + to + ">");
    }
    private void setMessage(String subject, String message, String from, String to){
        out.println(SmtpMethod.DATA);
        printResponse("DATA Prompt");
        out.println("Subject: " + subject + "\n\n" + message + "");
        out.println(".");
    }
    private void printResponse(String from) {
        System.out.println("Response from " + from + ": ");
        try {
            while((response = in.readLine()) != null && !(response.isEmpty()) && !(response.contains("Ready to start")) && !(response.contains("Ok"))){
                System.out.println(response);

                if(response.contains("Error") || response.contains("CHUNKING") || response.contains("334") || response.contains("235") || response.contains("2.0.0 Bye") || response.contains("End data with")){
                    break;
                }
            }
            if(response.contains("Ok")){
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        response = null;
    }

    public static void main(String[] args) {
        String subject = "Testsubject";
        String msg = "Testmessage!";

        SmtpClientWithSTARTTLS sendMail = new SmtpClientWithSTARTTLS();
        String mail = sendMail.username + "@kth.se";

        sendMail.logIn(sendMail.username, sendMail.password);
        sendMail.sendMail(mail, mail, subject, msg);
    }
}