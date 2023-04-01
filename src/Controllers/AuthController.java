package Controllers;

import Data.Interfaces.IUserData;
import Data.UserData;
import Models.User;

public class AuthController {
    public User user;
    IUserData userData = new UserData();
    Middleware middleware = new Middleware();

    public boolean register(String name, String username, String password) {
        if (userData.isUserNameUnique(username)) {
            return false;
        }
        this.user = new User(userData.getUpdatedId(), name, username, password, false);
        userData.saveUser(this.user);
        this.user = null;
        login(username, password);
        return true;
    }

    public void login(String username, String password) {
        userData.readUsersFromFile();
        boolean isUserFound = false;
        for (User user : userData.getUsers()) {
            if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                this.user = user;
                isUserFound = true;
                break;
            }
        }
        if (isUserFound) {
            if (this.user.isAdmin()) {
                middleware.authenticateAdmin();
            } else {
                middleware.authenticateUser();
            }
        }
    }

    public boolean isUserLoggedIn() {
        return this.user != null;
    }

    public boolean isUserAdmin() {
        return middleware.isUserAdmin();
    }

    public void logout() {
        this.user = null;
        middleware.logout();
    }
}
