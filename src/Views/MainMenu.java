package Views;

import Controllers.Admin.AdminController;
import Controllers.AuthController;
import Controllers.QuizController;
import Controllers.LeaderboardController;

public class MainMenu {
    KeyboardReader keyboardReader = new KeyboardReader();
    AuthController authController;
    QuizController quizController;
    AdminController adminController;
    Messenger messenger = new Messenger();


    public MainMenu(AuthController authController) {
        this.authController = authController;
        this.quizController = new QuizController(this.authController);
    }

    public void run() {
        final int ADMIN_PANEL_OPTION = 0;
        final int QUIZ_OPTION = 1;
        final int LEADERBOARD_OPTION = 2;
        final int EXIT_OPTION = 3;
        if (!authController.isUserLoggedIn()) {
            messenger.notLoggedIn();
            return;
        }
        String[] options = {"Start the quiz", "Show leaderboard", "Exit"};
        boolean exit = false;
        System.out.println("\n");

        while (!exit) {
            if (authController.isUserAdmin()) {
                System.out.println("0. Admin panel");
            }
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
            switch (choice) {
                case ADMIN_PANEL_OPTION -> adminPanel();
                case QUIZ_OPTION -> quiz();
                case LEADERBOARD_OPTION -> leaderboard();
                case EXIT_OPTION -> System.exit(0);
            }
        }
    }

    public void quiz() {
        quizController.startQuiz();
    }

    public void adminPanel() {
        if (!authController.isUserAdmin()) {
            messenger.notAdmin();
            return;
        }
        adminController = new AdminController(authController);
    }

    public void leaderboard() {
        LeaderboardController leaderboardController = new LeaderboardController(authController);
    }
}
