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

    public LeaderboardController() {
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
                leaderboard.add(user.getName() + " - " + quiz.getPercentage() + "%");
            } else {
                leaderboard.add("Anonymous - " + quiz.getPercentage() + "%");
            }
        }
    }
}
