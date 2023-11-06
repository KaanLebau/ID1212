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
public class GameServer extends ExceptionHandler {
    private final int PORT = 8088;
    private ServerSocket serverSocket;
    private ClientHandler clientHandler;

    public GameServer() {
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public synchronized void initialize() {
        try {
            String folder = System.getProperty("user.dir") + "\\guessing_game\\src\\";
            //String folder = "C:\\Users\\danlj\\OneDrive\\Dokument\\KTH\\Nätverksprogrammering\\ID1212\\lab2\\guessing_game\\src\\";
            System.out.println("Creating Serversocket");
            ServerSocket ss = new ServerSocket(1234);
            while (true) {
                System.out.println("Waiting for client...");
                Socket s = ss.accept();
                System.out.println("Client connected");

                BufferedReader request = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String str = request.readLine();
                System.out.println(str);

                StringTokenizer tokens = new StringTokenizer(str, " ?");
                tokens.nextToken(); // The word GET
                String requestedDocument = tokens.nextToken();

                PrintStream response = new PrintStream(s.getOutputStream());
                response.println("HTTP/1.1 200 OK");
                response.println("Server: Guessing Game");

                if ((requestedDocument.length() == 1))
                    requestedDocument = folder + "index.html";
                else
                    requestedDocument += folder + requestedDocument;

                if (requestedDocument.indexOf(".html") != -1)
                    response.println("Content-Type: text/html");
                if (requestedDocument.indexOf(".jpg") != -1)
                    response.println("Content-Type: image/jpg");

                response.println("Set-Cookie: clientId=1; expires=Wednesday,31-Dec-23 21:00:00 GMT");

                response.println();
                if (!"\favicon.ico".equals(requestedDocument)) { // Ignore any additional request to retrieve the bookmark-icon.
                    File f = new File("" + requestedDocument);
                    FileInputStream infil = new FileInputStream(f);
                    byte[] b = new byte[1024];
                    
                    while (infil.available() > 0) {
                        response.write(b, 0, infil.read(b));
                    }
                    // s.shutdownOutput();
                    // s.close();
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws IOException {
        GameServer gs = new GameServer();

        System.out.println("Server is running ");
        gs.initialize();
    }
}