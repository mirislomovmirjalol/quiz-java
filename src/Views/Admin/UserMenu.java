package Views.Admin;

import Controllers.Admin.AdminController;
import Controllers.Admin.UserController;
import Controllers.AuthController;
import Models.Question;
import Models.User;
import Views.KeyboardReader;
import Views.Messenger;

import java.util.ArrayList;

public class UserMenu {
    Messenger messenger = new Messenger();
    KeyboardReader keyboardReader = new KeyboardReader();
    UserController userController;
    AuthController authController;

    public UserMenu(AuthController authController) {
        this.authController = authController;
        this.userController = new UserController(authController);
    }

    public void run() {
        messenger.oneLineTitle("Users");
        String[] options = {"Show", "Create", "Update", "Delete", "Back"};
        boolean exit = false;
        final int SHOW_USERS = 1;
        final int CREATE_USER = 2;
        final int UPDATE_USER = 3;
        final int DELETE_USER = 4;
        final int BACK_OPTION = 5;
        while (!exit) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
            switch (choice) {
                case SHOW_USERS -> show(false);
                case CREATE_USER -> create();
                case UPDATE_USER -> update();
                case DELETE_USER -> delete();
                case BACK_OPTION -> exit = true;
            }
        }
    }

    public void show(Boolean isAction) {
        ArrayList<User> users = userController.getUsers();
        messenger.oneLineTitle("Users");
        for (User user : users) {
            System.out.println(user.getId() + " | " + user.getName() + " | " + user.getLogin() + " | " + user.getRole());
        }
        System.out.println("\n");
        if (!isAction) {
            keyboardReader.getString("Press enter to continue", true);
        }
    }

    public void create() {
        messenger.oneLineTitle("Please enter the name of the new user");
        String name = keyboardReader.getString("Name", false);
        messenger.oneLineTitle("Please enter the login of the new user");
        String login = keyboardReader.getString("Login", false);
        messenger.oneLineTitle("Please enter the password of the new user");
        String password = keyboardReader.getString("Password", false);
        messenger.oneLineTitle("Please choose the role of the new user");
        String[] options = {"Admin", "User"};
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
        boolean isAdmin;
        isAdmin = choice == 1;
        userController.create(name, login, password, isAdmin);
        messenger.oneLineTitle("User created successfully");
    }

    public void update() {
        messenger.oneLineTitle("Please enter the id of the user you want to update");
        show(true);
        int id = keyboardReader.getInt("Id", 0, 1000);
        User user = userController.getUserById(id);
        if (user == null) {
            messenger.oneLineTitle("User not found!");
            return;
        }
        boolean exit = false;
        final int UPDATE_NAME = 1;
        final int UPDATE_LOGIN = 2;
        final int UPDATE_PASSWORD = 3;
        final int UPDATE_ROLE = 4;
        while (!exit) {
            messenger.oneLineTitle("What do you want to update?");
            String[] options = {"Name", "Login", "Password", "Role", "Back"};
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("Please choose an option", 1, options.length);
            switch (choice) {
                case UPDATE_NAME -> {
                    updateName(user);
                }
                case UPDATE_LOGIN -> {
                    updateLogin(user);
                }
                case UPDATE_PASSWORD -> {
                    updatePassword(user);
                }
                case UPDATE_ROLE -> {
                    updateRole(user);
                }
            }
            messenger.oneLineTitle("Have you finished updating?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            choice = keyboardReader.getInt("Please choose an option", 1, 2);
            if (choice == 1) {
                userController.update(id, user);
                exit = true;
            }
        }
    }

    public void delete() {
        System.out.println("Delete");
    }

    public void updateName(User user) {
        messenger.twoLineTitle("Please enter the new name of the user", "Current name: " + user.getName());
        String name = keyboardReader.getString("Name", false);
        user.setName(name);
    }

    public void updateLogin(User user) {
        messenger.twoLineTitle("Please enter the new login of the user", "Current login: " + user.getLogin());
        String login = keyboardReader.getString("Login", false);
        user.setLogin(login);
    }

    public void updatePassword(User user) {
        messenger.oneLineTitle("Please enter the new password of the user");
        String password = keyboardReader.getString("Password", false);
        user.setPassword(password);
    }

    public void updateRole(User user) {
        messenger.oneLineTitle("Please choose the new role of the user");
        String[] options = {"Admin", "User"};
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        int choice = keyboardReader.getInt("Please choose an option", 1, options.length);
        if (choice == 1) {
            user.setAdmin(true);
        } else {
            user.setAdmin(false);
        }
    }
}
