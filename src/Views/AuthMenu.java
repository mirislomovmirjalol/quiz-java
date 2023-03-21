package Views;

import Controllers.AuthController;

public class AuthMenu {

    AuthController authController = new AuthController();
    KeyboardReader keyboardReader = new KeyboardReader();

    Messenger messenger = new Messenger();

    public void run() {
        final int LOGIN_OPTION = 1;
        final int REGISTER_OPTION = 2;
        final int EXIT_OPTION = 3;
        boolean exit = false;
        String[] options = {"Login", "Register", "Exit"};
        while (!exit) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 1, options.length);
            switch (choice) {
                case LOGIN_OPTION -> login();
                case REGISTER_OPTION -> register();
                case EXIT_OPTION -> System.exit(0);
            }
        }
    }

    public void login() {
        messenger.oneLineTitle("Login");
        String username = keyboardReader.getString("Please enter your username", false);
        String password = keyboardReader.getString("Please enter your password", false);

        authController.login(username, password);
        authController.isUserLoggedIn();

        if (authController.isUserLoggedIn()) {
            messenger.loginSuccessfully(authController.user.getName());
            MainMenu mainMenu = new MainMenu(authController);
            mainMenu.run();
        } else {
            messenger.loginFailed();
            run();
        }
    }

    public void register() {
        messenger.oneLineTitle("Register");
        String name = keyboardReader.getString("Please enter your name", false);
        String username = keyboardReader.getString("Please enter your username", false);
        String password = keyboardReader.getString("Please enter your password", false);
        boolean registrationSuccessful = authController.register(name, username, password);
        if (!registrationSuccessful) {
            messenger.oneLineTitle("Username is already taken!");
            run();
        }
        if (authController.isUserLoggedIn()) {
            messenger.registerSuccessfully(authController.user.getName());
            MainMenu mainMenu = new MainMenu(authController);
            mainMenu.run();
        } else {
            messenger.registerFailed();
            run();
        }
    }
}
