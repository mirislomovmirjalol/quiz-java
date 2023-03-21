package Views.Admin;

import Controllers.Admin.AdminController;
import Controllers.AuthController;
import Views.KeyboardReader;
import Views.Messenger;

public class QuizMenu {
    KeyboardReader keyboardReader = new KeyboardReader();
    AuthController authController;
    AdminController adminController;
    Messenger messenger = new Messenger();
}
