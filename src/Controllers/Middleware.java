package Controllers;

public class Middleware {
    private boolean isUserAuthenticated = false;
    private boolean isUserAdmin = false;

    public void authenticateUser() {
        this.isUserAuthenticated = true;
    }

    public void authenticateAdmin() {
        authenticateUser();
        this.isUserAdmin = true;
    }

    public boolean isUserAdmin() {
        return this.isUserAdmin;
    }

    public boolean isUserAuthenticated() {
        return this.isUserAuthenticated;
    }

    public void logout() {
        this.isUserAuthenticated = false;
        this.isUserAdmin = false;
    }
}
