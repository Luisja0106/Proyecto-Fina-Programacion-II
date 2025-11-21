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
  private PasswordField lblPasswordHide;

  @FXML
  private TextField lblUser;

  @FXML
  private VBox logo_square;
  @FXML
  private TextField lblPasswordUnHide;
  private MockLogin login = new MockLogin();

  @FXML
  void iniciar(ActionEvent event) {
    if (!verificaciones())
      return;
    String user = lblUser.getText();
    String pass = lblPasswordHide.getText();
    boolean resu = login.login(user, pass);
    if (!resu) {
      InputDialog.information("Error datos invalidos", "Favor ingrese ingrese datos validos");
      return;
    }
    InputDialog.information("Login correcto", "Iniciando sesion");
  }

  // TODO: añadir creacion de cuenta, y gestion de la misma.

  @FXML
  void mostrar(ActionEvent event) {
    if (btnMostrarPass.getText().equals("MOSTRAR")) {
      // almacenamos datos de la posicion del cursor y la info
      lblPasswordUnHide.setText(lblPasswordHide.getText());
      lblPasswordHide.setText("");
      // cambio de visibilidad
      lblPasswordUnHide.setVisible(true);
      lblPasswordHide.setVisible(false);
      // ubicamos el cursor
      lblPasswordUnHide.requestFocus();
      lblPasswordUnHide.positionCaret(lblPasswordUnHide.getText().length());
      // cambio en el boton
      btnMostrarPass.setText("OCULTAR");
    } else {
      // almacenamos datos de la posicion del cursor y la info
      lblPasswordHide.setText(lblPasswordUnHide.getText());
      lblPasswordUnHide.setText("");
      // cambio de visibilidad
      lblPasswordHide.setVisible(true);
      lblPasswordUnHide.setVisible(false);
      // ubicamos el cursor
      lblPasswordHide.requestFocus();
      lblPasswordHide.positionCaret(lblPasswordHide.getText().length());
      // cambio en el boton
      btnMostrarPass.setText("MOSTRAR");
    }
  }

  private boolean verificaciones() {
    if (lblUser.getText().isEmpty() || lblPasswordHide.getText().isEmpty() || lblCorreo.getText().isEmpty()) {
      InputDialog.warning("Llene todos los campos", "Favor llene todos los campos");
      return false;
    }
    if (!lblCorreo.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
      InputDialog.warning("Correo Invalido", "Favor ingrese un correo valido");
      return false;
    }
    return true;
  }

  public void initialize() {
    lblPasswordUnHide.setVisible(false);
  }

}
