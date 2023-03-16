package Models;

import Controllers.Middleware;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;

    private boolean isAdmin = false;

    public User(int id, String name, String login, String password, boolean isAdmin) {
        setId(id);
        setName(name);
        setLogin(login);
        setPassword(password);
        setAdmin(isAdmin);
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        if (isAdmin()) {
            return "Admin";
        } else {
            return "User";
        }
    }
}
