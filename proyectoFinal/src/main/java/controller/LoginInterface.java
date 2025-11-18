package controller;
import model.User;

public interface LoginInterface {
    public User login(String username, String password);
    public boolean createAccount(User newUser);
}
