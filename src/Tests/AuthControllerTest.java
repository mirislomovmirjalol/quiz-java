package Tests;

import Data.UserData;
import Models.User;
import Views.Messenger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

class AuthControllerTest {

    @org.junit.jupiter.api.Test
    void register() {
        UserData userData = new UserData();
        int id = userData.getUpdatedId();
        String name = "Test name";
        SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String login = "testLogin" + timestamp.format(new Timestamp(System.currentTimeMillis()));
        String password = "testPassword";
        boolean isAdmin = false;
        if (userData.isUserNameUnique(login)) {
            Messenger messenger = new Messenger();
            messenger.oneLineTitle("Username is already taken!");
            return;
        }
        User user = new User(id, name, login, password, isAdmin);
        userData.saveUser(user);
    }
}