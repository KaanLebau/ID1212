// Server.java
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer extends ExceptionHandler{
    private final int PORT = 8088;
    private ConnectionHandler connectionHandler;
    ServerSocket serverSocket;
    ExecutorService pool;


        public GameServer() {
            this.pool = Executors.newFixedThreadPool(10);
        }

        public void initialize() {
            try {
                serverSocket = new ServerSocket(PORT);
                System.out.println("Server is running on port " + PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    pool.execute(new ConnectionHandler(clientSocket));
                }
            } catch (IOException e) {
                initializeHandler(e, "Game server");
            }
        }

        public static void main(String[] args) {
            GameServer gs = new GameServer();

            gs.initialize();



        }
}

