package controller;
import model.User;

public interface LoginInterface {
    public boolean login(String username, String password);
    public boolean createAccount(User newUser);
}
