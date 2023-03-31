package Tests;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataPathTest {
    @org.junit.jupiter.api.Test
    void usersData() throws FileNotFoundException {
        final String usersPath = "src/Data/Database/Users.csv";
        java.io.File file = new java.io.File(usersPath);
        FileReader fileReader = new FileReader(file);
    }

    @org.junit.jupiter.api.Test
    void questionsData() throws FileNotFoundException {
        final String questionsPath = "src/Data/Database/Questions.csv";
        java.io.File file = new java.io.File(questionsPath);
        FileReader fileReader = new FileReader(file);
    }

    @org.junit.jupiter.api.Test
    void quizData() throws FileNotFoundException {
        final String quizPath = "src/Data/Database/Quizzes.csv";
        java.io.File file = new java.io.File(quizPath);
        FileReader fileReader = new FileReader(file);
    }

    @org.junit.jupiter.api.Test
    void categoriesData() throws FileNotFoundException {
        final String categoriesPath = "src/Data/Database/Categories.csv";
        java.io.File file = new java.io.File(categoriesPath);
        FileReader fileReader = new FileReader(file);
    }
}
