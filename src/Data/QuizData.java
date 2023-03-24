package Data;

import Data.Interfaces.IQuizData;
import Models.Quiz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizData implements IQuizData {
    String quizzesPath = "src/Data/Database/Quizzes.csv";

    private ArrayList<Quiz> quizzes = new ArrayList<Quiz>();

    private int lastId = 0;

    public QuizData() {
        readQuizzesFromFile();
    }

    @Override
    public void readQuizzesFromFile() {
        try {
            quizzes.clear();
            File file = new File(quizzesPath);
            file.createNewFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                lastId = id;
                int userId = Integer.parseInt(data[1]);
                int score = Integer.parseInt(data[2]);
                int maxScore = Integer.parseInt(data[3]);
                double percentage = Double.parseDouble(data[4]);
                String date = data[5];
                Quiz quiz = new Quiz(id);
                quiz.setUserId(userId);
                quiz.setScore(score);
                quiz.setMaxScore(maxScore);
                quiz.setPercentage(percentage);
                quiz.setDate(date);
                quizzes.add(quiz);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void saveQuizToFile(Quiz newQuiz) {
        readQuizzesFromFile();
        try {
            FileWriter writer = new FileWriter(quizzesPath);
            for (Quiz quiz : quizzes) {
                writer.write(quiz.getId() + "," + quiz.getUserId() + "," + quiz.getScore() + "," + quiz.getMaxScore() + "," + quiz.getPercentage() + "," + quiz.getDate() + "\n");
            }
            writer.write(newQuiz.getId() + "," + newQuiz.getUserId() + "," + newQuiz.getScore() + "," + newQuiz.getMaxScore() + "," + newQuiz.getPercentage() + "," + newQuiz.getDate() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuizzes(ArrayList<Quiz> quizzes) {
        try {
            FileWriter writer = new FileWriter(quizzesPath);
            for (Quiz quiz : quizzes) {
                writer.write(quiz.getId() + "," + quiz.getUserId() + "," + quiz.getScore() + "," + quiz.getMaxScore() + "," + quiz.getPercentage() + "," + quiz.getDate() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    @Override
    public int getLastId() {
        return lastId;
    }

    @Override
    public int getUpdatedId() {
        readQuizzesFromFile();
        return lastId + 1;
    }

    @Override
    public Quiz getQuizById(int id) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == id) {
                return quiz;
            }
        }
        return null;
    }
}
