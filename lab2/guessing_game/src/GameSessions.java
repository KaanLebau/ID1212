import java.util.concurrent.ConcurrentHashMap;

public class GameSessions {
    private final ConcurrentHashMap<String, GuessGameModel> sessions = new ConcurrentHashMap<>();

    public GuessGameModel getOrCreateGameModel(String sessionId) {
        // If a game model for this session ID already exists, return it
        // Otherwise, create a new game model, save it, and return it
        return sessions.computeIfAbsent(sessionId, id -> new GuessGameModel());
    }
}
