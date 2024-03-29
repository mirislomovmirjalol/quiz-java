package Data;

import Data.Interfaces.IQuestionData;
import Models.Option;
import Models.Question;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionData implements IQuestionData {
    private final String questionsPath = "src/Data/Database/Questions.csv";
    private final ArrayList<Question> questions = new ArrayList<Question>();
    private int lastId = 0;

    public QuestionData() {
        readQuestionsFromFile();
    }

    @Override
    public void readQuestionsFromFile() {
        try {
            questions.clear();
            File file = new File(questionsPath);
            file.createNewFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                ArrayList<Option> options = new ArrayList<Option>();
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                lastId = id;
                int categoryId = Integer.parseInt(data[1]);
                String question = data[2];
                String answer = data[3];
                String[] optionsString = data[4].split(",");
                for (String option : optionsString) {
                    options.add(new Option(option, false));
                }
                options.add(new Option(answer, true));
                Question newQuestion = new Question(id, question, new Option(answer, true), options, categoryId);
                questions.add(newQuestion);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void saveQuestionToFile(Question newQuestion) {
        readQuestionsFromFile();
        try {
            FileWriter writer = new FileWriter(questionsPath);
            for (Question question : questions) {
                writer.write(question.getId() + ";" + question.getCategoryId() + ";" + question.getQuestion() + ";" + question.getAnswer().getOption() + ";" + question.getOptionsString() + "\n");
            }
            writer.write(newQuestion.getId() + ";" + newQuestion.getCategoryId() + ";" + newQuestion.getQuestion() + ";" + newQuestion.getAnswer().getOption() + ";" + newQuestion.getOptionsString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestions(ArrayList<Question> questions) {
        try {
            FileWriter writer = new FileWriter(questionsPath);
            for (Question question : questions) {
                writer.write(question.getId() + ";" + question.getCategoryId() + ";" + question.getQuestion() + ";" + question.getAnswer().getOption() + ";" + question.getOptionsString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Question> getQuestions() {
        readQuestionsFromFile();
        return questions;
    }

    @Override
    public int getLastId() {
        return lastId;
    }

    @Override
    public int getUpdatedId() {
        readQuestionsFromFile();
        return lastId + 1;
    }

    @Override
    public Question getQuestionById(int id) {
        return questions.get(id);
    }

    @Override
    public void deleteQuestionsByCategoryId(int categoryId) {
        readQuestionsFromFile();
        ArrayList<Question> questionsToDelete = new ArrayList<Question>();
        for (Question question : questions) {
            if (question.getCategoryId() == categoryId) {
                questionsToDelete.add(question);
            }
        }
        for (Question question : questionsToDelete) {
            questions.remove(question);
        }
        updateQuestions(questions);
    }
}
