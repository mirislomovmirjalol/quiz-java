package Controllers;

import Data.IUserData;
import Data.UserData;
import Models.User;

public class AuthController {
    public User user;
    public IUserData userData = new UserData();
    public Middleware middleware = new Middleware();

    public void register(String name, String username, String password) {
        this.user = new User(userData.getUpdatedId(), name, username, password, false);
        userData.saveUser(this.user);
        this.user = null;
        login(username, password);
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
}
