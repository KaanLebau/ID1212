package com.example.lab5.dto;

public class QuestionsDTO{
    public String text;
    String options;
    String answers;
    public String[] optionList;
    public  String[]answerList;

    public QuestionsDTO(String text, String options, String answers) {
        this.text = text;
        this.options = options;
        this.answers = answers;
        this.optionList = getValues(options);
        this.answerList = getValues(answers);
    }

    public String[] getValues(String value){
        return value.split("/");
    }
    public String[] getFacid(){
        return this.answers.split("/");
    }

}
