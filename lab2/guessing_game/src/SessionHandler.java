import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionHandler {
    private final ConcurrentHashMap<String, GameController> sessions = new ConcurrentHashMap<>();

    public GameController getOrCreateGameController(String sessionId) {
        GameController gameController = sessions.computeIfAbsent(sessionId, id -> new GameController());
        return gameController;
    }

    public String getSessionId(String cookieHeader) {
        final String sessionCookieName = "sessionID";
        if (cookieHeader != null && !cookieHeader.isEmpty()) {
            String[] cookies = cookieHeader.split(";\\s*");
            for (String cookie : cookies) {
                if (cookie.startsWith(sessionCookieName + "=")) {
                    String sessionIdValue = cookie.substring((sessionCookieName + "=").length());
                    if (!"null".equals(sessionIdValue)) { 
                        return sessionIdValue;
                    }
                }
            }
        }
        return generateSessionId(); 
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    public String createSessionCookie(String sessionId) {
        int maxAgeSeconds = 3600;
        return String.format("sessionID=%s; Max-Age=%d; Path=/; HttpOnly; Secure", sessionId, maxAgeSeconds);
    }
}