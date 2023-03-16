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
                case 1 -> users();
                case 2 -> quizzes();
                case 3 -> questions();
                case 4 -> exit = true;
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
