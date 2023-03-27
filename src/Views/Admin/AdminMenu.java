package Views.Admin;

import Controllers.Admin.AdminController;
import Controllers.Admin.CategoriesController;
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
        final int CATEGORIES_OPTION = 4;
        final int BACK_OPTION = 5;
        messenger.oneLineTitle("Admin panel");
        if (!authController.isUserAdmin()) {
            messenger.notAdmin();
            return;
        }
        String[] options = {"Users", "Quizzes", "Questions", "Categories", "Back"};
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
                case CATEGORIES_OPTION -> categories();
                case BACK_OPTION -> exit = true;
            }
        }
    }

    public void users() {
        UserMenu userMenu = new UserMenu(authController);
        userMenu.run();
    }

    public void quizzes() {
        QuizMenu quizMenu = new QuizMenu(authController);
        quizMenu.run();
    }

    public void questions() {
        QuestionMenu questionMenu = new QuestionMenu(authController);
        questionMenu.run();
    }

    public void categories() {
        CategoriesMenu categoriesMenu = new CategoriesMenu(authController);
        categoriesMenu.run();
    }
}
