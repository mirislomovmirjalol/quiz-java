package Controllers.Admin;

import Controllers.AuthController;
import Data.Interfaces.IQuestionData;
import Data.QuestionData;
import Models.Option;
import Models.Question;
import Views.Admin.AdminMenu;
import Views.Admin.QuestionMenu;

import java.util.ArrayList;

public class QuestionController {

    AuthController authController;
    IQuestionData questionData = new QuestionData();
    AdminController adminController;
    ArrayList<Question> questions = new ArrayList<Question>();


    public QuestionController(AuthController authController, AdminController adminController) {
        this.authController = authController;
        this.adminController = adminController;
    }

    public void showQuestions(boolean isAction) {
        questions = questionData.getQuestions();
        QuestionMenu questionMenu = new QuestionMenu(authController, adminController);
        questionMenu.show(questions, isAction);
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
