package Controllers;

import Data.QuestionData;
import Models.Option;
import Models.Question;
import Views.AdminMenu;
import Views.QuestionAdminMenu;

import java.util.ArrayList;

public class AdminQuestionController {

    private AuthController authController;
    private AdminMenu adminMenu;
    private QuestionData questionData = new QuestionData();
    private AdminController adminController;
    ArrayList<Question> questions = new ArrayList<Question>();


    public AdminQuestionController(AuthController authController, AdminController adminController) {
        this.authController = authController;
        this.adminController = adminController;
        this.adminMenu = new AdminMenu(authController, adminController);
    }

    public void showQuestions(boolean isAction) {
        questions = questionData.getQuestions();
        QuestionAdminMenu questionAdminMenu = new QuestionAdminMenu(authController, adminController);
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

    public void deleteQuestion(int id) {
        Question questionToDelete = getQuestionById(id);
        questions.remove(questionToDelete);
        questionData.updateQuestions(questions);
    }

}