package Controllers.Admin;

import Controllers.AuthController;
import Data.Interfaces.IQuestionData;
import Data.QuestionData;
import Models.Question;

import java.util.ArrayList;

public class QuizController {
    AuthController authController;

    AdminController adminController;
    ArrayList<Question> questions = new ArrayList<Question>();
    public QuizController(AuthController authController, AdminController adminController) {
        this.authController = authController;
        this.adminController = adminController;
    }
}
