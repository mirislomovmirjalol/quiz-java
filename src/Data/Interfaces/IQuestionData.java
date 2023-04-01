package Data.Interfaces;

import Models.Question;

import java.util.ArrayList;

public interface IQuestionData {
    void readQuestionsFromFile();

    void saveQuestionToFile(Question newQuestion);

    void updateQuestions(ArrayList<Question> questions);

    ArrayList<Question> getQuestions();

    int getLastId();

    int getUpdatedId();

    Question getQuestionById(int id);

    void deleteQuestionsByCategoryId(int categoryId);
}
