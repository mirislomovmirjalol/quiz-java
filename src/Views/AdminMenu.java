package Views;

import Controllers.AdminController;
import Controllers.AuthController;

public class AdminMenu {
    public KeyboardReader keyboardReader = new KeyboardReader();
    public AuthController authController;
    public AdminController adminController;

    public AdminMenu(AuthController authController, AdminController adminController) {
        this.authController = authController;
        this.adminController = adminController;
    }

    public void run() {
        if (!authController.isUserAdmin()) {
            System.out.println("\n\n" + """
                    ______________________________
                     You are not an admin!
                     
                     Please login as an admin
                    ______________________________
                    """);
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
        System.out.println("Users");
    }

    public void quizzes() {
        System.out.println("Quizzes");
    }

    public void questions() {
        QuestionAdminMenu questionAdminMenu = new QuestionAdminMenu(authController, adminController);
        questionAdminMenu.run();
    }
}
