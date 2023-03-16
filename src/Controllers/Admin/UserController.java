package Controllers.Admin;

import Controllers.AuthController;
import Data.UserData;
import Models.Question;
import Models.User;

import java.util.ArrayList;

public class UserController {
    UserData userData = new UserData();
    ArrayList<User> users = new ArrayList<User>();
    AuthController authController;

    public UserController(AuthController authController) {
        users = userData.getUsers();
        this.authController = authController;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void create(String name, String login, String password, Boolean role) {
        User newUser = new User(userData.getUpdatedId(), name, login, password, role);
        userData.saveUser(newUser);
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void update(int id, User updatedUser) {
        User userToUpdate = getUserById(id);
        int indexOfUser = users.indexOf(userToUpdate);
        users.set(indexOfUser, updatedUser);
        userData.updateUsers(users);
    }
}
