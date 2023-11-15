import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionHandler {
    private final ConcurrentHashMap<String, GameController> sessions = new ConcurrentHashMap<>();

    public GameController getOrCreateGameController(String sessionId) {
        GameController gameController = sessions.computeIfAbsent(sessionId, id -> new GameController());
        return gameController;
    }

    public void sendHistory(String sessionId) {
        int totalControllers = 0;
        int totalGames = 0;
        int totalGuesses = 0;
        double averageGuesses = 0;

        for(GameController gameController : sessions.values()) {
            GameHistoryDTO gameHistory = gameController.getHistory();
            averageGuesses += gameHistory.getAverageGuesses();
            totalGames += gameHistory.getNumberOfGames();
            totalGuesses += gameHistory.getSumOfAttempts();
            totalControllers++;
        }

        GameController gameController = getOrCreateGameController(sessionId);
        gameController.updateServerHistory(new GameHistoryDTO(totalGames, totalGuesses, averageGuesses / totalControllers));
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