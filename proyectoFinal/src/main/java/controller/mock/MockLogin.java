package controller.mock;

import controller.LoginInterface;
import model.User;

public class MockLogin implements LoginInterface {
  @Override
  public boolean login(String username, String password) {
    if (username == "luffy") {
      return true;
    }
    if (password == "01060825") {
      return true;
    }
    return false;
  }

  @Override
  public boolean createAccount(User newUser) {
    return false;
  }

}
