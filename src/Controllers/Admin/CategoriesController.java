package Controllers.Admin;

import Controllers.AuthController;
import Data.CategoryData;
import Models.Category;
import Models.Question;
import Views.Admin.CategoriesMenu;

import java.util.ArrayList;

public class CategoriesController {
    CategoryData categoryData = new CategoryData();
    AuthController authController;
    ArrayList<Category> categories = categoryData.getCategories();

    public CategoriesController(AuthController authController) {
        this.authController = authController;
    }

    public void showCategories(boolean isAction) {
        categories = categoryData.getCategories();
        CategoriesMenu categoriesMenu = new CategoriesMenu(authController);
        categoriesMenu.showCategories(categories, isAction);
    }

    public void create(String name, String description) {
        Category newCategory = new Category(categoryData.getUpdatedId(), name, description);
        categoryData.saveCategoryToFile(newCategory);
    }

    public void update(int id, Category category) {
        Category categoryToUpdate = categoryData.getCategoryById(id);
        if (categoryToUpdate == null) {
            return;
        }
        int indexOfCategory = categories.indexOf(categoryToUpdate);
        categories.set(indexOfCategory, category);
        categoryData.updateCategories(categories);
    }

    public Category getCategory(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }
}
