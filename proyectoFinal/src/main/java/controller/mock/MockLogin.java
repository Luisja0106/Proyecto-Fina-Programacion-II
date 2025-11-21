package controller.mock;

import controller.LoginInterface;
import model.User;

public class MockLogin implements LoginInterface {
  @Override
  public boolean login(String username, String password) {
    return username.equals("luffy") && password.equals("01060825");
  }

  @Override
  public boolean createAccount(User newUser) {
    if (newUser != null) {
      return true;
    } else {
      return false;
    }
  }

}
