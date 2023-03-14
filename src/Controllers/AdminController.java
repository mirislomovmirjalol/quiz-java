package Controllers;

import Data.QuestionData;
import Models.Option;
import Models.Question;
import Views.AdminMenu;
import Views.QuestionAdminMenu;

import java.util.ArrayList;

public class AdminController {
    private AuthController authController;
    private AdminMenu adminMenu;
    private QuestionData questionData = new QuestionData();
    ArrayList<Question> questions = new ArrayList<Question>();

    public AdminController(AuthController authController) {
        this.authController = authController;
        this.adminMenu = new AdminMenu(authController, this);
        adminMenu.run();
    }

    public void showQuestions(boolean isAction) {
        questions = questionData.getQuestions();
        QuestionAdminMenu questionAdminMenu = new QuestionAdminMenu(authController, this);
        questionAdminMenu.show(questions, isAction);
    }

    public void createQuestion(String question, String answer, String options) {
        Option answerOption = new Option(answer, true);
        ArrayList<Option> optionsList = new ArrayList<Option>();
        String[] optionsArray = options.split(",");
        for (String option : optionsArray) {
            optionsList.add(new Option(option, false));
        }
        Question newQuestion = new Question(questionData.getUpdatedId(), question, answerOption, optionsList);
        questionData.saveQuestionToFile(newQuestion);

//        reminder - message to user
    }

    public int sizeOfQuestions() {
        return questionData.getQuestions().size();
    }

    public void updateQuestion(int id, Question question) {
        Question questionToUpdate = getQuestionById(id);
        int indexOfQuestion = questions.indexOf(questionToUpdate);
        questions.set(indexOfQuestion, question);
        questionData.updateQuestions(questions);
    }

    public Question getQuestionById(int id) {
        for (Question question : questionData.getQuestions()) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }
}
