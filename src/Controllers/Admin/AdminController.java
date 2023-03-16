package Controllers.Admin;

import Controllers.AuthController;
import Views.Admin.AdminMenu;

public class AdminController {
    AuthController authController;
    AdminMenu adminMenu;

    public AdminController(AuthController authController) {
        this.authController = authController;
        this.adminMenu = new AdminMenu(authController, this);
        adminMenu.run();
    }
}
