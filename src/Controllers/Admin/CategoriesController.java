package Controllers.Admin;

import Controllers.AuthController;
import Data.CategoryData;
import Data.Interfaces.IQuestionData;
import Data.QuestionData;
import Models.Category;
import Views.Admin.CategoriesMenu;

import java.util.ArrayList;

public class CategoriesController {
    CategoryData categoryData = new CategoryData();
    AuthController authController;
    ArrayList<Category> categories = categoryData.getCategories();

    public CategoriesController(AuthController authController) {
        this.authController = authController;
    }

    public void updateCategories() {
        categories = categoryData.getCategories();
    }

    public void showCategories(boolean isAction) {
        CategoriesMenu categoriesMenu = new CategoriesMenu(authController);
        categoriesMenu.showCategories(isAction);
    }

    public void create(String name, String description) {
        Category newCategory = new Category(categoryData.getUpdatedId(), name, description);
        categoryData.saveCategoryToFile(newCategory);
    }

    public void update(int id, Category category) {
        updateCategories();
        Category categoryToUpdate = categoryData.getCategoryById(id);
        if (categoryToUpdate == null) {
            return;
        }
        int indexOfCategory = categories.indexOf(categoryToUpdate);
        categories.set(indexOfCategory, category);
        categoryData.updateCategories(categories);
    }

    public Category getCategoryById(int id) {
        for (Category category : categoryData.getCategories()) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public void delete(int id) {
        updateCategories();
        Category categoryToDelete = categoryData.getCategoryById(id);
        if (categoryToDelete == null) {
            return;
        }
        IQuestionData questionData = new QuestionData();
        questionData.deleteQuestionsByCategoryId(id);
        categories.remove(categoryToDelete);
        categoryData.updateCategories(categories);
    }
}
