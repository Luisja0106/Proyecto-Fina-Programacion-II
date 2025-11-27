package controller.viewControllers;

import application.App;
import controller.login.ListaUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.InputDialog;
import utils.Paths;

public class RegistroController {

  @FXML
  private Button btnMostrarNewPass;

  @FXML
  private Button btnMostrarPrepe;

  @FXML
  private TextField lblCorreo;

  @FXML
  private TextField lblUser;

  @FXML
  private PasswordField lblnewPHide;

  @FXML
  private TextField lblnewPUnhide;

  @FXML
  private PasswordField lblrepePHide;

  @FXML
  private TextField lblrepePUnHide;

  @FXML
  private VBox logo_square;

  private ListaUsers users = new ListaUsers();

  @FXML
  void Crear(ActionEvent event) {
    String password = (lblrepePUnHide.isVisible()) ? lblrepePUnHide.getText() : lblrepePHide.getText();
    String passRepead = (lblnewPUnhide.isVisible()) ? lblnewPUnhide.getText() : lblnewPHide.getText();
    if (!password.equals(passRepead)) {
      InputDialog.error("Error las contraseñas no coinciden", "Las contraseñas no coinciden");
      return;
    }
    if (!verification())
      return;
    users.crearCuenta(lblUser.getText(), password, lblCorreo.getText());
  }

  @FXML
  void Ingresar(ActionEvent event) {
    App.app.setScene(Paths.GESTIONAR_LOGIN_VIEW);
  }

  @FXML
  void mostrarNew(ActionEvent event) {
    switchHide(btnMostrarNewPass, lblnewPHide, lblnewPUnhide);
  }

  @FXML
  void mostrarRepe(ActionEvent event) {
    switchHide(btnMostrarPrepe, lblrepePHide, lblrepePUnHide);
  }

  private boolean verification() {
    // se guarda la contraseña
    String password = (lblrepePUnHide.isVisible()) ? lblrepePUnHide.getText() : lblrepePHide.getText();
    String passRepead = (lblnewPUnhide.isVisible()) ? lblnewPUnhide.getText() : lblnewPHide.getText();
    // se verifica que no existan campos vacios
    if (password.isEmpty() || password.isBlank() || passRepead.isEmpty() || passRepead.isBlank()
        || lblUser.getText().isBlank() || lblUser.getText().isEmpty() || lblCorreo.getText().isEmpty()
        || lblCorreo.getText().isBlank()) {
      InputDialog.warning("Campos vacios", "Favor llene toda la informacion");
      return false;
    }
    // se verifca que el correo tenga la estructura correcta
    if (!lblCorreo.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
      InputDialog.warning("Correo Invalido", "Favor ingrese un correo valido");
      return false;
    }
    return true;
  }

  private void switchHide(Button btn, PasswordField hide, TextField unHide) { // meotod para swichear la visibilidad de
                                                                              // la contraseña
    if (btn.getText().equals("MOSTRAR")) {
      // almacenamos datos de la posicion del cursor y la info
      unHide.setText(hide.getText());
      hide.setText("");
      // cambio de visibilidad
      unHide.setVisible(true);
      hide.setVisible(false);
      // ubicamos el cursor
      unHide.requestFocus();
      unHide.positionCaret(unHide.getText().length());
      // cambio en el boton
      btn.setText("OCULTAR");
    } else {
      // almacenamos datos de la posicion del cursor y la info
      hide.setText(unHide.getText());
      unHide.setText("");
      // cambio de visibilidad
      hide.setVisible(true);
      unHide.setVisible(false);
      // ubicamos el cursor
      hide.requestFocus();
      hide.positionCaret(hide.getText().length());
      // cambio en el boton
      btn.setText("MOSTRAR");
    }
  }
}
