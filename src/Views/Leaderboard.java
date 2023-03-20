package Views;

import java.util.ArrayList;

public class Leaderboard {
    Messenger messenger = new Messenger();
    KeyboardReader keyboardReader = new KeyboardReader();

    public void showLeaderboard(ArrayList<String> leaderboard) {
        messenger.oneLineTitle("Leaderboard");
        for (int i = 0; i < leaderboard.size(); i++) {
            System.out.println((i + 1) + ". " + leaderboard.get(i));
        }
        System.out.println("\n");
        keyboardReader.getString("Press enter to continue", true);
    }
}
