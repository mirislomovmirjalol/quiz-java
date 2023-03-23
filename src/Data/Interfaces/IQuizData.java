package Data.Interfaces;

import Models.Quiz;

import java.util.ArrayList;

public interface IQuizData {
    void readQuizzesFromFile();

    void saveQuizToFile(Quiz newQuiz);

    ArrayList<Quiz> getQuizzes();

    int getLastId();

    int getUpdatedId();

    void updateQuizzes(ArrayList<Quiz> quizzes);
    Quiz getQuizById(int id);
}
