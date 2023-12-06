package quiz.dto;

public class QuizDTO {
    private int id;
    private String subject;

    public QuizDTO(int id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}