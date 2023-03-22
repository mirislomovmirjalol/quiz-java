package Controllers;

import Data.Interfaces.IQuizData;
import Data.QuizData;
import Data.UserData;
import Models.Quiz;
import Models.User;
import Views.Leaderboard;

import java.util.ArrayList;

public class LeaderboardController {
    ArrayList<Quiz> quizzes;
    ArrayList<Quiz> sortedQuizzes;
    ArrayList<String> leaderboard = new ArrayList<String>();
    ArrayList<User> users;
    IQuizData quizData = new QuizData();
    UserData userData = new UserData();
    AuthController authController;

    public LeaderboardController(AuthController authController) {
        this.authController = authController;
        this.users = userData.getUsers();
        this.quizzes = quizData.getQuizzes();
        sortQuizzes();
        makeLeaderBoard();
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.showLeaderboard(this.leaderboard);
    }

    public void sortQuizzes() {
        for (int i = 0; i < quizzes.size(); i++) {
            for (int j = 0; j < quizzes.size(); j++) {
                if (quizzes.get(i).getPercentage() > quizzes.get(j).getPercentage()) {
                    Quiz temp = quizzes.get(i);
                    quizzes.set(i, quizzes.get(j));
                    quizzes.set(j, temp);
                }
            }
        }
    }

    public void makeLeaderBoard() {
        for (Quiz quiz : quizzes) {
            User user = userData.getUserById(quiz.getUserId());
            if (user != null) {
                if (user.getId() == authController.user.getId()) {
                    leaderboard.add(user.getName() + " - " + quiz.getPercentage() + "% | " + quiz.getDate() + " | Me");
                    continue;
                }
                leaderboard.add(user.getName() + " - " + quiz.getPercentage() + "% | " + quiz.getDate());
            } else {
                leaderboard.add("Anonymous - " + quiz.getPercentage() + "% | " + quiz.getDate());
            }
        }
    }
}
