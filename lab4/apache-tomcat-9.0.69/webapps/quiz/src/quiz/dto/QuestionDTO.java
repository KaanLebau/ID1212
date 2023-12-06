package quiz.dto;

public class QuestionDTO {
    private int id;
    private String text;
    private String[] options;
    private String answer;

    public QuestionDTO(int id, String text, String[] options, String answer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }    

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }
}
