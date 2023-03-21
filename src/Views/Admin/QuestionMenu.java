package Views.Admin;

import Controllers.Admin.AdminController;
import Controllers.Admin.QuestionController;
import Controllers.AuthController;
import Models.Option;
import Models.Question;
import Views.KeyboardReader;
import Views.Messenger;

import java.util.ArrayList;

public class QuestionMenu {
    KeyboardReader keyboardReader = new KeyboardReader();
    AuthController authController;
    QuestionController questionController;
    AdminController adminController;
    Messenger messenger = new Messenger();

    public QuestionMenu(AuthController authController, AdminController adminController) {
        this.authController = authController;
        this.adminController = adminController;
        this.questionController = new QuestionController(authController, adminController);
    }

    public void run() {
        final int SHOW_QUESTIONS = 1;
        final int CREATE_QUESTION = 2;
        final int UPDATE_QUESTION = 3;
        final int DELETE_QUESTION = 4;
        final int BACK_OPTION = 5;
        if (!authController.isUserAdmin()) {
            messenger.notAdmin();
            return;
        }
        messenger.oneLineTitle("Questions");
        String[] options = {"Show", "Create", "Update", "Delete", "Back"};
        boolean exit = false;


        while (!exit) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
            switch (choice) {
                case SHOW_QUESTIONS -> questionController.showQuestions(false);
                case CREATE_QUESTION -> create();
                case UPDATE_QUESTION -> update();
                case DELETE_QUESTION -> delete();
                case BACK_OPTION -> exit = true;
            }
        }

    }

    public void show(ArrayList<Question> questions, boolean isAction) {
        for (Question question : questions) {
            System.out.println(question.getId() + ". " + question.getQuestion() + " | " + question.getAnswer().getOption() + " | " + question.getOptionsString());
        }
        System.out.println("\n");
        if (!isAction) {
            keyboardReader.getString("Press enter to continue", true);
        }
    }

    public void create() {
        messenger.oneLineTitle("Please enter the question");
        String question = keyboardReader.getString("Question", false);
        messenger.oneLineTitle("Please enter the answer");
        String answer = keyboardReader.getString("Answer", false);
        messenger.oneLineTitle("Please enter the options");
        StringBuilder options = new StringBuilder(keyboardReader.getString("Options", false));
        boolean finishOptions = true;
        while (finishOptions) {
            messenger.oneLineTitle("Do you want to add more options?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = keyboardReader.getInt("Please choose an option", 1, 2);
            if (choice == 1) {
                messenger.oneLineTitle("Please enter the option");
                options.append(",").append(keyboardReader.getString("Option:", false));
            } else {
                finishOptions = false;
            }
        }
        questionController.createQuestion(question, answer, options.toString());
    }

    public void update() {
        questionController.showQuestions(true);
        messenger.oneLineTitle("Please enter the question id that you want to update");
        int id = keyboardReader.getInt("Id", 0, questionController.sizeOfQuestions());
        Question question = questionController.getQuestionById(id);
        if (question == null) {
            messenger.oneLineTitle("Question not found!");
            return;
        }
        final int UPDATE_QUESTION = 1;
        final int UPDATE_ANSWER = 2;
        final int UPDATE_OPTIONS = 3;
        boolean exit = false;
        while (!exit) {
            messenger.oneLineTitle("What do you want to update?");
            String[] options = {"Question", "Answer", "Options", "Back"};
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("Please choose an option", 1, options.length);
            switch (choice) {
                case UPDATE_QUESTION -> {
                    updateQuestion(question);
                }
                case UPDATE_ANSWER -> {
                    updateAnswer(question);
                }
                case UPDATE_OPTIONS -> {
                    updateOptions(question);
                }
            }
            messenger.oneLineTitle("Have you finished updating?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            choice = keyboardReader.getInt("Please choose an option", 1, 2);
            if (choice == 1) {
                questionController.updateQuestion(id, question);
                exit = true;
            }
        }
    }

    public void delete() {
        questionController.showQuestions(true);
        messenger.oneLineTitle("Please enter the question id that you want to delete");
        int id = keyboardReader.getInt("Id", 0, questionController.sizeOfQuestions());
        Question question = questionController.getQuestionById(id);
        if (question == null) {
            messenger.oneLineTitle("Question not found!");
            return;
        }
        questionController.deleteQuestion(id);
    }

    public void updateQuestion(Question question) {
        messenger.updateQuestion(question.getQuestion());
        String newQuestion = keyboardReader.getString("Question", false);
        question.setQuestion(newQuestion);
    }

    public void updateAnswer(Question question) {
        messenger.updateAnswer(question.getAnswer().getOption());
        String newAnswer = keyboardReader.getString("Answer", false);
        Option answerAsOption = new Option(newAnswer, true);
        question.setAnswer(answerAsOption);
    }

    public void updateOptions(Question question) {
        messenger.oneLineTitle("What do you want to do?");
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. Remove");
        System.out.println("4. Back");
        final int ADD_OPTION = 1;
        final int UPDATE_OPTION = 2;
        final int REMOVE_OPTION = 3;
        int option = keyboardReader.getInt("Please choose an option", 1, 4);
        switch (option) {
            case ADD_OPTION -> {
                updateAddOptions(question);
            }
            case UPDATE_OPTION -> {
                updateUpdateOptions(question);
            }
            case REMOVE_OPTION -> {
                updateRemoveOptions(question);
            }

        }
    }

    public void updateRemoveOptions(Question question) {
        messenger.oneLineTitle("Please enter the option id that you want to remove");
        for (int i = 0; i < question.getOptions().size(); i++) {
            System.out.println((i + 1) + ". " + question.getOptions().get(i).getOption());
        }
        int optionId = keyboardReader.getInt("Id", 0, question.getOptions().size());
        Option optionThatShouldRemove = question.getOptions().get(optionId - 1);
        question.removeOption(optionThatShouldRemove);
    }

    public void updateUpdateOptions(Question question) {
        boolean finishUpdatingOptions = true;
        while (finishUpdatingOptions) {
            messenger.oneLineTitle("Please enter the option id that you want to update");
            for (int i = 0; i < question.getOptions().size(); i++) {
                if (question.getOptions().get(i).isCorrect()) {
                    continue;
                }
                System.out.println((i + 1) + ". " + question.getOptions().get(i).getOption());
            }
            int optionId = keyboardReader.getInt("Id", 0, question.getOptions().size());
            messenger.updateOption(question.getOptions().get(optionId - 1).getOption());

            String newOption = keyboardReader.getString("Option", false);
            question.getOptions().get(optionId - 1).setOption(newOption);
            messenger.oneLineTitle("You want to update another options?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice2 = keyboardReader.getInt("Please choose an option", 1, 2);
            if (choice2 == 2) {
                finishUpdatingOptions = false;
            }
        }
    }

    public void updateAddOptions(Question question) {
        messenger.oneLineTitle("Please enter the options");
        String newOption = keyboardReader.getString("Option", false);
        Option newOptionAsOption = new Option(newOption, false);
        question.addOption(newOptionAsOption);
        boolean finishCreatingOptions = true;
        while (finishCreatingOptions) {
            messenger.oneLineTitle("You want to add more options?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice2 = keyboardReader.getInt("Please choose an option", 1, 2);
            if (choice2 == 1) {
                messenger.oneLineTitle("Please enter the options");
                newOption = keyboardReader.getString("Option", false);
                newOptionAsOption = new Option(newOption, false);
                question.addOption(newOptionAsOption);
            } else {
                finishCreatingOptions = false;
            }
        }
    }
}
