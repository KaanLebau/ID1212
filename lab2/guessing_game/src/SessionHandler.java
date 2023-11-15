import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionHandler {
    private final ConcurrentHashMap<String, GameController> sessions = new ConcurrentHashMap<>();

    public GameController getOrCreateGameController(String sessionId) {
        // If a game model for this session ID already exists, return it
        GameController gameController = sessions.computeIfAbsent(sessionId, id -> new GameController());
        // Otherwise, create a new game model, save it, and return it
        return gameController;
    }

    public String getSessionId(String cookieHeader) {
        final String sessionCookieName = "sessionID";
        if (cookieHeader != null && !cookieHeader.isEmpty()) {
            String[] cookies = cookieHeader.split(";\\s*");
            for (String cookie : cookies) {
                if (cookie.startsWith(sessionCookieName + "=")) {
                    String sessionIdValue = cookie.substring((sessionCookieName + "=").length());
                    if (!"null".equals(sessionIdValue)) { // Check if the value is not the string "null"
                        return sessionIdValue;
                    }
                }
            }
        }
        //Create a new sessionId if it was null
        return generateSessionId(); 
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    // Create a session cookie header
    public String createSessionCookie(String sessionId) {
        int maxAgeSeconds = 3600;
        // HttpOnly and Secure flags are set for security
        return String.format("sessionID=%s; Max-Age=%d; Path=/; HttpOnly; Secure", sessionId, maxAgeSeconds);
    }
}