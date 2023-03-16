package Models;

import java.util.ArrayList;

public class Quiz {
    private int id;
    private int userId;
    private int score;
    private int maxScore;
    private double percentage;
    private String date;
    public ArrayList<Question> questions;

    public Quiz(int id) {
        setId(id);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
