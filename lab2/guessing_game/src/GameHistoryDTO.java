/**
 * This class is used to send data between different java classes
 * @author Kaan Özsan
 * @author Dan Ljungström
 */
public class GameHistoryDTO {


    private int numberOfGames = 1;
    private int sumOfAttempts;
    private double averageGuesses;

    public GameHistoryDTO(int numberOfGames, int sumOfAttempts, double averageGuesses){
        this.averageGuesses = averageGuesses;
        this.numberOfGames = numberOfGames;
        this.sumOfAttempts = sumOfAttempts;
    }
    public int getNumberOfGames() {
        return numberOfGames;
    }

    public int getSumOfAttempts() {
        return sumOfAttempts;
    }

    public double getAverageGuesses() {
        return averageGuesses;
    }

}
