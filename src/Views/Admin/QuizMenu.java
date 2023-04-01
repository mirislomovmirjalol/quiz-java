package Views.Admin;

import Controllers.Admin.QuizController;
import Controllers.AuthController;
import Data.Interfaces.IUserData;
import Data.UserData;
import Models.Quiz;
import Models.User;
import Views.KeyboardReader;
import Views.Messenger;

import java.util.ArrayList;

public class QuizMenu {
    KeyboardReader keyboardReader = new KeyboardReader();
    AuthController authController;
    Messenger messenger = new Messenger();
    IUserData userData = new UserData();
    QuizController quizController;

    public QuizMenu(AuthController authController) {
        this.authController = authController;
        this.quizController = new QuizController(authController);
    }

    public void run() {
        final int SHOW_QUIZZES_OPTION = 1;
        final int DELETE_QUIZ_OPTION = 2;
        final int BACK_OPTION = 3;
        if (!authController.isUserAdmin()) {
            messenger.notAdmin();
            return;
        }
        messenger.oneLineTitle("Quizzes");
        String[] options = {"Show", "Delete", "Back"};
        boolean exit = false;


        while (!exit) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
            switch (choice) {
                case SHOW_QUIZZES_OPTION -> quizController.showQuizzes(false);
                case DELETE_QUIZ_OPTION -> delete();
                case BACK_OPTION -> exit = true;
            }
        }
    }

    public void show(ArrayList<Quiz> quizzes, boolean isAction) {
        for (Quiz quiz : quizzes) {
            User user = userData.getUserById(quiz.getUserId());
            System.out.println(quiz.getId() + ". " + user.getName() + " | " + quiz.getScore() + "/" + quiz.getMaxScore() + " | " + quiz.getPercentage() + "% | " + quiz.getDate());
        }
        if (!isAction) {
            keyboardReader.getString("Press enter to continue", true);
        }
    }

    public void delete() {
        quizController.showQuizzes(true);
        int id = keyboardReader.getInt("Please enter the id of the quiz you want to delete", 0, Integer.MAX_VALUE);
        Quiz quiz = quizController.getQuizById(id);
        if (quiz == null) {
            messenger.notFound("quiz");
            return;
        }
        quizController.deleteQuiz(quiz);
    }

}
