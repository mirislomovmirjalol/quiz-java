package Views;

import Controllers.AuthController;

public class AuthMenu {

    public AuthController authController = new AuthController();
    public KeyboardReader keyboardReader = new KeyboardReader();

    public void run() {
        String[] options = {"Login", "Register"};
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        int choice = keyboardReader.getInt("\n\nPlease choose an option", 1, options.length);
        switch (choice) {
            case 1 -> login();
            case 2 -> register();
        }
    }

    public void login() {
        String username = keyboardReader.getString("Please enter your username");
        String password = keyboardReader.getString("Please enter your password");

        authController.login(username, password);
        authController.isUserLoggedIn();

        if (authController.isUserLoggedIn()) {
            System.out.println("\n\n" +
                    """
                            ______________________________
                             User logged in successfully
                             
                             Hello\t""" + authController.user.getName() +
                    """
                            \n______________________________
                            """
            );
            MainMenu mainMenu = new MainMenu(authController);
            mainMenu.run();
        } else {
            System.out.println("\n\n" +
                    """
                            ______________________________
                             User login failed
                             
                             Please check your credentials
                            ______________________________
                            """
            );
            run();
        }
    }

    public void register() {
        String name = keyboardReader.getString("Please enter your name");
        String username = keyboardReader.getString("Please enter your username");
        String password = keyboardReader.getString("Please enter your password");
        authController.register(name, username, password);
        if (authController.isUserLoggedIn()) {
            System.out.println("\n\n" +
                    """
                            ______________________________
                             User registered successfully
                             
                             """ + " Hello\t" + authController.user.getName() +
                    """
                            \n______________________________
                            """
            );
            MainMenu mainMenu = new MainMenu(authController);
            mainMenu.run();
        } else {
            System.out.println("\n\n" +
                    """
                            ______________________________
                             User registration failed
                             
                             Please try again
                            ______________________________
                            """
            );
            run();
        }
    }
}
