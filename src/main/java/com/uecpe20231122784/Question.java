package com.uecpe20231122784;

public class Question {
    
    private String questionText;
    private String[] possibleAnswers;
    private int correctAnswer;

    public Question(String questionText, String[] possibleAnswers, int correctAnswer) {
        this.questionText = questionText;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getPossibleAnswer(int index) {
        return possibleAnswers[index];
    }

    public int getCorrectAnswerNumber() {
        return correctAnswer;
    }

    public String getCorrectAnswer() {
        return possibleAnswers[correctAnswer];
    }

    public void setQuestion(String questionText) {
        this.questionText = questionText;
    }

    public void setPossibleAnswer(int index, String text) {
        this.possibleAnswers[index] = text;
    }

    public void setCorrectAnswerNum(int index) {
        this.correctAnswer = index;
    }
}
