package com.uecpe20231122784;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import com.uecpe20231122784.lib.input;
import com.uecpe20231122784.lib.print;

public class Main {
    public static void main(String[] args) throws Exception {

        // Check if db.xml exists
        File game_db = new File("db.xml");
        if (!game_db.exists()) {
            print.ln("Game database db.xml not found. Please add db.xml containing trivia questions to the root directory.");
            System.exit(0);
        }

        // Read the game database and prepare variables
        File trivia_file = new File("db.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document trivia = db.parse(trivia_file);

        // Fetch the number of questions per player and the total number of questions in the database
        int QUESTION_PER_PLAYER = Integer.parseInt(trivia.getDocumentElement().getAttribute("questionsPerPlayer"));
        int MAX_DB_QUESTIONS = trivia.getElementsByTagName("question").getLength();

        // Check if there are enough questions in the database
        if (QUESTION_PER_PLAYER * 2 > MAX_DB_QUESTIONS) {
            print.ln("Not enough questions in database. Please add more questions to db.xml.");
            System.exit(0);
        }

        // Instantiate questions and question ids
        Question[] questions = new Question[QUESTION_PER_PLAYER * 2];
        int[] question_ids = new int[QUESTION_PER_PLAYER * 2];
        // Instantiate players
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        // Prepare arrays to store player answers (for debugging and checking)
        int[] player1_answer_array = new int[QUESTION_PER_PLAYER];
        int[] player2_answer_array = new int[QUESTION_PER_PLAYER];
        // Set scores to 0
        int player1_score = 0;
        int player2_score = 0;
        boolean isQuestionUnique = false;

        // Fetch unique questions for players
        // For loop to get 10 unique questions from xml db and assign them to the Question class arrays
        for (int i = 0; i < QUESTION_PER_PLAYER * 2; i++) {
            // A loop to generate random number from 1 to the number of questions in the database to generate random questions
            do {
                isQuestionUnique = false;
                int randqid = (int) (1 + Math.random() * MAX_DB_QUESTIONS);
                for (int j : question_ids) {
                    if (j != randqid) {
                        // If the random number is unique, then the question is unique
                        isQuestionUnique = true;
                    }
                    else {
                        // Until a unique question is found, keep generating random numbers
                        isQuestionUnique = false;
                        break;
                    }
                }
                if (isQuestionUnique) {
                    // If a unique question is found, assign the random number to the question id array
                    question_ids[i] = randqid;
                }
            } while (!isQuestionUnique);

            // Fill Question classes in array
            questions[i] = new Question(
                trivia.getElementsByTagName("question").item(question_ids[i] - 1).getTextContent(),
                new String[] {
                    trivia.getElementsByTagName("option0").item(question_ids[i] - 1).getTextContent(),
                    trivia.getElementsByTagName("option1").item(question_ids[i] - 1).getTextContent(),
                    trivia.getElementsByTagName("option2").item(question_ids[i] - 1).getTextContent(),
                    trivia.getElementsByTagName("option3").item(question_ids[i] - 1).getTextContent()
                },
                Integer.parseInt(trivia.getElementsByTagName("answer").item(question_ids[i] - 1).getTextContent())
            );
        }

        // Print welcome message and game instructions
        print.ln("Welcome to the Trivia Game!");
        print.ln("This is a two player game. Each player gets to answer " + QUESTION_PER_PLAYER + " questions each." + '\n' +
                 "Questions are randomly picked from a database and each player gets to answer in turns." + '\n' +
                 "Each answer is labled by letters A, B, C and D. Type the correct answer when prompted," + '\n' +
                 "and it will be recorded regardless if you typed a lowercase or uppercase answer once you hit enter." + '\n' +
                 "Typing any other letters, words, or phrase will invalidate your answer." + '\n' +
                 "Scores will be counted at the end of the game. Good luck and have fun!" + '\n');

        // Start the game
        for (int q = 0; q < QUESTION_PER_PLAYER * 2; q++) {
            // First and odd questions are for player 1
            if (q % 2 == 0) {
                print.ln("Player 1's turn:");
                // Print the question
                print.ln((q + 1) + ") " + questions[q].getQuestionText());
                // Print the possible answers
                for (int choice = 0; choice < 4; choice++) {
                    char choice_letter = 'x';
                    switch (choice) {
                        case 0:
                            choice_letter = 'a';
                            break;
                        case 1:
                            choice_letter = 'b';
                            break;
                        case 2:
                            choice_letter = 'c';
                            break;
                        case 3:
                            choice_letter = 'd';
                            break;
                        default:
                            break;
                    }
                    print.ln(" " + choice_letter + ". " + questions[q].getPossibleAnswer(choice));
                }
                // Ask for the player's answer
                print.s("Your answer: ");
                player1_answer_array[q / 2] = player1.chooseAnswer((String) input.wait("string"));
                // Check if the player's answer is correct
                if (player1.getCurrentAnswer() == questions[q].getCorrectAnswerNumber()) {
                    player1_score++;
                }
            }
            // Second and even questions are for player 2
            else {
                print.ln("Player 2's turn:");
                print.ln((q + 1) + ") " + questions[q].getQuestionText());
                for (int choice = 0; choice < 4; choice++) {
                    char choice_letter = 'x';
                    switch (choice) {
                        case 0:
                            choice_letter = 'a';
                            break;
                        case 1:
                            choice_letter = 'b';
                            break;
                        case 2:
                            choice_letter = 'c';
                            break;
                        case 3:
                            choice_letter = 'd';
                            break;
                        default:
                            break;
                    }
                    print.ln(" " + choice_letter + ". " + questions[q].getPossibleAnswer(choice));
                }
                print.s("Your answer: ");
                player2_answer_array[(int) q / 2] = player2.chooseAnswer((String) input.wait("string"));
                if (player2.getCurrentAnswer() == questions[q].getCorrectAnswerNumber()) {
                    player2_score++;
                }
            }
            print.ln("====================================");
        }

        // Print the final scores and the winner
        print.ln("");
        if (player1_score > player2_score) {
            print.ln("Player 1 wins!");
        }
        else if (player1_score < player2_score) {
            print.ln("Player 2 wins!");
        }
        else {
            print.ln("It's a tie!");
        }
        print.ln("Player 1's score: " + player1_score);
        print.ln("Player 2's score: " + player2_score);

    }
}