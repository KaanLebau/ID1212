import java.io.*;
import java.net.Socket;

public class ConnectionHandler extends ExceptionHandler implements Runnable {
    private final Socket clientSocket;
    GameController gameController;
    CookieHandler cookieHandler;

    ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.cookieHandler = new CookieHandler();
        this.gameController = new GameController(this, this.cookieHandler);
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received request: " + request);

                // Handle GET or POST request here
                if (request.startsWith("GET")) {
                    // Respond with the content of index.html
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println();
                    out.println(readFile("index.html"));
                } else if (request.startsWith("POST")) {
                    StringBuilder requestData = new StringBuilder();
                    int contentLength = 0;
                    while (!(request = in.readLine()).isEmpty()) {
                        if (request.contains("Content-Length:")) {
                            contentLength = Integer.parseInt(request.substring(request.indexOf("Content-Length:") + 16).trim());
                        }
                    }
                    for (int i = 0; i < contentLength; i++) {
                        requestData.append((char) in.read());
                    }

                    System.out.println("Received POST data: " + requestData.toString());

                    // Process the data as needed

                    // Send a response
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/plain");
                    out.println();
                    out.println("Received POST data: " + requestData.toString());
                    gameController.takeAGuess(requestData.toString());


                }
            }
        } catch (IOException e) {
           outHandler(e, "Connection Handler");
        }
    }
    public void requestPageRerender() {
        try (Socket socket = new Socket("localhost", 8088)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("rerender");
        } catch (IOException e) {
            outHandler(e, "Connection Handler");
        }
    }
    private String readFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = getClass().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream != null) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } else {
                System.err.println("File not found: " + filename);
            }
        } catch (IOException e) {
            fileHandler(e, "Connection handler");
        }
        return content.toString();
    }

}
