package controller.mock;

import controller.LoginInterface;
import model.User;

public class MockLogin implements LoginInterface {
    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean createAccount(User newUser) {
        return false;
    }

}
