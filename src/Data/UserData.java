package Data;

import Data.Interfaces.IUserData;
import Models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserData implements IUserData {
    private final String usersPath = "src/Data/Database/Users.csv";
    private final ArrayList<User> users = new ArrayList<>();
    private int lastId = 0;

    public UserData() {
        readUsersFromFile();
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public void saveUser(User newUser) {
        readUsersFromFile();
        try {
            FileWriter writer = new FileWriter(usersPath);
            for (User user : users) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getLogin() + "," + user.getPassword() + "," + user.isAdmin() + "\n");
            }
            writer.write(newUser.getId() + "," + newUser.getName() + "," + newUser.getLogin() + "," + newUser.getPassword() + "," + newUser.isAdmin() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsers(ArrayList<User> users) {
        try {
            FileWriter writer = new FileWriter(usersPath);
            for (User user : users) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getLogin() + "," + user.getPassword() + "," + user.isAdmin() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void readUsersFromFile() {
        try {
            users.clear();
            File file = new File(usersPath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                lastId = id;
                String name = data[1];
                String login = data[2];
                String password = data[3];
                boolean isAdmin = Boolean.parseBoolean(data[4]);
                User user = new User(id, name, login, password, isAdmin);
                users.add(user);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public int getLastId() {
        return lastId;
    }

    @Override
    public int getUpdatedId() {
        readUsersFromFile();
        return lastId + 1;
    }

    @Override
    public boolean isUserNameUnique(String username) {
        readUsersFromFile();
        for (User user : users) {
            if (user.getLogin().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        readUsersFromFile();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
