package Views.Admin;

import Controllers.Admin.AdminController;
import Controllers.Admin.UserController;
import Controllers.AuthController;
import Views.KeyboardReader;
import Views.Messenger;

public class AdminMenu {
    public KeyboardReader keyboardReader = new KeyboardReader();
    public AuthController authController;
    public AdminController adminController;
    public Messenger messenger = new Messenger();

    public AdminMenu(AuthController authController, AdminController adminController) {
        this.authController = authController;
        this.adminController = adminController;
    }

    public void run() {
        final int USERS_OPTION = 1;
        final int QUIZZES_OPTION = 2;
        final int QUESTIONS_OPTION = 3;
        final int BACK_OPTION = 4;
        messenger.oneLineTitle("Admin panel");
        if (!authController.isUserAdmin()) {
            messenger.notAdmin();
            return;
        }
        String[] options = {"Users", "Quizzes", "Questions", "Back"};
        boolean exit = false;
        while (!exit) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
            switch (choice) {
                case USERS_OPTION -> users();
                case QUIZZES_OPTION -> quizzes();
                case QUESTIONS_OPTION -> questions();
                case BACK_OPTION -> exit = true;
            }
        }
    }

    public void users() {
        UserMenu userMenu = new UserMenu(authController);
        userMenu.run();
    }

    public void quizzes() {
        System.out.println("Quizzes");
    }

    public void questions() {
        QuestionMenu questionMenu = new QuestionMenu(authController, adminController);
        questionMenu.run();
    }
}
