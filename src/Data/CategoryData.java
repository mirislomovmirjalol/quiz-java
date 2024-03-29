package Data;

import Data.Interfaces.ICategoryData;
import Models.Category;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryData implements ICategoryData {
    String categoriesPath = "src/Data/Database/Categories.csv";
    ArrayList<Category> categories = new ArrayList<Category>();

    private int lastId = 0;

    public CategoryData() {
        readCategoriesFromFile();
    }

    @Override
    public void readCategoriesFromFile() {
        try {
            categories.clear();
            File file = new File(categoriesPath);
            file.createNewFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                lastId = id;
                String title = data[1];
                String description = data[2];
                Category category = new Category(id, title, description);
                categories.add(category);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void saveCategoryToFile(Category newCategory) {
        readCategoriesFromFile();
        try {
            FileWriter writer = new FileWriter(categoriesPath);
            for (Category category : categories) {
                writer.write(category.getId() + "," + category.getName() + "," + category.getDescription() + "\n");
            }
            writer.write(newCategory.getId() + "," + newCategory.getName() + "," + newCategory.getDescription() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategories(ArrayList<Category> categories) {
        try {
            FileWriter writer = new FileWriter(categoriesPath);
            for (Category category : categories) {
                writer.write(category.getId() + "," + category.getName() + "," + category.getDescription() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public int getLastId() {
        return lastId;
    }

    @Override
    public int getUpdatedId() {
        readCategoriesFromFile();
        return lastId + 1;
    }

    @Override
    public Category getCategoryById(int id) {
        readCategoriesFromFile();
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }
}