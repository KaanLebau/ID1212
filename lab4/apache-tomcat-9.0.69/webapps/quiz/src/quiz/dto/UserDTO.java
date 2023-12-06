package quiz.dto;

public class UserDTO {
    private int id;
    private String username;

    public UserDTO(int id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}