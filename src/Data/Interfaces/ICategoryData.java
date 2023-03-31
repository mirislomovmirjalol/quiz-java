package Data.Interfaces;

import Models.Category;

import java.util.ArrayList;

public interface ICategoryData {
    void readCategoriesFromFile();

    void saveCategoryToFile(Category newCategory);

    void updateCategories(ArrayList<Category> categories);

    ArrayList<Category> getCategories();

    int getLastId();

    int getUpdatedId();

    Category getCategoryById(int id);
}
