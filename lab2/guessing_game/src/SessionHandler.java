import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionHandler {
    private final ConcurrentHashMap<String, GuessGameModel> sessions = new ConcurrentHashMap<>();

    public GuessGameModel getOrCreateGameModel(String sessionId) {
        // If a game model for this session ID already exists, return it
        // Otherwise, create a new game model, save it, and return it
        return sessions.computeIfAbsent(sessionId, id -> new GuessGameModel());
    }

            // Method to parse cookies from the request header
    // Method to parse the sessionID cookie directly from the request header
    public String getSessionId(String cookieHeader) {
        final String sessionCookieName = "sessionID";
        if (cookieHeader != null && !cookieHeader.isEmpty()) {
            String[] cookies = cookieHeader.split(";\\s*");
            for (String cookie : cookies) {
                if (cookie.startsWith(sessionCookieName + "=")) {
                    return cookie.substring((sessionCookieName + "=").length());
                }
            }
        }
        return null; // Return null if no sessionID cookie is found
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