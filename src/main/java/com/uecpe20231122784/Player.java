package com.uecpe20231122784;

public class Player {
    
    private int playerNumber;
    private int points;
    private int currentAnswer;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.points = 0;
        this.currentAnswer = -1;
    }

    public int chooseAnswer(String answer) {
        switch (answer) {
            case "a":
                currentAnswer = 0;
                break;
            case "b":
                currentAnswer = 1;
                break;
            case "c":
                currentAnswer = 2;
                break;
            case "d":
                currentAnswer = 3;
                break;
            case "A":
                currentAnswer = 0;
                break;
            case "B":
                currentAnswer = 1;
                break;
            case "C":
                currentAnswer = 2;
                break;
            case "D":
                currentAnswer = 3;
                break;
            default:
                currentAnswer = -1;
                break;
        }
        return currentAnswer;
    }

    public int getCurrentAnswer() {
        return currentAnswer;
    }

    public void incrementPoints() {
        points++;
    }

    public int getPoints() {
        return points;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

}
