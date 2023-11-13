import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ConnectionHandler extends ExceptionHandler implements Runnable {
    private final Socket clientSocket;
    GameController gameController;
    CookieHandler cookieHandler;
    GameSessions sessions;

    ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.cookieHandler = new CookieHandler();
        this.sessions = new GameSessions();
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String requestLine = in.readLine();
                String headerLine;
                String cookiesHeader = "";
                if (requestLine == null || requestLine.contains("favicon")) {
                    return;
                }
    
                // Read the rest of the HTTP header and look for cookies
                while (!(headerLine = in.readLine()).isEmpty()) {
                    if (headerLine.toLowerCase().startsWith("cookie:")) {
                        cookiesHeader = headerLine.substring("Cookie:".length()).trim();
                        break; // Assuming cookies are all in one line, otherwise keep reading headers
                    }
                }
    
                Map<String, String> cookies = cookieHandler.parseCookies(cookiesHeader);
                String sessionId = cookieHandler.getCookieValue(cookies, "sessionID");
                if (sessionId.isEmpty()) {
                    sessionId = cookieHandler.generateSessionId();
                }
        
                // Extract the method from the request line
                String method = requestLine.split(" ")[0];
        
                switch (method) {
                    case "GET":
                        receivedGetRequest(out, requestLine, sessionId);
                        break;
                    case "POST":
                    System.out.println("POST:" + requestLine);
                        receivedPostRequest(out, in, requestLine, sessionId);
                        break;
                    // Add additional cases for other HTTP methods if needed
                    default:
                        // Handle unsupported methods or provide a default case
                        out.println("HTTP/1.1 501 Not Implemented");
                        out.println();
                        out.flush();
                        break;
                }
        } catch (IOException e) {
           outHandler(e, "Connection Handler");
        }
    }

    private void receivedPostRequest(PrintWriter out, BufferedReader in, String request, String sessionId) throws IOException {
        StringBuilder requestData = new StringBuilder();
        int contentLength = 0;
        
        while (!(request = in.readLine()).isEmpty()) {
            if (request.contains("Content-Length:")) {
                contentLength = Integer.parseInt(request.substring(request.indexOf("Content-Length:") + 16).trim());
            }
        }
        if(contentLength > 0) {
            char[] bodyChars = new char[contentLength];
            in.read(bodyChars);
            requestData.append(new String(bodyChars));
        }

        // Retrieve or create the GuessGameModel instance for this session
        GuessGameModel gameModel = sessions.getOrCreateGameModel(sessionId);
        gameController = new GameController(gameModel);

        // Process the guess with the retrieved or new game model
        gameController.takeAGuess(requestData.toString());
        sendResponse(out, requestData.toString(), sessionId);
    }

    private void sendResponse(PrintWriter out, String requestData, String sessionId){
        // Set the cookie in the response
        String cookieHeader = cookieHandler.createSessionCookie(sessionId, 3600);

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/plain");
        out.println("Set-Cookie: " + cookieHeader);
        out.println();
        out.println("Received POST data: " + requestData);
        if(gameController == null){
            // Retrieve or create the GuessGameModel instance for this session
            GuessGameModel gameModel = sessions.getOrCreateGameModel(sessionId);
            gameController = new GameController(gameModel);
        }
        gameController.takeAGuess(requestData);
    }

    private void receivedGetRequest(PrintWriter out, String request, String sessionId){
        System.out.println(request);
        String cookieHeader = cookieHandler.createSessionCookie(sessionId, 3600); // 1 hour for session expiry
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("Set-Cookie: " + cookieHeader);
        out.println();
        out.println(readFile("index.html"));
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
