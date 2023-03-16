package Models;

import java.util.ArrayList;

public class Question {
    private int id;
    private String question;
    private Option answer;
    private ArrayList<Option> options;

    public Question(int id, String question, Option answer, ArrayList<Option> options) {
        setId(id);
        setQuestion(question);
        setAnswer(answer);
        setOptions(options);
    }

    public String getQuestion() {
        return question;
    }

    public Option getAnswer() {
        return answer;
    }

    public ArrayList<Option> getOptions() {
        return options;
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

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(Option answer) {
        this.answer = answer;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
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
