package com.example.lab5.dto;

public record QuestionsDTO(
        String text,
        String options,
        String answers
) {
    public String[] getOptions(){
        return this.options.split("/");
    }
    public String[] getFacid(){
        return this.answers.split("/");
    }

}
