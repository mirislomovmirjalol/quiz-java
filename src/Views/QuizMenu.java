package Views;

import Controllers.QuizController;
import Models.Option;

import java.util.ArrayList;

public class QuizMenu {
    public KeyboardReader keyboardReader = new KeyboardReader();
    Messenger messenger = new Messenger();

    public Option quiz(String question, ArrayList<Option> options) {
        messenger.oneLineTitle(question);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "." + options.get(i).getOption());
        }
        return options.get(keyboardReader.getInt("Please choose an option", 1, options.size()) - 1);
    }

    public void endQuiz(int score, int questionsSize) {
        messenger.endQuiz(score, questionsSize);
        keyboardReader.getString("Press enter to continue", true);
    }

    public void noQuestions() {
        messenger.notFound("Questions");
        keyboardReader.getString("Press enter to continue", true);
    }
}
