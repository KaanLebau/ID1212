import java.io.IOException;
import java.net.ServerSocket;

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
    public synchronized void initialize(){
        try {
            while(!serverSocket.isClosed()){
                clientHandler = new ClientHandler(serverSocket.accept());
                Thread ch = new Thread(clientHandler);
                ch.start();
            }
        }catch (Exception e){
            //TODO: handle exception
        }
}

    public static void main(String[] args) throws IOException {
        GameServer gs = new GameServer();

        System.out.println("Server is running ");
        gs.initialize();
    }
}