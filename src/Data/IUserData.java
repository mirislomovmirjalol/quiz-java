package Data;

import Models.User;

import java.util.ArrayList;

public interface IUserData {
    ArrayList<User> getUsers();

    void saveUser(User newUser);

    void readUsersFromFile();

    int getLastId();

    int getUpdatedId();
}
