package dev.kadan.kthForum.utilities;

import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
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
    private String username;
    private String password;
    private String response;
    private boolean registred = false;

    public SmtpClientWithSTARTTLS() {
            createSocket();
            initializeIO();
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
    public boolean logIn(String kthUserName, String kthPassword){
            out.println("AUTH LOGIN");
            printResponse("Username Prompt");
            out.println(Base64.getEncoder().encodeToString(kthUserName.getBytes()));
            printResponse("Password Prompt");
            out.println(Base64.getEncoder().encodeToString(kthPassword.getBytes()));
            printResponse("Authentication Status");

            if(this.registred){
                this.registred = false;
                return true;
            } else
                return false;
    }


    public void logout(){

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
            if(response.contains("Authentication successful") || response.contains("already authenticated")){
                this.registred = true;
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        response = null;
    }


}