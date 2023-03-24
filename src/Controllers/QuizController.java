package Controllers;

import Data.Interfaces.IQuestionData;
import Data.Interfaces.IQuizData;
import Data.QuestionData;
import Data.QuizData;
import Models.Question;
import Models.Quiz;
import Views.QuizMenu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class QuizController {
    ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<String> questionsString = new ArrayList<String>();
    IQuestionData questionData = new QuestionData();
    IQuizData IQuizData = new QuizData();
    AuthController authController;
    Boolean isRandomizeQuestions = true;

    public QuizController(AuthController authController) {
        this.authController = authController;
        questionData.readQuestionsFromFile();
        questions = questionData.getQuestions();
    }

    public void startQuiz() {
        QuizMenu quizMenu = new QuizMenu();
        Quiz quiz = new Quiz(IQuizData.getUpdatedId());
        if (isRandomizeQuestions) {
            Collections.shuffle(questions, new Random());
        }
        if (questions.size() == 0) {
            Views.MainMenu mainMenu = new Views.MainMenu(authController);
            mainMenu.run();
        }
        quiz.setQuestions(questions);
        quiz.setMaxScore(questions.size());
        int score = 0;
        for (Question question : quiz.getQuestions()) {
            Collections.shuffle(question.getOptions(), new Random());
            if (quizMenu.quiz(question.getQuestion(), question.getOptions()).isCorrect()) {
                score++;
            }
        }
        int questionsSize = quiz.getQuestions().size();
        quiz.setScore(score);
        quiz.setUserId(authController.user.getId());
        quiz.setPercentage((double) score / questionsSize * 100);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        quiz.setDate(dateTimeFormatter.format(now));
        quizMenu.endQuiz(score, questionsSize);
        IQuizData.saveQuizToFile(quiz);
        Views.MainMenu mainMenu = new Views.MainMenu(authController);
        mainMenu.run();
    }


}
