package Data.Interfaces;

import Models.User;

import java.util.ArrayList;

public interface IUserData {
    ArrayList<User> getUsers();

    void saveUser(User newUser);

    void updateUsers(ArrayList<User> users);

    void readUsersFromFile();

    int getLastId();

    int getUpdatedId();

    boolean isUserNameUnique(String username);
}
