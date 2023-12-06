package quiz.dto;

public class ResultDTO {
    private int id;
    private int userId;
    private int quizId;
    private int score;

    public ResultDTO(int id, int userId, int quizId, int score) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}