package controller.viewControllers;

import controller.mock.MockLogin;
import utils.InputDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginController {

  @FXML
  private Button btnMostrarPass;

  @FXML
  private TextField lblCorreo;

  @FXML
  private PasswordField lblPassword;

  @FXML
  private TextField lblUser;

  @FXML
  private VBox logo_square;
  private MockLogin login = new MockLogin();

  @FXML
  void iniciar(ActionEvent event) {
    String user = lblUser.getText();
    String pass = lblCorreo.getText();
    boolean resu = login.login(user, pass);
    if (!resu) {
      InputDialog.information("Error datos invalidos", "Favor ingrese ingrese datos validos");
      return;
    }
    InputDialog.information("Login correcto", "Iniciando sesion");
  }

  @FXML
  void mostrar(ActionEvent event) {

  }

}
