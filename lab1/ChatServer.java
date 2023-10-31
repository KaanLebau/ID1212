import java.awt.print.PrinterGraphics;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer{
    private final int PORT = 8088;
    private ServerSocket serverSocket;
    private ClientHandler client;
private ExceptionHandler exceptionHandler;
    public ChatServer(){
        try {
            this.serverSocket = new ServerSocket(PORT);
            exceptionHandler = new ExceptionHandler();

        }catch(IOException e){
            //TODO handle exception
        }
    }
public void initialize(){
        try {
            while(!serverSocket.isClosed()){
                client = new ClientHandler(serverSocket.accept());
            }
        }catch (Exception e){
            //TODO
        }
}



    public static void main(String[] args) throws IOException {

        ChatServer cs = new ChatServer();


        System.out.println("Hello There!");


    }
}

