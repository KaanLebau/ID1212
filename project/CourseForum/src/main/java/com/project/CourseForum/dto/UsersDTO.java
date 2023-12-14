package com.project.CourseForum.dto;

import java.util.Objects;

public class UsersDTO {
    private  Integer id;
    private  String username;


    public UsersDTO(Integer id, String username) {
        this.id = id;
        this.username = username;

    }

    public Integer id() {
        return id;
    }
    public String username() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UsersDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "UsersDTO[" +
                "id=" + id + ", " +
                "username=" + username + ']';
    }

}
