package Models;

import java.util.ArrayList;

public class Question {
    private int id;
    private String question;
    private Option answer;
    private ArrayList<Option> options;
    private int categoryId;

    public Question(int id, String question, Option answer, ArrayList<Option> options, int categoryId) {
        setId(id);
        setQuestion(question);
        setAnswer(answer);
        setOptions(options);
        setCategoryId(categoryId);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Option getAnswer() {
        return answer;
    }

    public void setAnswer(Option answer) {
        this.answer = answer;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    public String getOptionsString() {
        String optionsString = getOptionsStringWithComma();
        return optionsString.substring(0, optionsString.length() - 1);
    }

    public String getOptionsStringWithComma() {
        StringBuilder optionsString = new StringBuilder();
        for (Option option : options) {
            if (option.isCorrect()) {
                continue;
            }
            optionsString.append(option.getOption()).append(",");
        }
        return optionsString.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void removeOption(Option option) {
        options.remove(option);
    }
}
