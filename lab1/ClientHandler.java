import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * <p>
 * This class handles clients that have connected to the chat server.
 * </p>
 *
 * <p>
 * The class functionality is as follows
 * </p>
 *
 * <ul>
 * <li>Receive messages from a client
 * <li>Broadcast incoming message to all clients including the server.
 * <li>Server related commands from clients
 * <li>Close a client's connection to the server.
 * <li>Remove a client from the client list
 * </ul>
 *
 *  @author Kaan Özsan
 *  @author Dan Ljungström
 */
public class ClientHandler extends ExceptionHandler implements Runnable {

    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatServer server;
    private ExceptionHandler exceptionhandler;
    private String message;
    private String username;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.username = in.readLine();
            clients.add(this);

            System.out.println("New user: " + this.username + ". Number of users: " + clients.size());
            broadcast(this.username + " has joined the chat.");

        } catch (IOException e) {
            creationHandler(e, "Client handler");
        }
    }


    @Override
    public void run() {
        String message;

        while (!socket.isClosed()) {
            try {
                message = in.readLine();
                broadcast(message);
            } catch (IOException e) {
                inHandler(e,"Client handler");
                closeConnection(this);
                break;
            }
        }
    }

    public synchronized void broadcast(String msg) {
        if (messageCheck(msg)) {
            System.out.println("Broadcasting: " + msg);
            for (ClientHandler client : clients) {
                try {
                    if (client.username != this.username) {
                        client.out.println(msg);
                    }
                } catch (Exception e) {
                    outHandler(e,"Client handler");
                    closeConnection(this);
                }
            }
        }
    }

    public boolean messageCheck(String msg) {
        if (msg.startsWith(this.username + ": /quit")) {
            closeConnection(this);
            return false;
        } else {
            return true;
        }
    }

    private synchronized void closeConnection(ClientHandler client) {
        try {
            closeReader(client.in);
            closeWriter(client.out);
            closeSocket(client.socket);
            removeClient(client);
        } catch (IOException e) {
            closingHandler(e,"Client handler");
        }

    }

    private void closeSocket(Socket client) throws IOException {
        if (client != null) {
            client.close();
        }

    }

    private void closeReader(BufferedReader in) throws IOException {
        if (in != null) {
            in.close();
        }
    }

    private void closeWriter(PrintWriter out) {
        if (out != null) {
            out.close();
        }
    }

    private void removeClient(ClientHandler client) {
        broadcast(client.username + " disconnected.");
        clients.remove(client);
    }

}
