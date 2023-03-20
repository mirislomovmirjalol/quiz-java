package Views;

import java.util.Scanner;

public class KeyboardReader {

    Scanner m_sc;
    Messenger messenger = new Messenger();

    public KeyboardReader() {
        m_sc = new Scanner(System.in);
    }

    public int getInt(String title, int min, int max) {
        int retv = 0;
        boolean valid = false;
        do {
            try {
                System.out.print(title + " : ");
                String input = m_sc.nextLine();
                retv = Integer.parseInt(input);
                if (retv < min || retv > max) {
                    messenger.twoLineTitle(("Only valid integers between " + min + " and " + max + " are allowed"), ("Please try again"));
                } else {
                    valid = true;
                }
                return retv;
            } catch (NumberFormatException e) {
                messenger.twoLineTitle("Only valid integers are allowed", "Please try again");
            }
        } while (!valid);
        return retv;
    }

    public String getString(String title, boolean allowEmpty) {
        String retv = "";
        boolean valid = false;
        do {
            try {
                System.out.print(title + " : ");
                String input = m_sc.nextLine();
                if (!allowEmpty) {
                    if (!isStringValid(input)) {
                        messenger.twoLineTitle("Input doesn't allow empty", "Please enter a valid input");
                        continue;
                    }
                }
                valid = true;
                return input;
            } catch (NumberFormatException e) {
                messenger.twoLineTitle("Only valid integers are allowed", "Please try again");
            }
        } while (!valid);
        return retv;
    }

    public boolean isStringValid(String string) {
        return string != null && !string.isEmpty();
    }
}