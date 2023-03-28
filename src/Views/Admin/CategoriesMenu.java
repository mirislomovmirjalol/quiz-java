package Views.Admin;

import Controllers.Admin.CategoriesController;
import Controllers.AuthController;
import Models.Category;
import Views.KeyboardReader;
import Views.Messenger;

import java.util.ArrayList;

public class CategoriesMenu {
    AuthController authController;
    ArrayList<Category> categories = new ArrayList<Category>();
    Messenger messenger = new Messenger();
    KeyboardReader keyboardReader = new KeyboardReader();
    CategoriesController categoriesController;

    public CategoriesMenu(AuthController authController) {
        this.authController = authController;
        this.categoriesController = new CategoriesController(authController);
    }

    public void run() {
        if (!authController.isUserAdmin()) {
            messenger.notAdmin();
            return;
        }
        final int SHOW_CATEGORIES = 1;
        final int CREATE_CATEGORY = 2;
        final int UPDATE_CATEGORY = 3;
        final int DELETE_CATEGORY = 4;
        messenger.oneLineTitle("Categories");
        String[] options = {"Show", "Create", "Update", "Delete"};
        boolean exit = false;


        while (!exit) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
            switch (choice) {
                case SHOW_CATEGORIES -> categoriesController.showCategories(false);
                case CREATE_CATEGORY -> create();
                case UPDATE_CATEGORY -> update();
                case DELETE_CATEGORY -> delete();
            }
        }
    }

    public void showCategories(ArrayList<Category> categories, boolean isAction) {
        for (Category category : categories) {
            System.out.println(category.getId() + ". " + category.getName() + " | " + category.getDescription());
        }
        if (!isAction) {
            keyboardReader.getString("Press enter to continue", true);
        }
    }

    public void create() {
        messenger.oneLineTitle("Please enter title for new category");
        String title = keyboardReader.getString("Title", false);
        messenger.oneLineTitle("Please enter description for new category");
        String description = keyboardReader.getString("Description", false);
        categoriesController.create(title, description);
    }

    public void update() {
        categoriesController.showCategories(true);
        messenger.oneLineTitle("Please enter id of category to update");
        int id = keyboardReader.getInt("Id", 0, Integer.MAX_VALUE);
        Category category = categoriesController.getCategory(id);
        if (category == null) {
            messenger.notFound("Category");
            return;
        }
        final int UPDATE_TITLE = 1;
        final int UPDATE_DESCRIPTION = 2;
        boolean exit = false;
        while (!exit) {
            messenger.oneLineTitle("What do you want to update?");
            String[] options = {"Title", "Description", "Back"};
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            int choice = keyboardReader.getInt("\n\nPlease choose an option", 0, options.length);
            switch (choice) {
                case UPDATE_TITLE -> updateTitle(category);
                case UPDATE_DESCRIPTION -> updateDescription(category);
            }
        }
        messenger.oneLineTitle("Have you finished updating?");
        messenger.oneLineTitle("Have you finished updating?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = keyboardReader.getInt("Please choose an option", 1, 2);
        if (choice == 1) {
            categoriesController.update(id, category);
            exit = true;
        }
    }

    public void delete() {
        System.out.println("delete");
    }

    public void updateTitle(Category category) {
        messenger.oneLineTitle("Please enter new title for category");
        String title = keyboardReader.getString("Title", false);
        category.setName(title);
    }

    public void updateDescription(Category category) {
        messenger.oneLineTitle("Please enter new description for category");
        String description = keyboardReader.getString("Description", false);
        category.setDescription(description);
    }

}
