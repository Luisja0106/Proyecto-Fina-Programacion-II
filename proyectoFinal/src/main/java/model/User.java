package model;

public class User {
  private String name;
  private String pass;
  private String correo;

  public User() {

  }

  public User(String nom, String passw, String corre) {
    name = nom;
    pass = passw;
    correo = corre;
  }

  public User createUser(String nom, String pass, String corre) {
    if (!verifyUser(nom, pass, corre))
      return null;
    User user = new User(nom, pass, corre);
    return user;
  }

  private boolean verifyUser(String nom, String pass, String corre) {
    if (!nom.equals("luffy") || !pass.equals("1060825") || !corre.equals("luisjaperez0106@gmail.com"))
      return false;
    else
      return true;
  }

}
