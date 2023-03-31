package Models;

import Data.QuestionData;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private String description;
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Category(int id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Question> getQuestions() {
        QuestionData questionData = new QuestionData();
        questionData.readQuestionsFromFile();
        ArrayList<Question> allQuestions = questionData.getQuestions();
        for (Question question : allQuestions) {
            if (question == null) {
                return null;
            }
            if (question.getCategoryId() == this.id) {
                questions.add(question);
            }
        }
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
