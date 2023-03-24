package Controllers.Admin;

import Controllers.AuthController;
import Data.Interfaces.IQuizData;
import Data.QuizData;
import Models.Quiz;
import Views.Admin.QuizMenu;

import java.util.ArrayList;

public class QuizController {
    AuthController authController;
    ArrayList<Quiz> quizzes = new ArrayList<>();
    IQuizData quizData = new QuizData();

    public QuizController(AuthController authController) {
        this.authController = authController;
    }

    public void showQuizzes(boolean isAction) {
        quizzes = quizData.getQuizzes();
        QuizMenu quizMenu = new QuizMenu(authController);
        quizMenu.show(quizzes, isAction);
    }

    public void deleteQuiz(Quiz quizToDelete) {
        quizzes.remove(quizToDelete);
        quizData.updateQuizzes(quizzes);
    }

    public Quiz getQuizById(int id) {
        for (Quiz quiz : quizData.getQuizzes()) {
            if (quiz.getId() == id) {
                return quiz;
            }
        }
        return null;
    }
}
