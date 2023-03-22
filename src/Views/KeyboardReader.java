package Views;

import java.util.Scanner;

public class KeyboardReader {

    Scanner m_sc;

    public KeyboardReader() {
        m_sc = new Scanner(System.in);
    }

    public int getInt(String title, int min, int max) {
        final String INTEGER_REGEX = "^-?\\d+$";
        final String ERROR_MESSAGE_MIN_MAX = "Only valid integers between %d and %d are allowed";
        final String ERROR_MESSAGE_INTEGER = "Only valid integers are allowed";
        int retv = 0;
        boolean valid = false;
        do {
            System.out.print(title + " : ");
            String input = m_sc.nextLine();
            if (input.matches(INTEGER_REGEX)) {
                retv = Integer.parseInt(input);
                if (retv < min || retv > max) {
                    Messenger messenger = new Messenger();
                    messenger.twoLineTitle(String.format(ERROR_MESSAGE_MIN_MAX, min, max), "Please try again");
                } else {
                    valid = true;
                }
            } else {
                Messenger messenger = new Messenger();
                messenger.twoLineTitle(String.format(ERROR_MESSAGE_INTEGER), "Please try again");
            }
        } while (!valid);
        return retv;
    }

    public String getString(String title, boolean allowEmpty) {
        final String ERROR_MESSAGE_EMPTY = "Input must not be empty";
        final String ERROR_MESSAGE_COMMA = "Input must not contain comma (,) character";
        String retv = "";
        boolean valid = false;
        do {
            System.out.print(title + " : ");
            String input = m_sc.nextLine();
            if (!allowEmpty && !isStringValid(input)) {
                Messenger messenger = new Messenger();
                messenger.twoLineTitle(String.format(ERROR_MESSAGE_EMPTY), "Please try again");
                continue;
            }
            if (input.contains(",")) {
                Messenger messenger = new Messenger();
                messenger.twoLineTitle(String.format(ERROR_MESSAGE_COMMA), "Please try again");
                continue;
            }
            valid = true;
            retv = input;
        } while (!valid);
        return retv;
    }

    public boolean isStringValid(String string) {
        return string != null && !string.isEmpty();
    }
}