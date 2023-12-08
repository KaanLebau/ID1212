package com.example.lab5.dto;

public class SelectedQuizDTO {
    Integer id;
    public SelectedQuizDTO(Integer id){
        this.id = id;
    }
    public Integer id(){
        return this.id;
    }
    public String toString(){
        return "id: "+ this.id;
    }
}
