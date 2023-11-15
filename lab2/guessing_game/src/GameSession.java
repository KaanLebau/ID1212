public class GameSession {
    private GameController gameController;
    private int numberOfGames;
    private int numberOfGuesses;

    // Constructor
    public GameSession(GameController gameController) {
        this.gameController = gameController;
        this.numberOfGames = 0;
        this.numberOfGuesses = 0;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void incrementNumberOfGames() {
        this.numberOfGames++;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void updateGuesses(int guesses) {
        this.numberOfGuesses += guesses;
    }
}
