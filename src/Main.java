import Views.AuthMenu;
import Views.Messenger;

public class Main {
    public static void main(String[] args) {
        Messenger messenger = new Messenger();
        messenger.oneLineTitle("Welcome to Quiz App");
        AuthMenu authMenu = new AuthMenu();
        authMenu.run();
    }
}