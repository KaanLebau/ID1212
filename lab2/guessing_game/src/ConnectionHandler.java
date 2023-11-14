import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectionHandler extends ExceptionHandler implements Runnable {
    private final Socket clientSocket;
    GameController gameController;
    SessionHandler sessionHandler;

    ConnectionHandler(Socket clientSocket, SessionHandler sessionHandler) {
        this.clientSocket = clientSocket;
        this.sessionHandler = sessionHandler;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.contains("favicon")) {
                return;
            }

            // Extract the method from the request line
            String method = requestLine.split(" ")[0];
            Map<String, String> headers = readHeaders(in);

            // Extract the Cookie header
            String cookiesHeader = headers.get("Cookie");
            String sessionId = sessionHandler.getSessionId(cookiesHeader);

            // If no session ID is found, generate a new one
            if (sessionId == null || sessionId.trim().isEmpty()) {
                sessionId = sessionHandler.generateSessionId();
            }

            switch (method) {
                case "GET":
                    receivedGetRequest(out, requestLine, sessionId);
                    break;
                case "POST":
                    receivedPostRequest(out, in, headers, sessionId);
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

    private Map<String, String> readHeaders(BufferedReader in) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line;
        while (!(line = in.readLine()).isEmpty()) {
            int colonIndex = line.indexOf(":");
            if (colonIndex != -1) {
                String headerName = line.substring(0, colonIndex).trim();
                String headerValue = line.substring(colonIndex + 1).trim();
                headers.put(headerName, headerValue);
            }
        }
        return headers;
    }

    private void receivedPostRequest(PrintWriter out, BufferedReader in, Map<String, String> headers, String sessionId)
            throws IOException {
        int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
        StringBuilder requestBody = new StringBuilder();
        String reply;

        if (contentLength > 0) {
            char[] bodyChars = new char[contentLength];
            in.read(bodyChars);
            requestBody.append(new String(bodyChars));
        }

        GameController gameController = sessionHandler.getOrCreateGameController(sessionId);
        reply = gameController.takeAGuess(requestBody.toString());
        sendResponse(out, reply, sessionId);
    }

    private void sendResponse(PrintWriter out, String reply, String sessionId) {;
        String cookieHeader = sessionHandler.createSessionCookie(sessionId);

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/plain");
        out.println("Set-Cookie: " + cookieHeader);
        out.println();
        out.println(reply);
    }

    private void receivedGetRequest(PrintWriter out, String request, String sessionId ) {
        if(request.contains("new-game")) {
            GameController gameController = sessionHandler.getOrCreateGameController(sessionId);
            gameController.newGame();
        }
        String cookieHeader = sessionHandler.createSessionCookie(sessionId);
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
