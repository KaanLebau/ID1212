import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

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
            this.exceptionhandler = new ExceptionHandler();
            this.username = in.readLine();
            clients.add(this);

            System.out.println("New user: " + this.username + ". Number of users: " + clients.size());
            broadcast(this.username + " has joined the chat.");

        } catch (IOException e) {
            exceptionhandler.chCreationHandler(e.toString());
        }
    }

    public Socket getClientSocket() {
        return socket;
    }

    @Override
    public void run() {
        String message;

        while (!socket.isClosed()) {
            try {
                message = in.readLine();
                broadcast(message);
            } catch (IOException e) {
                exceptionhandler.clientInHandler(e.toString());
                closeConnection(this);
                break;
            }
        }
    }

    public void broadcast(String msg) {
        if (messageCheck(msg)) {
            System.out.println("Broadcasting: " + msg);
            for (ClientHandler client : clients) {
                try {
                    if (client.username != this.username) {
                        client.out.println(msg);
                    }
                } catch (Exception e) {
                    exceptionhandler.clientOutHandler(e.toString());
                    closeConnection(this);
                }
            }
        }
    }

    public boolean messageCheck(String msg) {
        if (msg.startsWith(this.username + ": /quit")) {
            this.out.println("**QUIT**");
            closeConnection(this);
            return false;
        } else {
            return true;
        }
    }

    private void closeConnection(ClientHandler client) {
        try {
            closeReader(client.in);
            closeWriter(client.out);
            closeSocket(client.socket);
            removeClient(client);
        } catch (IOException e) {
            System.out.println("shit");
            exceptionhandler.clientClosingHandler(e.toString());
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
